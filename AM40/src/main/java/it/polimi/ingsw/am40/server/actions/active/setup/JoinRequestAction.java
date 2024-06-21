package it.polimi.ingsw.am40.server.actions.active.setup;

import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.data.passive.flow.JoinResponseData;
import it.polimi.ingsw.am40.data.passive.flow.NoActivePartiesData;
import it.polimi.ingsw.am40.server.ActionAgent;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.network.virtual_view.NetworkParty;
import it.polimi.ingsw.am40.server.network.virtual_view.VVServer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JoinRequestAction extends Action {

    public JoinRequestAction(String nickname, int gameID, int playerID) {
        super("JOIN_GAME", nickname, gameID, playerID);
    }

    @Override
    public void doAction(ActionAgent agent){
        VVServer server = (VVServer) agent;
        List<NetworkParty> parties = server.getActiveParties();
        if (parties.isEmpty()) {
            server.sendOnNetworkUnicast(this.getPlayerID(), new NoActivePartiesData(this.getNickname()));
        } else {
            Map<Integer, ArrayList<Integer>> currentParties = new HashMap<>();
            for (NetworkParty p : parties) {
                ArrayList<Integer> numbers = new ArrayList<>();
                numbers.add(p.getCurrentNumOfClients());
                numbers.add(p.getTotalNumOfClients());
                currentParties.put(p.getPartyID(), numbers);
            }
            Data joinResponse = new JoinResponseData(this.getNickname(), currentParties);
            server.sendOnNetworkUnicast(this.getPlayerID(), joinResponse);
        }
    }

}
