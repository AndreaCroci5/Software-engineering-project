package it.polimi.ingsw.am40.data.active.firstRound;

import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.active.firstRound.TokenChoiceAction;
import it.polimi.ingsw.am40.server.model.Color;

@JsonTypeName("TOKEN_SELECTION")
public class TokenChoiceData extends Data {

    private String token;

    //CONSTRUCTOR

    //Logic constructor for subclasses
    public TokenChoiceData(String token) {
        super("TOKEN_SELECTION");
        this.token = token;
    }

    //Json constructor
    public TokenChoiceData() {

    }

    //PUBLIC METHODS

    public Action onServer(){
        Color color = Color.valueOf(this.token);
        return new TokenChoiceAction(this.getGameID(), this.getPlayerID(), color);
    }

    public Message onClient() {
        return null;
    }
}
