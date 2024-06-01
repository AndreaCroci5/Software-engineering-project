package it.polimi.ingsw.am40.server.actions.passive.firstRound;

import it.polimi.ingsw.am40.server.ActionAgent;
import it.polimi.ingsw.am40.server.actions.Action;

/**
 * This class serves as a mean to notify to the VirtualView which then will notify the client by using the Network interface
 * that the StartingCard CardFace chosen is correctly set
 */
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
