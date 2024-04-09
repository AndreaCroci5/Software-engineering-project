package it.polimi.ingsw.am40.model;

import java.util.ArrayList;
import java.util.List;

//Author of the notes: Andrea
//TODO Add the getPlayersColors() method
//TODO Add the constructor, (Note: remainingRounds will be set to a default value till the endgame state)
//FIXME Once the Commonboard is pushed on the Repo fix references and errors if present
//FIXME Row 14 and 18 of the code, Javadoc the initialization and the winner selection
//TODO Add the public methods once everything is pushed

/**
 * The Game class is the entry point from the controller and manages the correct flow of the game.
 * His first role is to initialize and set up everything in order to plau.
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
        List<Player> temp = new ArrayList<>();
        for (Player player : players) {
            if (temp.isEmpty()) {
                temp.add(player);
            } else if (player.getScore() > temp.getFirst().getScore()) {
                temp.clear();
                temp.add(player);
            } else if (player.getScore() == temp.getFirst().getScore()) {
                temp.add(player);
            }
        }
        return temp;
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
        res = 4 + (3 - index); // 4 for the last rounds, (3-index) for finishing the current round
        // 3-index means the distance between the current player and the one that started

        setRemainingRounds(res);
    }
}
