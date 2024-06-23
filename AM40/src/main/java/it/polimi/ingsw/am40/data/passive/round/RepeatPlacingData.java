package it.polimi.ingsw.am40.data.passive.round;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.round.RepeatPlacingMessage;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.Action;

@JsonTypeName("REPEAT_PLACING")
public class RepeatPlacingData extends Data {
    //ATTRIBUTES

    //CONSTRUCTOR

    @JsonCreator
    public RepeatPlacingData(@JsonProperty("nickname") String nickname){
        super("REPEAT_PLACING", nickname);
    }


    //PUBLIC METHODS


    public Action onServer(){
        return null;
    }

    public Message onClient() {
        return new RepeatPlacingMessage(this.getNickname());
    }
}
