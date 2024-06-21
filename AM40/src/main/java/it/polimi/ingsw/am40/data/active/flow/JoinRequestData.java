package it.polimi.ingsw.am40.data.active.flow;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.actions.active.setup.JoinRequestAction;

@JsonTypeName("JOIN_GAME")
public class JoinRequestData extends Data {

    private String ipAddress;

    private int port;

    public JoinRequestData(String nickname) {
        super("JOIN_GAME", nickname);
    }

    @JsonCreator
    public JoinRequestData(@JsonProperty("nickname") String nickname,
                           @JsonProperty("ipAddress") String ipAddress,
                           @JsonProperty("port") int port) {
        super("JOIN_GAME", nickname);
        this.ipAddress = ipAddress;
        this.port = port;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public Action onServer(){
        return new JoinRequestAction(this.getNickname(), this.getGameID(), this.getPlayerID());
    }
}
