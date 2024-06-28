package it.polimi.ingsw.am40.server.actions;

import it.polimi.ingsw.am40.data.InterruptedGameData;
import it.polimi.ingsw.am40.server.ActionAgent;
import it.polimi.ingsw.am40.server.network.virtual_view.VVServer;

/**
 * This class represents the Action made by the server after a client has disconnected.
 * It proceeds to notify all the Clients of this event
 * */
public class ClientDisconnectedAction extends Action {

    public ClientDisconnectedAction(String nickname, int gameID, int playerID) {
        super("CLIENT_DISCONNECTED", nickname, gameID, playerID);
    }

    /**
     * Override of doAction for Client Disconnected
     * @param agent is the VVServer from which the Action is performed and transformed into a Data and sent
     */
    public void doAction(ActionAgent agent) {
        VVServer server = (VVServer) agent;
        server.sendOnNetworkBroadcastInAParty(this.getGameID(),new InterruptedGameData(this.getNickname()));
    }
}
