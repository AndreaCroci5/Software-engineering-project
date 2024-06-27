package it.polimi.ingsw.am40.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.actions.ClientDisconnectedAction;

@JsonTypeName("CLIENT_DISCONNECTED")
public class ClientDisconnectedData extends Data{

    @JsonCreator
    public ClientDisconnectedData(@JsonProperty("nickname") String nickname) {
        super("CLIENT_DISCONNECTED", nickname);
    }

    public Action onServer() {
        return new ClientDisconnectedAction(this.getNickname(),this.getGameID(),this.getPlayerID());
    }
}
