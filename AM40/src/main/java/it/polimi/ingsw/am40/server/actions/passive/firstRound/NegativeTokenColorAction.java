package it.polimi.ingsw.am40.server.actions.passive.firstRound;

import it.polimi.ingsw.am40.server.ActionAgent;
import it.polimi.ingsw.am40.server.actions.Action;

/**
 * This class serves as a mean of the notification to the VirtualView which then will notify the client by using the Network interface
 * that the Color of the Token that has been selected was already chosen
 */
public class NegativeTokenColorAction extends Action {

    //CONSTRUCTOR
    /**
     * Constructor for Negative Token selection response
     */
    public NegativeTokenColorAction(int gameID, int playerID){
        super("NEGATIVE_TOKEN_COLOR", gameID, playerID);
    }

    /**
     * Override of doAction for the negative response after a Token selection
     * @param agent is the Game in which we perform the Action
     */
    @Override
    public void doAction(ActionAgent agent){

    }

}
