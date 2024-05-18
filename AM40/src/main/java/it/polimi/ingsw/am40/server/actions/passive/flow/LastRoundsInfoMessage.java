package it.polimi.ingsw.am40.server.actions.passive.flow;

import it.polimi.ingsw.am40.server.actions.Action;

public class LastRoundsInfoMessage extends Action {
    //Attributes

    //CONSTRUCTOR
    /**
     * Constructor for Last Rounds Information response when Endgame phase is triggered
     */
    public LastRoundsInfoMessage(int gameID, int playerID){
        super("LAST_ROUNDS", gameID, playerID);
    }
}
