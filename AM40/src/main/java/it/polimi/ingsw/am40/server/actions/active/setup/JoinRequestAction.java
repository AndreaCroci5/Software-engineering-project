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

/**
 * This class represents the Action made by the Server when the respective Data is sent on the network carrying the
 * request made by a client to join a party.
 * This Action fetches all the parties on the server
 */
public class JoinRequestAction extends Action {

    //CONSTRUCTOR
    public JoinRequestAction(String nickname, int gameID, int playerID) {
        super("JOIN_GAME", nickname, gameID, playerID);
    }

    /**
     * Override of doAction for the join request
     * @param agent is the VVServer from where the Action fetch the IDs of the parties
     */
    @Override
    public void doAction(ActionAgent agent){
        VVServer server = (VVServer) agent;
        List<NetworkParty> parties = server.getActiveParties();
        //EmptinessCheck
        if (parties.isEmpty()) {
            server.sendOnNetworkUnicast(this.getPlayerID(), new NoActivePartiesData(this.getNickname()));
        } else {
            //Data fetch
            Map<Integer, ArrayList<Integer>> currentParties = new HashMap<>();
            for (NetworkParty p : parties) {
                ArrayList<Integer> numbers = new ArrayList<>();
                numbers.add(p.getCurrentNumOfClients());
                numbers.add(p.getTotalNumOfClients());
                currentParties.put(p.getPartyID(), numbers);
            }
            //Positive fetch net notification
            Data joinResponse = new JoinResponseData(this.getNickname(), currentParties);
            server.sendOnNetworkUnicast(this.getPlayerID(), joinResponse);
        }
    }

}
