package it.polimi.ingsw.am40.data.passive.flow;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.flow.LastRoundInfoMessage;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.Action;

@JsonTypeName("LAST_ROUNDS")
public class LastRoundsInfoData extends Data {
    //ATTRIBUTES

    //CONSTRUCTOR

    @JsonCreator
    public LastRoundsInfoData(@JsonProperty("nickname") String nickname) {
        super("LAST_ROUNDS", nickname);
    }

    //PUBLIC METHODS


    public Action onServer(){
        return null;
    }

    public Message onClient() {
        return new LastRoundInfoMessage(this.getNickname());
    }
}
