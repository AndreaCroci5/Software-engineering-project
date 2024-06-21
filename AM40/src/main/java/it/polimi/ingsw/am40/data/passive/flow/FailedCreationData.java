package it.polimi.ingsw.am40.data.passive.flow;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.flow.FailedCreationMessage;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.Action;

@JsonTypeName("FAILED_CREATION")
public class FailedCreationData extends Data {
    //Json constructor
    @JsonCreator
    public FailedCreationData(@JsonProperty("nickname") String nickname) {
        super("FAILED_CREATION", nickname);
    }


    //PUBLIC METHODS


    public Action onServer(){
        return null;
    }

    public Message onClient() {
       return new FailedCreationMessage(this.getNickname());
    }
}
