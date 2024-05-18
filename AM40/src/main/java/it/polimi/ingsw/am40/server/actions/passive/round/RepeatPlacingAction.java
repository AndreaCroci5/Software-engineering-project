package it.polimi.ingsw.am40.server.actions.passive.round;

import it.polimi.ingsw.am40.server.ActionAgent;
import it.polimi.ingsw.am40.server.actions.Action;

public class RepeatPlacingAction extends Action {

    //CONSTRUCTOR
    /**
     * Constructor for Negative placing action response
     */
    public RepeatPlacingAction(int gameID, int playerID){
        super("REPEAT_PLACING", gameID, playerID);
    }

}
