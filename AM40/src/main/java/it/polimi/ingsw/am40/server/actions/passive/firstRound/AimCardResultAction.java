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
    public AimCardResultAction(String nickname, int gameID, int playerID, int aimCardChosenID){
        super("AIM_CARD_SELECTED", nickname, gameID, playerID);
        this.aimCardChosenID = aimCardChosenID;
    }

    /**
     * Override of doAction for AimCard face choice confirmation
     * @param agent is the VVServer from which the Action is transformed into a Data and sent
     */
    @Override
    public void doAction(ActionAgent agent){
        VVServer v = (VVServer) agent;
        v.sendOnNetworkBroadcastInAParty(this.getGameID(), this.dataCreator());
    }

    /**
     * Override of dataCreator for the creation of the respective result Data
     * @return an AimCardResultData
     */
    @Override
    public Data dataCreator() {
        return new AimCardResultData(this.getNickname(), this.aimCardChosenID);
    }

}
