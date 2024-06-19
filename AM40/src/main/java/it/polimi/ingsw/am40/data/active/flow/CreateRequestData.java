package it.polimi.ingsw.am40.data.active.flow;

import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.actions.active.firstRound.AimCardChoiceAction;
import it.polimi.ingsw.am40.server.actions.active.setup.CreateRequestAction;

@JsonTypeName("CREATE_GAME")
public class CreateRequestData extends Data {
    private  int numOfPlayers;

    public CreateRequestData(String nickname, int numOfPlayer) {
        super("CREATE_GAME", nickname);
        this.numOfPlayers = numOfPlayer;
    }

    public CreateRequestData() {

    }

    @Override
    public Action onServer(){
        return new CreateRequestAction(this.getNickname(),this.getGameID(), this.getPlayerID(), this.numOfPlayers);
    }
}
