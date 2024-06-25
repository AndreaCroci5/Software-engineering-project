package it.polimi.ingsw.am40.data.active.firstRound;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.actions.active.firstRound.AimCardRequestAction;

@JsonTypeName("AIM_CARD_REQUEST")
public class AimCardRequestData extends Data {

    //CONSTRUCTOR

    @JsonCreator public AimCardRequestData (@JsonProperty("nickname") String nickname) {
        super("AIM_CARD_REQUEST", nickname);
    }

    //PUBLIC METHODS

    public Action onServer() {
        return new AimCardRequestAction(this.getNickname(), this.getGameID(), this.getPlayerID());
    }
}
