package it.polimi.ingsw.am40.data.passive.round;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.round.RepeatDrawMessage;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.Action;

@JsonTypeName("REPEAT_DRAW")
public class RepeatDrawData extends Data {
    //ATTRIBUTES

    //CONSTRUCTOR

    @JsonCreator
    public RepeatDrawData(@JsonProperty("nickname") String nickname) {
        super("REPEAT_DRAW", nickname);
    }


    //PUBLIC METHODS

    public Action onServer(){
        return null;
    }

    public Message onClient() {
        return new RepeatDrawMessage(this.getNickname());
    }
}
