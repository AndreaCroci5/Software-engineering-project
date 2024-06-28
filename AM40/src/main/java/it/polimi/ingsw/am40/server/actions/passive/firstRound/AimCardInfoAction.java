package it.polimi.ingsw.am40.server.actions.passive.firstRound;

import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.data.passive.firstRound.AimCardInfoData;
import it.polimi.ingsw.am40.server.ActionAgent;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.network.virtual_view.VVServer;

/**
 * This class serves as a mean to notify to the VirtualView which then will notify the client by using the Network interface
 * about the two AimCards from which he will have to choose one to keep
 */
public class AimCardInfoAction extends Action {
    //ATTRIBUTES
    private final int aimID1;
    private final int aimID2;

    //CONSTRUCTOR
    public AimCardInfoAction(String nickname, int gameID, int playerID, int aimID1, int aimID2){
        super("AIM_CARD_INFO", nickname, gameID, playerID);
        this.aimID1 = aimID1;
        this.aimID2 = aimID2;
    }

    /**
     * Override of doAction for AimCard info transport
     * @param agent is the VVServer from which the Action is transformed into a Data and sent
     */
    @Override
    public void doAction(ActionAgent agent){
        VVServer v = (VVServer) agent;
        v.sendOnNetworkBroadcastInAParty(this.getGameID(), this.dataCreator());
    }

    /**
     * Override of dataCreator for the creation of the respective Data
     * @return an AimCardInfoData
     */
    @Override
    public Data dataCreator() {
        return new AimCardInfoData(this.getNickname(),this.aimID1, this.aimID2);
    }

}
