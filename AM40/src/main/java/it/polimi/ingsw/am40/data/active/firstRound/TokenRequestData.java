package it.polimi.ingsw.am40.data.active.firstRound;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.actions.active.firstRound.TokenRequestAction;

@JsonTypeName("TOKEN_REQUEST")
public class TokenRequestData extends Data {

    //CONSTRUCTOR

    @JsonCreator
    public TokenRequestData(@JsonProperty("nickname") String nickname) {
        super("TOKEN_REQUEST", nickname);
    }

    //PUBLIC METHODS

    public Action onServer() {
        return new TokenRequestAction(this.getNickname(), this.getGameID(),this.getPlayerID());
    }
}
