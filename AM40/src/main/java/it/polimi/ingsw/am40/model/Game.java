package it.polimi.ingsw.am40.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Author of the following note : Andrea
//TODO Add the Game constructor for the controller's use, (Note: remainingRounds should be set to a default value
// till the call of calculateRemainingRounds() method)

/**
 * The Game class is the entry point from the controller and manages the correct flow of the game.
 * Its first role is to initialize and set up everything in order to play.
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
     * This method returns an ArrayList of each Players' Token color
     * with the index corresponding to the order of players of this class
     * @return all the Token colors chosen by the players during the starting phase of the Game
     */
    public ArrayList<Color> getPlayersTokenColor() {
        ArrayList<Color> playersTokenColor = new ArrayList<>();
        for (Player p: players) {
            playersTokenColor.add(p.getToken().getColor());
        }
        return playersTokenColor;
    }

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
            int singleNumOfAim = 0; // num of aim done on each card
            int totalNumOfAim = 0; // total num of aim done by a player

            // increments by adding personal aim points, and common aim points
            singleNumOfAim = player.getPrivateAim().calculateNumOfPatternVerified(player.getPrivateBoard());
            finalScore += singleNumOfAim * player.getPrivateAim().getPoints();
            totalNumOfAim += singleNumOfAim;

            singleNumOfAim = commonBoard.getPlateAimCard().getFirst().calculateNumOfPatternVerified(player.getPrivateBoard());
            finalScore += singleNumOfAim * commonBoard.getPlateAimCard().getFirst().getPoints();
            totalNumOfAim += singleNumOfAim;

            singleNumOfAim = commonBoard.getPlateAimCard().getLast().calculateNumOfPatternVerified(player.getPrivateBoard());
            finalScore += singleNumOfAim * commonBoard.getPlateAimCard().getLast().getPoints();
            totalNumOfAim += singleNumOfAim;

            // maximum points for each player are 29
            if (finalScore > 29) {
                finalScore = 29;
            }

            player.setScore(finalScore);
            player.setNumOfAimDone(totalNumOfAim);
        }
    }

    /**
     * This method is the simulation of the card placing phase.
     * It checks if the Card, the card orientation and the position chosen by a Player are legal in order to proceed
     * with the placing.
     * In case that the choice leads to an illegal move according to the rules, this method doesn't do anything.
     * So, it's the controller responsibility to manage the correct procedure
     * @param choice is the card chosen by a player to be placed
     * @param coordinates are the coordinates chosen by a player to indicate where the card will be placed
     * @param cardFace is the orientation of the card chosen by a player
     */
    public void placeCard(int choice, Coordinates coordinates, CardFace cardFace) {
        int index = getIndexOfPlayingPlayer();
        PrivateBoard currentPlayerPrivateBoard = this.players.get(index).getPrivateBoard();
        //Check if the choice, the coordinates and the CardFace are legal.
        if (currentPlayerPrivateBoard.checkPlacing(currentPlayerPrivateBoard.getHandDeck().get(choice), coordinates, cardFace)) {
            currentPlayerPrivateBoard.placing(currentPlayerPrivateBoard.takeCardFromHand(choice), coordinates, cardFace);
            currentPlayerPrivateBoard.refreshElementsCounter();
            currentPlayerPrivateBoard.refreshPlacingCoordinates();
            this.players.get(index).increaseScore(currentPlayerPrivateBoard.refreshPoints());
        }
    }

    /**
     * This method checks the player that is playing and it returns the index
     * @return the index of the playing Player as int
     */
    private int getIndexOfPlayingPlayer(){
        for (Player player : players) {
            if (player.isCurrentlyPlaying()) {
                return players.indexOf(player);
            }
        }
        return 0;
    }

}
