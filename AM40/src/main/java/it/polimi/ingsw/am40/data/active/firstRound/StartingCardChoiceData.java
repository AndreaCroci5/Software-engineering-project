package it.polimi.ingsw.am40.data.active.firstRound;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.actions.active.firstRound.StartingCardChoiceAction;
import it.polimi.ingsw.am40.server.model.CardFace;

@JsonTypeName("STARTING_CARD_CHOICE")
public class StartingCardChoiceData extends Data {
    //ATTRIBUTES
    private  String face;

    //CONSTRUCTOR

    @JsonCreator
    public StartingCardChoiceData(@JsonProperty("nickname") String nickname,
                                  @JsonProperty("face") String face) {
        super("STARTING_CARD_CHOICE", nickname);
        this.face = face;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    //PUBLIC METHODS

    public Action onServer(){
        return new StartingCardChoiceAction(this.getNickname(), this.getGameID(), this.getPlayerID(), CardFace.valueOf(this.face.toUpperCase()));
    }

    public Message onClient() {
        return null;
    }
}
