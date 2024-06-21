package it.polimi.ingsw.am40.data.active.flow;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.actions.active.setup.ReadyToPlayAction;

@JsonTypeName("READY_TO_PLAY")
public class ReadyToPlayData extends Data {



    @JsonCreator
    public ReadyToPlayData(@JsonProperty("nickname") String nickname) {
        super("READY_TO_PLAY", nickname);
    }

    @Override
    public Action onServer() {
        return new ReadyToPlayAction(getDescription(), getNickname(), getGameID(), getPlayerID());
    }
}
