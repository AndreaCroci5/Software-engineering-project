package it.polimi.ingsw.am40.server.actions.passive.firstRound;

import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.data.passive.firstRound.StartingCardInfoData;
import it.polimi.ingsw.am40.server.ActionAgent;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.network.virtual_view.VVServer;

public class StartingCardInfoAction extends Action {
    //ATTRIBUTES
    private int startingCardID;

    //CONSTRUCTOR
    /**
     * Constructor for StartingCardInfoAction
     */
    public StartingCardInfoAction(String nickname, int gameID, int playerID, int startingCardID){
        super("STARTING_CARD_INFO", nickname, gameID, playerID);
        this.startingCardID = startingCardID;
    }

    /**
     * Override of doAction for AimCard info transport
     * @param agent is the Game in which we perform the Action
     */
    @Override
    public void doAction(ActionAgent agent){
        VVServer v = (VVServer) agent;
        v.sendOnNetworkBroadcastInAParty(this.getGameID(), dataCreator());
    }

    public Data dataCreator() {
        return new StartingCardInfoData(this.getNickname(), this.startingCardID);
    }
}
