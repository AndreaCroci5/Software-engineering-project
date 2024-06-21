package it.polimi.ingsw.am40.data.passive.flow;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.flow.ChangeTurnResponseMessage;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.Action;

@JsonTypeName("CHANGE_TURN_INFO")
public class ChangeTurnInfoData extends Data {
    //ATTRIBUTES
    /** Nickname of the next Player that has the right to play*/
    private String nextActivePlayer;

    //CONSTRUCTOR

    //Json constructor
    @JsonCreator
    public ChangeTurnInfoData(@JsonProperty("nickname") String nickname,
                              @JsonProperty("nextActivePlayer") String nextActivePlayer) {
        super("CHANGE_TURN_INFO", nickname);
        this.nextActivePlayer = nextActivePlayer;
    }

    public String getNextActivePlayer() {
        return nextActivePlayer;
    }

    public void setNextActivePlayer(String nextActivePlayer) {
        this.nextActivePlayer = nextActivePlayer;
    }

    //PUBLIC METHODS


    public Action onServer(){
        return null;
    }

    public Message onClient() {
        return new ChangeTurnResponseMessage(this.nextActivePlayer);
    }
}
