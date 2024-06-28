package it.polimi.ingsw.am40.server.actions.passive.firstRound;

import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.data.passive.firstRound.PlayersOrderInfoData;
import it.polimi.ingsw.am40.server.ActionAgent;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.network.virtual_view.VVServer;

import java.util.ArrayList;

/**
 * This class serves as a mean of the notification to the VirtualView which then will notify the client by using the Network interface
 * about the player order in the game
 */
public class PlayersOrderInfoAction extends Action {
    //ATTRIBUTES
    /**
     * This ArrayList contains the nicknames of the Players in a certain Game ordered by their respective Game order,
     * which establishes when each Player has the right to play his turn
     */
    private ArrayList<String> nicknames;

    //CONSTRUCTOR
    public PlayersOrderInfoAction(String nickname, int gameID, int playerID, ArrayList<String> nicknames){
        super("PLAYERS_ORDER_INFO", nickname, gameID, playerID);
        this.nicknames = nicknames;
    }

    //PUBLIC METHOD

    /**
     * Override of doAction for Player nicknames order info transport
     * @param agent is the VVServer from which the Action is transformed into a Data and sent
     */
    @Override
    public void doAction(ActionAgent agent){
        VVServer v = (VVServer) agent;
        v.sendOnNetworkBroadcastInAParty(this.getGameID(), dataCreator());
    }

    /**
     * Override of dataCreator for the creation of the respective result Data
     * @return a PlayersOrderInfoData
     */
    public Data dataCreator() {
        return new PlayersOrderInfoData(this.getNickname(), this.nicknames);
    }

}
