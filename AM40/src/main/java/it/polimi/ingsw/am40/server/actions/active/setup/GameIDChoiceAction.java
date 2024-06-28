package it.polimi.ingsw.am40.server.actions.active.setup;

import it.polimi.ingsw.am40.data.passive.flow.FailedGameIDData;
import it.polimi.ingsw.am40.data.passive.flow.GameIDResultData;
import it.polimi.ingsw.am40.server.ActionAgent;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.network.virtual_view.NetworkParty;
import it.polimi.ingsw.am40.server.network.virtual_view.VVServer;

/**
 * This class represents the Action made by the Server when the respective Data is sent on the network carrying the
 * information of what party does the Client that sent the Data wants to join
 */
public class GameIDChoiceAction extends Action {
    //ATTRIBUTES
    /**
     * Number representing the ID of a party that a Client wants to join
     */
    private int gameIDChoice;


    //CONSTRUCTOR
    public GameIDChoiceAction(String nickname, int gameID, int playerID, int gameIDChoice) {
        super("GAME_ID_CHOICE", nickname, gameID, playerID);
        this.gameIDChoice = gameIDChoice;
    }

    /**
     * Override of doAction for the gameID choice
     * @param agent is the VVServer where we try to register as a new member of a party
     */
    @Override
    public void doAction(ActionAgent agent){
        VVServer server = (VVServer) agent;
        int currentNumOfPlayer = 0;
        int totalNumOfPlayer = 0;
        this.setGameID(this.gameIDChoice);

        //Check
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

            //Net notification
            server.sendOnNetworkBroadcastInAParty(this.gameIDChoice, new GameIDResultData(this.getNickname(),this.gameIDChoice, this.gameIDChoice, currentNumOfPlayer, totalNumOfPlayer));
        } catch (Exception e) {
            //Failed join through ID notification
            server.sendOnNetworkUnicast(this.getPlayerID(), new FailedGameIDData(this.getNickname()));
        }
    }
}
