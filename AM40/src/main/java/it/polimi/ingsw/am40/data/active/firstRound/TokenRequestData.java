package it.polimi.ingsw.am40.data.active.firstRound;

import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.actions.active.firstRound.TokenRequestAction;

@JsonTypeName("TOKEN_REQUEST")
public class TokenRequestData extends Data {

    //CONSTRUCTOR
    //Logic and Json constructor for subclasses
    public TokenRequestData() {
        super("TOKEN_REQUEST");
    }

    //PUBLIC METHODS

    public Action onServer() {
        return new TokenRequestAction(this.getGameID(),this.getPlayerID());
    }
}
