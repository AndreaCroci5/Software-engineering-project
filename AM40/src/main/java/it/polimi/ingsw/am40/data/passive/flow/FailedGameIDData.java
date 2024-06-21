package it.polimi.ingsw.am40.data.passive.flow;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.flow.FailedGameIDMessage;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.Action;

@JsonTypeName("FAILED_GAME_ID")
public class FailedGameIDData extends Data {

    //Json constructor
    @JsonCreator
    public FailedGameIDData(@JsonProperty("nickname") String nickname) {
        super("FAILED_GAME_ID", nickname);
    }

    //PUBLIC METHODS


    public Action onServer(){
        return null;
    }

    public Message onClient() {
        return new FailedGameIDMessage(this.getNickname());
    }

}
