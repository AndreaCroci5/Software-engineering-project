package it.polimi.ingsw.am40.server.actions.passive.firstRound;

import it.polimi.ingsw.am40.server.ActionAgent;
import it.polimi.ingsw.am40.server.actions.Action;

public class StartingCardResultAction extends Action {

    //CONSTRUCTOR
    /**
     * Constructor for Positive Starting Card CardFace selection response
     */
    public StartingCardResultAction(int gameID, int playerID){
        super("POSITIVE_STARTING_CARD", gameID, playerID);
    }

    /**
     * Override of doAction for the positive response after a StartingCard Face selection phase
     * @param agent is the Game in which we perform the Action
     */
    @Override
    public void doAction(ActionAgent agent){

    }
}
