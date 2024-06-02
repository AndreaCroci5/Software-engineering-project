package it.polimi.ingsw.am40.server.actions.passive.firstRound;

import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.data.passive.firstRound.AimCardResultData;
import it.polimi.ingsw.am40.server.ActionAgent;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.network.virtual_view.VVServer;

/**
 * This class serves as a mean to notify to the VirtualView which then will notify the client by using the Network interface
 * that the AimCard that the Player wants is correctly selected
 */
public class AimCardResultAction extends Action {
    //ATTRIBUTES
    /** ID of the AimCard chosen by the Player*/
    private final int aimCardChosenID;


    //CONSTRUCTOR
    /**
     * Constructor for AimCard choice selection response
     */
    public AimCardResultAction(int gameID, int playerID, int aimCardChosenID){
        super("AIM_CARD_SELECTED", gameID, playerID);
        this.aimCardChosenID = aimCardChosenID;
    }

    @Override
    public void doAction(ActionAgent agent){
        VVServer v = (VVServer) agent;
        v.sendOnNetworkUnicast(this.getPlayerID(), this.dataCreator());
    }

    @Override
    public Data dataCreator() {
        return new AimCardResultData(this.aimCardChosenID);
    }

}
