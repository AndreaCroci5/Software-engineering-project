package it.polimi.ingsw.am40.data.active.flow;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.actions.active.firstRound.AimCardChoiceAction;
import it.polimi.ingsw.am40.server.actions.active.setup.CreateRequestAction;

@JsonTypeName("CREATE_GAME")
public class CreateRequestData extends Data {

    private  int numOfPlayers;

    private String ipAddress;

    private int port;

    public int getNumOfPlayers() {
        return numOfPlayers;
    }

    public void setNumOfPlayers(int numOfPlayers) {
        this.numOfPlayers = numOfPlayers;
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

    //Json constructor
    @JsonCreator
    public CreateRequestData(@JsonProperty("nickname") String nickname,
                             @JsonProperty("num_of_player") int numOfPlayers,
                             @JsonProperty("ipAddress") String ipAddress,
                             @JsonProperty("port") int port) {
        super("CREATE_GAME",nickname);
        this.numOfPlayers = numOfPlayers;
        this.ipAddress = ipAddress;
        this.port = port;
    }

    @Override
    public Action onServer(){
        return new CreateRequestAction(this.getNickname(),this.getGameID(), this.getPlayerID(), this.numOfPlayers);
    }
}
