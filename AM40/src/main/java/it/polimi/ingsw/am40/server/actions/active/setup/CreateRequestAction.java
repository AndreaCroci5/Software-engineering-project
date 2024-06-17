package it.polimi.ingsw.am40.server.actions.active.setup;

import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.data.passive.flow.CreateResultData;
import it.polimi.ingsw.am40.server.ActionAgent;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.network.virtual_view.VVServer;

public class CreateRequestAction extends Action {
    //FIXME add num of players
    public CreateRequestAction(String nickname, int gameID, int playerID) {
        super("CREATE_GAME", nickname, gameID, playerID);
    }

    @Override
    public void doAction(ActionAgent agent){
        VVServer net = (VVServer) agent;
        net.sendOnNetworkUnicast(this.getPlayerID(), this.dataCreator());
    }

    public Data dataCreator() {
        return new CreateResultData(this.getNickname());
    }
}
