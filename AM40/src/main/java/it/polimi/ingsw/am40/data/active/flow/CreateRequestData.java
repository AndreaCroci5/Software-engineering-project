package it.polimi.ingsw.am40.data.active.flow;

import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.data.Data;

@JsonTypeName("CREATE_GAME")
public class CreateRequestData extends Data {
    private  int numOfPlayers;

    public CreateRequestData(String nickname, int numOfPlayer) {
        super("CREATE_GAME", nickname);
        this.numOfPlayers = numOfPlayer;
    }

    public CreateRequestData() {

    }
}
