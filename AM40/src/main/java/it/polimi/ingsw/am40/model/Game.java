package it.polimi.ingsw.am40.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Author of the notes: Andrea
//TODO Add the getPlayersColors() method
//TODO Add the constructor, (Note: remainingRounds will be set to a default value till the endgame state)
//FIXME Once the Commonboard is pushed on the Repo fix references and errors if present
//FIXME Row 14 and 18 of the code, Javadoc the initialization and the winner selection
//TODO Add the public methods once everything is pushed

/**
 * The Game class is the entry point from the controller and manages the correct flow of the game.
 * His first role is to initialize and set up everything in order to play.
 * Then the Game class works as a bridge from all the Players to the CommonBoard,
 * in order to ease the cards passing through the boards during a Player turn.
 * Lastly once the end game phase is triggered, it calculates the number of the remaining rounds
 * and then returns the winner.
 */
public class Game {
    //REFERENCE ATTRIBUTES

    /**Reference to all the players that are in the Game*/
    private ArrayList<Player> players;
    /**Reference to the CommonBoard where all the Decks are kept and where players draw cards*/
    private CommonBoard commonBoard;


    //GAME ATTRIBUTES

    /**Number of the remaining rounds once the endgame phase is triggered*/
    private int remainingRounds;

    //GETTERS

    /**
     * Getter for the reference of all the players
     * @return players as a reference to an Arraylist of Player
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * Getter for CommonBoard reference
     * @return commonBoard as a CommonBoard reference
     */
    public CommonBoard getCommonBoard() {
        return commonBoard;
    }

    /**
     * Getter for remainingRounds
     * @return the number of the rounds remaining as int
     */
    public int getRemainingRounds() {
        return remainingRounds;
    }


    //SETTER

    /**
     * Setter for remainingRounds
     * @param remainingRounds to set the number of the remaining rounds
     */
    public void setRemainingRounds(int remainingRounds) {
        this.remainingRounds = remainingRounds;
    }

    //PUBLIC METHODS

    /**
     * This method checks if one of the player reached 20 points or if the decks are empty
     * in order to start the end game
     * @return true if the decks are empty or a player has more than 20 points, false otherwise
     */
    public boolean checkEndGame() {
        // checks if someone have 20 or more points
        for (Player player : players) {
            if (player.getScore() >= 20) {
                return true;
            }
        }

        // checks if the decks are empty return true if they are, false otherwise
        return commonBoard.deckEmptinessCheck();
    }

    /**
     * This method select the player that has more points than the others
     * In case of draw select all the players that have more points than the others
     * @return a list of player that could have from one to four players
     */
    public List<Player> selectWinner() {
        List<Player> temp = new ArrayList<>(); // list of player/players that has more points
        for (Player player : players) {
            if (temp.isEmpty()) {
                temp.add(player);
            } else if (player.getScore() > temp.getFirst().getScore()) {
                temp.clear(); // remove all the previous player added
                temp.add(player); // add this one that has more points
            } else if (player.getScore() == temp.getFirst().getScore()) {
                temp.add(player); // add this one that has the same points without removing the others
            }
        }
        if(temp.size() == 1) { // there is no tie
            return temp;
        }

        // if there is a tie we have to check which player has the most aim done
        List<Player> winners = new ArrayList<>();
        for (Player player : temp) {
            if (winners.isEmpty()) {
                winners.add(player);
            }
            else if (player.getNumOfAimDone() > winners.getFirst().getNumOfAimDone() ) {
                winners.clear(); // remove all the previous player
                winners.add(player); // add this one that has more aim done
            }
            else if (player.getNumOfAimDone() == winners.getFirst().getNumOfAimDone()) {
                winners.add(player); // add this one that has the same aim done without removing the others
            }
        }
        return winners;
    }

    /**
     * This method calculates how many rounds are left after we start the end game procedure
     */
    public void calculateRemainingRounds() {
        int res;
        int index = 0; // the index of the current player that is playing the round
        for (Player player : players) {
            if (player.isCurrentlyPlaying()) {
                index = players.indexOf(player);
            }
        }
        res = 4 + (3 - index); // 4 for the last round, (3-index) for finishing the current round
        // 3-index means the distance between the current player and the one that started

        setRemainingRounds(res);
    }

    /**
     * This method execute the starting game flow of the game.
     * 1st) It loads the cards from JSON
     * 2nd) It creates a new common board and initialises it
     * 3rd) It creates the players with their own private board
     * 4th) It decides the player order and the first player (starting player)
     * 5th) It calls the methods for the first run
     * @param nicknames List of player nicknames from controller
     */
    public void startGame(List<String> nicknames){

        //1st) It loads the cards from JSON
        //2nd) It creates a new common board and initialises it
        JSONCardLoader cardLoader = new JSONCardLoader();
        this.commonBoard = new CommonBoard(cardLoader.loadCards());
        this.commonBoard.iniCommonBoard();

        //3rd) It creates the players with their own private board
        for (String n : nicknames) {
            this.players.add(new Player(n, this));
        }

        //4th) It decides the player order and the first player (starting player)
        this.decidePlayerOrder();
        //5th) It calls the methods for the first run
        this.playFirstRound();
    }

    //TO CHECK (MESSAGES)
    /**
     * This method calls other sub-methods to play the first round of the game
     */
    private void playFirstRound() {
        for (Player p : this.players) {
            /*
            p.chooseInitialColor()
            p.choosePrivateAim()
            p.chooseInitialCardFacing()
            */
            for (int i = 0; i < 2; i++) {
                p.getPrivateBoard()
                        .getHandDeck()
                        .add(this.commonBoard.getResourceDeck().
                                pickFromTop());
            }

            p.getPrivateBoard()
                    .getHandDeck()
                    .add(this.commonBoard.getGoldenResourceDeck()
                            .pickFromTop());

        }
    }

    /**
     * This method decides the player order and sets the first player as starting player
     */
    private void decidePlayerOrder() {
        Collections.shuffle(this.players);
        this.players.getFirst().setStartingPlayerTrue();
    }

    /**
     * This method perform a draw
     * Values of choice and selection comes from the controller, and they're checked there
     * @param choice is the choice of resource(0) or golden(1)
     * @param selection is the selection of the two cards on plate(0),plate(1) or deck(2)
     */
    public void draw(int choice, int selection) {
        ResourceCard temp = null;

        switch(choice) {
            case 0 -> {
                switch (selection) {
                    case 0, 1 -> {
                        temp = commonBoard.pickFromResourcePlate(selection);
                        commonBoard.addCartToResourcePlate(selection);
                    }
                    case 2 -> {
                        temp = commonBoard.getResourceDeck().pickFromTop();
                    }
                    default -> {
                        // No action
                    }
                }
            }
            case 1 -> {
                switch (selection) {
                    case 0, 1 -> {
                        temp = commonBoard.pickFromGoldenPlate(selection);
                        commonBoard.addCartToGoldenPlate(selection);
                    }
                    case 2 -> {
                        temp = commonBoard.getGoldenResourceDeck().pickFromTop();
                    }
                    default -> {
                        // No action
                    }
                }
            }
            default -> {
                // No action
            }
        }
        for (Player player : players) {
            if (player.isCurrentlyPlaying()) {
                player.getPrivateBoard().addCardToHand(temp);
            }
        }
    }

    /**
     * This method calculate the final score for each player by adding the aim cards
     */
    public void calculateFinalScore() {
        for (Player player : players) {
            int finalScore = player.getScore(); // take the score

            // increments by adding personal aim points, and common aim points
            finalScore += player.getPrivateAim().calculatePoints(player.getPrivateBoard());
            finalScore += commonBoard.getPlateAimCard().getFirst().calculatePoints(player.getPrivateBoard());
            finalScore += commonBoard.getPlateAimCard().getLast().calculatePoints(player.getPrivateBoard());

            // maximum points for each player are 29
            if (finalScore > 29) {
                finalScore = 29;
            }

            player.setScore(finalScore);
        }
    }
}
