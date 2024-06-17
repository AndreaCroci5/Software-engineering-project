package it.polimi.ingsw.am40.data.active.flow;

import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.data.Data;

@JsonTypeName("JOIN_GAME")
public class JoinRequestData extends Data {

    public JoinRequestData(String nickname) {
        super("JOIN_GAME", nickname);
    }

    public JoinRequestData(){

    }
}
