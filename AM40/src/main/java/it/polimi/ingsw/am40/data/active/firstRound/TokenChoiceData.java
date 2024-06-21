package it.polimi.ingsw.am40.data.active.firstRound;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonCreator
    public TokenChoiceData(@JsonProperty("nickname") String nickname,
                           @JsonProperty("token") String token) {
        super("TOKEN_SELECTION", nickname);
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    //PUBLIC METHODS

    public Action onServer(){
        Color color = Color.valueOf(this.token.toUpperCase());
        return new TokenChoiceAction(this.getNickname(), this.getGameID(), this.getPlayerID(), color);
    }

    public Message onClient() {
        return null;
    }
}
