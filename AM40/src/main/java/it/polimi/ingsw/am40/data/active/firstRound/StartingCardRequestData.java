package it.polimi.ingsw.am40.data.active.firstRound;

import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.actions.active.firstRound.StartingCardRequestAction;

@JsonTypeName("STARTING_CARD_REQUEST")
public class StartingCardRequestData extends Data {

    //CONSTRUCTOR

    //Logic constructor for subclasses
    public StartingCardRequestData(String nickname) {
        super("STARTING_CARD_REQUEST", nickname);
    }
    //Json constructor
    public StartingCardRequestData() {

    }


    //PUBLIC METHODS

    public Action onServer(){
        return new StartingCardRequestAction(this.getNickname(), this.getGameID(), this.getPlayerID());
    }

    public Message onClient() {
        return null;
    }
}
