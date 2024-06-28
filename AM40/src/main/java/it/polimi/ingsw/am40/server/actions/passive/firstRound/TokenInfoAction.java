package it.polimi.ingsw.am40.server.actions.passive.firstRound;

import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.data.passive.firstRound.TokenInfoData;
import it.polimi.ingsw.am40.server.ActionAgent;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.network.virtual_view.VVServer;

import java.util.List;

/**
 * This class serves as a mean to notify to the VirtualView which then will notify the client by using the Network interface
 * about the remaining Tokens
 */
public class TokenInfoAction extends Action {
    //ATTRIBUTES
    /**
     * Reference to the List containing the remaining colors from where a player can choose his Token
     */
    private final List<String> remainingColors;

    //CONSTRUCTOR
    public TokenInfoAction(String nickname, int gameID, int playerID, List<String> remainingColors){
        super("TOKEN_INFO", nickname, gameID, playerID);
        this.remainingColors = remainingColors;
    }

    /**
     * Override of doAction for Token info transport
     * @param agent is the VVServer from which the Action is transformed into a Data and sent
     */
    @Override
    public void doAction(ActionAgent agent){
        VVServer v = (VVServer) agent;
        v.sendOnNetworkBroadcastInAParty(this.getGameID(), dataCreator());
    }

    /**
     * Override of dataCreator for the creation of the respective result Data
     * @return a TokenInfoData
     */
    public Data dataCreator() {
        return new TokenInfoData(this.getNickname(), this.remainingColors);
    }
}
