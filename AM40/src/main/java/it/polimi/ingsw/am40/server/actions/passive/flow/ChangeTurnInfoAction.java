package it.polimi.ingsw.am40.server.actions.passive.flow;

import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.data.passive.flow.ChangeTurnInfoData;
import it.polimi.ingsw.am40.server.ActionAgent;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.network.virtual_view.VVServer;

/**
 * This class serves as a mean to notify to the VirtualView which then will notify the client by using the Network interface
 * that the turn has changed on the model
 */
public class ChangeTurnInfoAction extends Action {
    //Attributes
    /**
     * Index of the next Player that has the right to play
     */
    private final String nextActivePlayerNick;

    //CONSTRUCTOR

    /**
     * Constructor for Change Turn Information response
     */
    public ChangeTurnInfoAction(String nickname, int gameID, int playerID, String nextActivePlayerNick) {
        super("CHANGE_TURN_INFO", nickname, gameID, playerID);
        this.nextActivePlayerNick = nextActivePlayerNick;
    }


    @Override
    public void doAction(ActionAgent agent){
        VVServer v = (VVServer) agent;
        v.sendOnNetworkBroadcastInAParty(this.getGameID(), dataCreator());
    }

    public Data dataCreator() {
        return new ChangeTurnInfoData(this.getNickname(), this.nextActivePlayerNick);
    }
}
