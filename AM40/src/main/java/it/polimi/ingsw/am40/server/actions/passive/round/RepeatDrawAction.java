package it.polimi.ingsw.am40.server.actions.passive.round;

import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.data.passive.round.RepeatDrawData;
import it.polimi.ingsw.am40.server.ActionAgent;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.network.virtual_view.VVServer;

/**
 * This class serves as a mean to notify to the VirtualView which then will notify the client by using the Network interface.
 * This Action is usually called by an Exception handling when a Player tries to draw a Card from an empty plate or Deck
 */
public class RepeatDrawAction extends Action {

    //CONSTRUCTOR
    /**
     * Constructor for Negative Draw action response
     */
    public RepeatDrawAction(String nickname, int gameID, int playerID){
        super("REPEAT_DRAW", nickname, gameID, playerID);
    }


    @Override
    public void doAction(ActionAgent agent){
        VVServer v = (VVServer) agent;
        v.sendOnNetworkBroadcastInAParty(this.getGameID(), dataCreator());
    }

    public Data dataCreator() {
        return new RepeatDrawData(this.getNickname());
    }
}
