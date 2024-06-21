package it.polimi.ingsw.am40.data.passive.firstRound;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.firstRound.TokenInfoMessage;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.Action;

import java.util.List;

@JsonTypeName("TOKEN_INFO")
public class TokenInfoData extends Data {
    //ATTRIBUTES
    private List<String> colors;

    //CONSTRUCTOR

    @JsonCreator
    public TokenInfoData(@JsonProperty("nickname") String nickname,
                         @JsonProperty("colors") List<String> colors) {
        super("TOKEN_INFO", nickname);
        this.colors = colors;
    }

    public List<String> getColors() {
        return colors;
    }

    public void setColors(List<String> colors) {
        this.colors = colors;
    }

    //PUBLIC METHODS


    public Action onServer(){
        return null;
    }

    public Message onClient() {
        return new TokenInfoMessage(this.getNickname(), this.colors);
    }

}
