package it.polimi.ingsw.am40.server.actions.active.setup;

import it.polimi.ingsw.am40.data.passive.flow.NotEnoughPlayersData;
import it.polimi.ingsw.am40.server.ActionAgent;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.actions.active.firstRound.InitializationAction;
import it.polimi.ingsw.am40.server.network.virtual_view.NetworkClient;
import it.polimi.ingsw.am40.server.network.virtual_view.NetworkParty;
import it.polimi.ingsw.am40.server.network.virtual_view.VVServer;

import java.util.ArrayList;

public class ReadyToPlayAction extends Action {


    public ReadyToPlayAction(String description, String nickname, int gameID, int playerID) {
        super("READY_TO_PLAY", nickname, gameID, playerID);
    }

    @Override
    public void doAction(ActionAgent agent) {
        VVServer server = (VVServer) agent;
        int partyID = -1;

        for (NetworkParty p : server.getActiveParties()) {
            for (NetworkClient c : p.getClients()) {
                if (c.getClientID() == this.getPlayerID()) {
                    // GameID/PartyID fetch
                    partyID = p.getPartyID();
                }
            }
        }

        //CHECK for the game init
        try {
            int currentPlayers = server.getPartyByID(partyID).getCurrentNumOfClients();
            int totalPlayers = server.getPartyByID(partyID).getTotalNumOfClients();
            if (currentPlayers == totalPlayers) {
                //Information collection
                ArrayList<String> nicknames = new ArrayList<>();
                for (NetworkClient nC : server.getPartyByID(partyID).getClients()) {
                    nicknames.add(nC.getUsername());
                }
                server.notifyListeners(new InitializationAction(this.getNickname(),partyID, this.getPlayerID(), nicknames, server), server.getListeners());
            } else {
                server.sendOnNetworkBroadcastInAParty(partyID, new NotEnoughPlayersData(this.getNickname()));
            }
        }catch (Exception e) {

        }

    }
}
