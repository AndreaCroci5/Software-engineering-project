package it.polimi.ingsw.am40.server.actions.passive.round;

import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.data.passive.round.RepeatPlacingData;
import it.polimi.ingsw.am40.server.ActionAgent;
import it.polimi.ingsw.am40.server.actions.Action;

/**
 * This class serves as a mean to notify to the VirtualView which then will notify the client by using the Network interface.
 * This Action is usually called by an Exception handling when a Player tries to place a Card in an illegal position
 * or tries to place a GoldenCard, but it doesn't satisfy the resource requirements shown on the GoldenCard
 */
public class RepeatPlacingAction extends Action {

    //CONSTRUCTOR
    /**
     * Constructor for Negative placing action response
     */
    public RepeatPlacingAction(String nickname, int gameID, int playerID){
        super("REPEAT_PLACING", nickname, gameID, playerID);
    }


    @Override
    public Data dataCreator() {
        return new RepeatPlacingData(this.getNickname());
    }
}
