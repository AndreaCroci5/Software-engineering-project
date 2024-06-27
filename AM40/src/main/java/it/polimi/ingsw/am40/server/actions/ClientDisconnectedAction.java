package it.polimi.ingsw.am40.server.actions;

import it.polimi.ingsw.am40.data.InterruptedGameData;
import it.polimi.ingsw.am40.server.ActionAgent;
import it.polimi.ingsw.am40.server.network.virtual_view.VVServer;

public class ClientDisconnectedAction extends Action {

    public ClientDisconnectedAction(String nickname, int gameID, int playerID) {
        super("CLIENT_DISCONNECTED", nickname, gameID, playerID);
    }

    public void doAction(ActionAgent agent) {
        VVServer server = (VVServer) agent;
        server.sendOnNetworkBroadcastInAParty(this.getGameID(),new InterruptedGameData(this.getNickname()));
    }
}
