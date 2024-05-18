package it.polimi.ingsw.am40.server.actions.passive.round;

import it.polimi.ingsw.am40.server.actions.Action;

public class RepeatDrawAction extends Action {

    //CONSTRUCTOR
    /**
     * Constructor for Negative Draw action response
     */
    public RepeatDrawAction(int gameID, int playerID){
        super("REPEAT_DRAW", gameID, playerID);
    }

}
