package it.polimi.ingsw.am40.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.actions.ClientDisconnectedAction;

/**
 * This data is sent by the client when he quit the game
 */
@JsonTypeName("CLIENT_DISCONNECTED")
public class ClientDisconnectedData extends Data{

    /**
     * Constructor for the ClientDisconnectedData
     * @param nickname is the name of the active client
     */
    @JsonCreator
    public ClientDisconnectedData(@JsonProperty("nickname") String nickname) {
        super("CLIENT_DISCONNECTED", nickname);
    }

    /**
     * This method is called once the Data reaches the Server and creates the Action related to the Data sent by polymorphism
     * @return the corresponding Action on the Server
     */
    public Action onServer() {
        return new ClientDisconnectedAction(this.getNickname(),this.getGameID(),this.getPlayerID());
    }
}
