package it.polimi.ingsw.am40.server.actions.active.setup;

import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.data.passive.flow.CreateResultData;
import it.polimi.ingsw.am40.data.passive.flow.FailedCreationData;
import it.polimi.ingsw.am40.server.ActionAgent;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.network.virtual_view.NonExistentClientException;
import it.polimi.ingsw.am40.server.network.virtual_view.VVServer;

public class CreateRequestAction extends Action {
    //ATTRIBUTES
    private  int numOfPlayers;

    public CreateRequestAction(String nickname, int gameID, int playerID, int numOfPlayers) {
        super("CREATE_GAME", nickname, gameID, playerID);
        this.numOfPlayers = numOfPlayers;
    }

    @Override
    public void doAction(ActionAgent agent){
        VVServer server = (VVServer) agent;
        try {
            server.createNewParty(numOfPlayers, server.getClientByID(this.getPlayerID()));
            server.sendOnNetworkUnicast(this.getPlayerID(), this.dataCreator());
        } catch (Exception e) {
            server.sendOnNetworkUnicast(this.getPlayerID(), new FailedCreationData(this.getNickname()));
        }
    }

    public Data dataCreator() {
        return new CreateResultData(this.getNickname(), this.numOfPlayers, 1);
    }
}
