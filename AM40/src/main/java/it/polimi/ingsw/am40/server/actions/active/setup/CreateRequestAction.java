package it.polimi.ingsw.am40.server.actions.active.setup;

import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.data.passive.flow.CreateResultData;
import it.polimi.ingsw.am40.data.passive.flow.FailedCreationData;
import it.polimi.ingsw.am40.server.ActionAgent;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.network.virtual_view.NonExistentClientException;
import it.polimi.ingsw.am40.server.network.virtual_view.VVServer;

/**
 * This class represents the Action made by the Server when the respective Data is sent on the network demanding the
 * creation of a new party of players
 */
public class CreateRequestAction extends Action {
    //ATTRIBUTES
    /**
     * Number representing the party size chosen by the Client (it has to be between 2 and 4)
     */
    private int numOfPlayers;

    //CONSTRUCTOR
    public CreateRequestAction(String nickname, int gameID, int playerID, int numOfPlayers) {
        super("CREATE_GAME", nickname, gameID, playerID);
        this.numOfPlayers = numOfPlayers;
    }

    /**
     * Override of doAction for the party creation request
     * @param agent is the VVServer where we register the newborn party
     */
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

    /**
     * Override of dataCreator for the creation of the respective result Data
     * @return CreateResultData as the respective result Data of this Action
     */
    @Override
    public Data dataCreator() {
        return new CreateResultData(this.getNickname(), this.numOfPlayers, 1);
    }
}
