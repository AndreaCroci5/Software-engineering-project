package it.polimi.ingsw.am40.server.actions.passive.round;

import it.polimi.ingsw.am40.server.actions.Action;

/**
 * This class serves as a mean to notify to the VirtualView which then will notify the client by using the Network interface.
 * This Action is usually called by an Exception handling when a Player tries to draw a Card from an empty plate or Deck
 */
public class RepeatDrawAction extends Action {

    //CONSTRUCTOR
    /**
     * Constructor for Negative Draw action response
     */
    public RepeatDrawAction(int gameID, int playerID){
        super("REPEAT_DRAW", gameID, playerID);
    }

}
