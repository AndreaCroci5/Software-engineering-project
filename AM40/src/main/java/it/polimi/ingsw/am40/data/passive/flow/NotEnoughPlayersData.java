package it.polimi.ingsw.am40.data.passive.flow;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.flow.NotEnoughPlayerMessage;
import it.polimi.ingsw.am40.data.Data;

@JsonTypeName("NOT_ENOUGH_PLAYERS")
public class NotEnoughPlayersData extends Data {

    @JsonCreator
    public NotEnoughPlayersData(@JsonProperty ("nickname") String nickname) {
        super("NOT_ENOUGH_PLAYERS",nickname);
    }

    public Message onClient() {
        return new NotEnoughPlayerMessage(this.getNickname());
    }
}
