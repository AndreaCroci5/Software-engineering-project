package it.polimi.ingsw.am40.server.actions.passive.setup;

import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.data.passive.flow.GameInitResultData;
import it.polimi.ingsw.am40.server.ActionAgent;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.network.virtual_view.VVServer;

import java.util.ArrayList;
import java.util.Map;

public class GameInitResultAction extends Action {
    //ATTRIBUTES
    private ArrayList<String> nicknames;

    /**
     * Reference to a Map containing as Keys the name of the Type of Cards that the Values Represent: "RESOURCE", "GOLDEN", "AIM".
     * The values represent an ArrayList of Integers, whose element represent the card ID and the index the position on the CommonBoard:
     * indexes: (0) plate first card, (1) plate second card, (2) deck card
     */
    private Map<String, ArrayList<Integer>> commonboard;

    public GameInitResultAction(String nickname, int gameID, int playerID, ArrayList<String> nicknames, Map<String, ArrayList<Integer>> commonboard) {
        super("GAME_INIT_RESULT", nickname, gameID, playerID);
        this.nicknames = nicknames;
        this.commonboard = commonboard;
    }

    @Override
    public Data dataCreator() {
        return new GameInitResultData(this.getNickname(),this.nicknames,this.commonboard);
    }

    @Override
    public void doAction(ActionAgent agent) {
        VVServer v = (VVServer) agent;
        v.sendOnNetworkBroadcastInAParty(this.getGameID(),dataCreator());
    }
}
