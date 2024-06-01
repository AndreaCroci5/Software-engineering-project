package it.polimi.ingsw.am40.server.actions.passive.firstRound;

import it.polimi.ingsw.am40.server.ActionAgent;
import it.polimi.ingsw.am40.server.actions.Action;

/**
 * This class serves as a mean to notify to the VirtualView which then will notify the client by using the Network interface
 * about the two AimCards from which he will have to choose one to keep
 */
public class AimCardInfoAction extends Action {
    //ATTRIBUTES
    private final int aimID1;
    private final int aimID2;

    //CONSTRUCTOR
    /**
     * Constructor for AimCardInfoAction
     */
    public AimCardInfoAction(int gameID, int playerID, int aimID1, int aimID2){
        super("AIM_CARD_INFO", gameID, playerID);
        this.aimID1 = aimID1;
        this.aimID2 = aimID2;
    }

    /**
     * Override of doAction for AimCard info transport
     * @param agent is the Game in which we perform the Action
     */
    @Override
    public void doAction(ActionAgent agent){

    }


}
