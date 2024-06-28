package it.polimi.ingsw.am40.server.actions.passive.firstRound;

import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.data.passive.firstRound.StartingCardInfoData;
import it.polimi.ingsw.am40.server.ActionAgent;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.network.virtual_view.VVServer;

public class StartingCardInfoAction extends Action {
    //ATTRIBUTES
    /**
     * ID of the StartingCard picked from the Player
     */
    private int startingCardID;

    //CONSTRUCTOR
    public StartingCardInfoAction(String nickname, int gameID, int playerID, int startingCardID){
        super("STARTING_CARD_INFO", nickname, gameID, playerID);
        this.startingCardID = startingCardID;
    }

    /**
     * Override of doAction for StartingCard info transport
     * @param agent is the VVServer from which the Action is transformed into a Data and sent
     */
    @Override
    public void doAction(ActionAgent agent){
        VVServer v = (VVServer) agent;
        v.sendOnNetworkBroadcastInAParty(this.getGameID(), dataCreator());
    }

    /**
     * Override of dataCreator for the creation of the respective result Data
     * @return a StartingCardInfoData
     */
    public Data dataCreator() {
        return new StartingCardInfoData(this.getNickname(), this.startingCardID);
    }
}
