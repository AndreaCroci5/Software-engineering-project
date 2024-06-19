package it.polimi.ingsw.am40.server.actions.active.setup;

import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.data.passive.flow.FailedGameIDData;
import it.polimi.ingsw.am40.data.passive.flow.GameIDResultData;
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
        try {
            server.logClientInAParty(gameIDChoice, this.getPlayerID());
        } catch (Exception e) {
            server.sendOnNetworkUnicast(this.getPlayerID(), new FailedGameIDData(this.getNickname()));
        }
    }
}
