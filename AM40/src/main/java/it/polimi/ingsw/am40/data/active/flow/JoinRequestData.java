package it.polimi.ingsw.am40.data.active.flow;

import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.actions.active.setup.JoinRequestAction;

@JsonTypeName("JOIN_GAME")
public class JoinRequestData extends Data {

    public JoinRequestData(String nickname) {
        super("JOIN_GAME", nickname);
    }

    public JoinRequestData(){

    }

    @Override
    public Action onServer(){
        return new JoinRequestAction(this.getNickname(), this.getGameID(), this.getPlayerID());
    }
}
