package it.polimi.ingsw.am40.server.model;

import it.polimi.ingsw.am40.server.exceptions.model.ForceEndgameTurnException;
import it.polimi.ingsw.am40.server.exceptions.model.RepeatDrawException;
import it.polimi.ingsw.am40.server.exceptions.model.TurnException;
import it.polimi.ingsw.am40.server.ActionListener;
import it.polimi.ingsw.am40.server.ActionPoster;
import it.polimi.ingsw.am40.server.actions.Action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static it.polimi.ingsw.am40.server.model.CommonBoard.plateAimCard;

/**
 * The Game class is the entry point from the controller and manages the correct flow of the game.
 * Its first role is to initialize and set up everything in order to play.
 * Then the Game class works as a bridge from all the Players to the CommonBoard,
 * in order to ease the cards passing through the boards during a Player turn.
 * Lastly once the end game phase is triggered, it calculates the number of the remaining rounds
 * and then returns the winner.
 */
public class Game implements ActionPoster {

    //REFERENCE ATTRIBUTES

    /**Reference to all the players that are in the Game*/
    private ArrayList<Player> players;
    /**Reference to the CommonBoard where all the Decks are kept and where players draw cards*/
    private CommonBoard commonBoard;
    /**
     * List of Objects which implement the ActionListener interface, i.e. classes that want to be notified by the model
     */
    private List<ActionListener> listeners;


    //GAME ATTRIBUTES

    /**Number of the remaining rounds once the endgame phase is triggered*/
    private int remainingRounds;

    //CONSTRUCTOR
    public Game() {
        this.players = new ArrayList<Player>(); ;
        this.commonBoard = new CommonBoard(); ;
        this.remainingRounds = 70;
        this.listeners = new ArrayList<>();
    }

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

        int numOfPlayerPlaying = 0;
        for (Player player : players) {
                numOfPlayerPlaying++;
        }

        res = numOfPlayerPlaying + (numOfPlayerPlaying - (index + 1));
        // numOfPlayerPlaying for the final round
        // numOfPlayerPlaying - (index + 1) is the ending of the current round

        if (res < 0) { // this is in case of zero players (it shouldn't happen)
            res = 0;
        }
        setRemainingRounds(res);
    }

    /**
     * This method execute the starting game flow of the game.
     * 1st) It loads the cards from JSON
     * 2nd) It creates a new common board and initialises it
     * 3rd) It creates the players with their own private board
     * 4th) It decides the player order and the first player (starting player)
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
    }

    /**
     * This method is used during the first round where a Player choose on which side place the StartingCard
     * @param cardFace the CardFace chosen by the Player in order to place the StaringCard
     */
    public void placeStartingCard(CardFace cardFace) {
        //Place the Starting Card recently added to handDeck as first Card, in position (0,0)
        placeCard(0,new Coordinates(0,0),cardFace);
    }

    /**
     * This method is used during the first round where a Player choose on which side place the StartingCard
     * @param color the CardFace chosen by the Player in order to place the StaringCard
     */
    public void chooseTokenColor(Color color){
        int index = getIndexOfPlayingPlayer();
        Player currentPlayer = this.players.get(index);

        currentPlayer.getToken().setColor(color);
    }

    /**
     * This method is called during the first phase of the Game where the Players have to receive their first two ResourceCards
     * and one GoldenResourceCard. This method draw from the correct Decks and assigns the Cards drawn to the Player that is
     * in the "active" mode
     */
    public void dealCards() {
        try {
            draw(0,2);
            draw(0,2);
            draw(1,2);
        } catch (RepeatDrawException e) {
            //It doesn't happen
        }
    }

    /**
     * This method is used during the first round where a Player one of the two AimCards offered
     * @param choice is the AimCard chosen by the Client: (0) the first, (1) the second
     */
    public void aimCardSelection(int choice) {
        int index = getIndexOfPlayingPlayer();
        Player currentPlayer = this.players.get(index);

        //AimCardSelection
        if (choice == this.getCommonBoard().getAimDeck().peekFirstCard()) {
            //Selection
            AimCard cardSelected = this.getCommonBoard().getAimDeck().pickFromTop();
            currentPlayer.setPrivateAim(cardSelected);
            //Discard of the other AimCard
            AimCard cardDiscarded = this.getCommonBoard().getAimDeck().pickFromTop();
            this.getCommonBoard().getAimDeck().appendToBottom(cardDiscarded);
        } else {
            //Discard of the other AimCard
            AimCard cardDiscarded = this.getCommonBoard().getAimDeck().pickFromTop();
            this.getCommonBoard().getAimDeck().appendToBottom(cardDiscarded);
            //Selection
            AimCard cardSelected = this.getCommonBoard().getAimDeck().pickFromTop();
            currentPlayer.setPrivateAim(cardSelected);
        }
    }

    /**
     * This method is called during the first phase of the Game where the Players have to decide the Color of the Token
     * that thew want. In order to do that the Client requests the List of all the Colors still available and not already
     * chosen by the other Players before the active turn of choice. This method fetch all the remaining Colors and put them
     * in a List (the value NONE is excluded from the list)
     * @return the reference to a List containing all the remaining Colors of the Tokens
     */
    public List<String> remainingTokenColors() {
        Color[] allColors = Color.values();
        ArrayList<Color> remainingColors = new ArrayList<>();
        for(Color c : allColors) {
            boolean isTaken = false;
            for (Player p : this.players) {
                if (c == p.getToken().getColor() && c!= Color.NONE) {
                    isTaken = true;
                }
            }
            if (!isTaken) {
                remainingColors.add(c);
            }
        }
        //List<String> creation
        return remainingColors.stream()
                .map(Color::toString)
                .collect(Collectors.toList());
    }

    /**
     * This method decides the player order and sets the first player as starting player
     */
    public void decidePlayerOrder() {
        //Set everyone as not-CurrentlyPlaying and not first-Playing
        for (Player p : players) {
            p.setCurrentlyPlaying(false);
            p.setCurrentlyPlaying(false);
        }
        Collections.shuffle(this.players);
        this.players.getFirst().setStartingPlayerTrue();
        this.players.getFirst().setCurrentlyPlaying(true);
    }

    //FIXME FINISH DRAW EXC with plate emptiness check and deck emptiness from draw and make throw a repeat draw exception
    /**
     * This method perform a draw
     * Values of choice and selection comes from the controller, and they're checked there
     * @param choice is the choice of resource(0) or golden(1)
     * @param selection is the selection of the two cards on plate(0),plate(1) or deck(2)
     */
    public void draw(int choice, int selection) throws RepeatDrawException {
        ResourceCard temp = null;

        switch(choice) {
            case 0 -> {
                switch (selection) {
                    case 0, 1 -> {
                        temp = commonBoard.pickFromResourcePlate(selection);
                        commonBoard.addCardToResourcePlate(selection);
                    }
                    case 2 -> {
                        temp = commonBoard.getResourceDeck().pickFromTop();
                    }
                    default -> {
                        throw new RepeatDrawException();
                    }
                }
            }
            case 1 -> {
                switch (selection) {
                    case 0, 1 -> {
                        temp = commonBoard.pickFromGoldenPlate(selection);
                        commonBoard.addCardToGoldenPlate(selection);
                    }
                    case 2 -> {
                        temp = commonBoard.getGoldenResourceDeck().pickFromTop();
                    }
                    default -> {
                        throw new RepeatDrawException();
                    }
                }
            }
            default -> {
                throw new RepeatDrawException();
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

            singleNumOfAim = plateAimCard[0].calculateNumOfPatternVerified(player.getPrivateBoard());
            finalScore += singleNumOfAim * plateAimCard[0].getPoints();
            totalNumOfAim += singleNumOfAim;

            singleNumOfAim = plateAimCard[1].calculateNumOfPatternVerified(player.getPrivateBoard());
            finalScore += singleNumOfAim * plateAimCard[1].getPoints();
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
     * It's called by the controller if checkPlaceCard returns true
     * The role of this method is: to place a Card, to set the right EdgeState in TAKEN, to refresh the elementsCounter,
     * to refresh the placingCoordinates for future placings and to refresh the score of a Player
     * @param choice is the card chosen by a player to be placed
     * @param coordinates are the coordinates chosen by a player to indicate where the card will be placed
     * @param cardFace is the orientation of the card chosen by a player
     */
    public void placeCard(int choice, Coordinates coordinates, CardFace cardFace) {
        int index = getIndexOfPlayingPlayer();
        PrivateBoard currentPlayerPrivateBoard = this.players.get(index).getPrivateBoard();

        //Place Phase
        currentPlayerPrivateBoard.placing(currentPlayerPrivateBoard.takeCardFromHand(choice), coordinates, cardFace);
        currentPlayerPrivateBoard.refreshElementsCounter();
        currentPlayerPrivateBoard.refreshPlacingCoordinates();
        this.players.get(index).increaseScore(currentPlayerPrivateBoard.refreshPoints());
    }

    /**
     * This method checks if the Card, the card orientation and the position chosen by a Player are legal,
     * in order to proceed with the card placement
     * @param choice is the card chosen by a player to be placed
     * @param coordinates are the coordinates chosen by a player to indicate where the card will be placed
     * @param cardFace is the orientation of the card chosen by a player
     * @return true if the Player's choice is legal. false otherwise
     */
    public boolean checkPlaceCard(int choice, Coordinates coordinates, CardFace cardFace) {
        int index = getIndexOfPlayingPlayer();
        PrivateBoard currentPlayerPrivateBoard = this.players.get(index).getPrivateBoard();

        //Check
        if (currentPlayerPrivateBoard.checkPlacing(currentPlayerPrivateBoard.getHandDeck().get(choice), coordinates, cardFace))
            return true;
        else return false;
    }


    /**
     * This method checks the player that is playing and it returns the index
     * @return the index of the playing Player as int
     */
    public int getIndexOfPlayingPlayer(){
        for (Player player : players) {
            if (player.isCurrentlyPlaying()) {
                return players.indexOf(player);
            }
        }
        return 0;
    }

    /**
     * This method is used after the end of every Round or Phase in order to change the Player that has the right
     * to play its turn
     * @param index is the index of the actual player, should use getIndexOfPlayingPlayer method
     */
    public void changePlayersTurn(int index) throws TurnException {
        //This check serves to control the final rounds
        if (remainingRounds<8 && remainingRounds>0) {
            remainingRounds--;
        }
        if (remainingRounds == 0) {
            throw new ForceEndgameTurnException();
        }
        //Check if the next Player is Online and in that case puts him as CurrentlyPlaying for the nextTurn recursively
        if (remainingRounds != 0) {
            this.players.get(index).setCurrentlyPlaying(false);
            if (index != this.players.size()-1) {
                if(this.players.get(++index).isOnline()){
                    this.players.get(++index).setCurrentlyPlaying(true);
                } else {
                    changePlayersTurn(++index);
                }
            } else {
                if(this.players.get(0).isOnline()){
                    this.players.get(0).setCurrentlyPlaying(true);
                } else {
                    changePlayersTurn(1);
                }
                this.players.get(0).setCurrentlyPlaying(true);
            }
        }
    }


    //INTERFACE METHODS IMPLEMENTATION

    /**
     * @param listener           Reference to the new object which implements "action listener"
     * @param listenersContainer Collection of the listeners
     */
    @Override
    public void addListener(ActionListener listener, Collection<ActionListener> listenersContainer) {
        listenersContainer.add(listener);
    }

    /**
     * @param listener           Reference to the object you want to remove
     * @param listenersContainer Collection of the listeners
     */
    @Override
    public void removeListener(ActionListener listener, Collection<ActionListener> listenersContainer) {
        listenersContainer.remove(listener);
    }

    /**
     * @param event              Message to post
     * @param listenersContainer Collection of the listeners
     */
    @Override
    public void notifyListeners(Action event, Collection<ActionListener> listenersContainer) {
        for (ActionListener l : listenersContainer) {
            l.onEvent(event);
        }
    }

    /**
     * Getter for the list of listeners
     *
     * @return the list of references of the listeners
     */
    @Override
    public List<ActionListener> getListeners() {
        return this.listeners;
    }
}
