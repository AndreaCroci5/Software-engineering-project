package it.polimi.ingsw.am40.server.actions.active.setup;

import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.data.passive.flow.FailedGameIDData;
import it.polimi.ingsw.am40.data.passive.flow.GameIDResultData;
import it.polimi.ingsw.am40.data.passive.flow.JoinResponseData;
import it.polimi.ingsw.am40.data.passive.flow.NoActivePartiesData;
import it.polimi.ingsw.am40.server.ActionAgent;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.network.virtual_view.NetworkClient;
import it.polimi.ingsw.am40.server.network.virtual_view.NetworkParty;
import it.polimi.ingsw.am40.server.network.virtual_view.VVServer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameIDChoiceAction extends Action {
    //ATTRIBUTES
    private int gameIDChoice;

    public GameIDChoiceAction(String nickname, int gameID, int playerID, int gameIDChoice) {
        super("GAME_ID_CHOICE", nickname, gameID, playerID);
        this.gameIDChoice = gameIDChoice;
    }

    @Override
    public void doAction(ActionAgent agent){
        VVServer server = (VVServer) agent;
        int currentNumOfPlayer = 0;
        int totalNumOfPlayer = 0;
        this.setGameID(this.gameIDChoice);

        try {
            for (NetworkParty p : server.getActiveParties()) {
                if (p.getPartyID() == gameIDChoice) {
                    if (p.getCurrentNumOfClients() == p.getTotalNumOfClients()) {
                        throw new Exception();
                    } else {
                        server.logClientInAParty(gameIDChoice, this.getPlayerID());
                    }
                    currentNumOfPlayer = p.getCurrentNumOfClients();
                    totalNumOfPlayer = p.getTotalNumOfClients();
                }
            }

            server.sendOnNetworkBroadcastInAParty(this.gameIDChoice, new GameIDResultData(this.getNickname(),this.gameIDChoice, this.gameIDChoice, currentNumOfPlayer, totalNumOfPlayer));
        } catch (Exception e) {
            server.sendOnNetworkUnicast(this.getPlayerID(), new FailedGameIDData(this.getNickname()));
        }
    }
}
