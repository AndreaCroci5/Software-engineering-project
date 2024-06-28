package it.polimi.ingsw.am40.server.actions.passive.firstRound;

import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.data.passive.firstRound.PositiveTokenColorData;
import it.polimi.ingsw.am40.server.ActionAgent;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.network.virtual_view.VVServer;

/**
 * This class serves as a mean to notify to the VirtualView which then will notify the client by using the Network interface
 * that the user has correctly selected his token color
 */
public class PositiveTokenColorAction extends Action {
    //COLOR CHOSEN
    /**
     * Color of the Token chosen by a Player
     */
    private final String color;
    //CONSTRUCTOR
    public PositiveTokenColorAction(String nickname, int gameID, int playerID, String color){
        super("POSITIVE_TOKEN_COLOR", nickname, gameID, playerID);
        this.color = color;
    }

    /**
     * Override of doAction for the positive Token selection
     * @param agent is the VVServer from which the Action is transformed into a Data and sent
     */
    @Override
    public void doAction(ActionAgent agent){
        VVServer v = (VVServer) agent;
        v.sendOnNetworkBroadcastInAParty(this.getGameID(), dataCreator());
    }

    /**
     * Override of dataCreator for the creation of the respective result Data
     * @return a PositiveTokenColorData
     */
    public Data dataCreator() {
        return new PositiveTokenColorData(this.getNickname(), this.color);
    }
}
