package it.polimi.ingsw.am40.data.active.firstRound;

import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.actions.active.firstRound.StartingCardChoiceAction;
import it.polimi.ingsw.am40.server.model.CardFace;

@JsonTypeName("STARTING_CARD_CHOICE")
public class StartingCardChoiceData extends Data {
    //ATTRIBUTES
    private final String face;

    //CONSTRUCTOR
    //Logic constructor for subclasses
    public StartingCardChoiceData(String nickname, String face) {
        super("STARTING_CARD_CHOICE", nickname);
        this.face = face;
    }
    //Json constructor
    public StartingCardChoiceData(){

    }


    //PUBLIC METHODS

    public Action onServer(){
        return new StartingCardChoiceAction(this.getNickname(), this.getGameID(), this.getPlayerID(), CardFace.valueOf(this.face.toUpperCase()));
    }

    public Message onClient() {
        return null;
    }
}
