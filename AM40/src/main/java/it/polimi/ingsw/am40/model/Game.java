package it.polimi.ingsw.am40.model;

import java.util.ArrayList;

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


}
