package it.polimi.ingsw.am40.server.actions.passive.firstRound;

import it.polimi.ingsw.am40.server.ActionAgent;
import it.polimi.ingsw.am40.server.actions.Action;

public class PositiveTokenColorAction extends Action {
    //CONSTRUCTOR
    /**
     * Constructor for Positive Token selection response
     */
    public PositiveTokenColorAction(int gameID, int playerID){
        super("POSITIVE_TOKEN_COLOR", gameID, playerID);
    }

    /**
     * Override of doAction for the positive Token selection
     * @param agent is the Game in which we perform the Action
     */
    @Override
    public void doAction(ActionAgent agent){

    }
}
