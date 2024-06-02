package it.polimi.ingsw.am40.data.active.firstRound;

import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.actions.active.firstRound.StartingCardRequestAction;

//TODO Add attributes
@JsonTypeName("STARTING_CARD_REQUEST")
public class StartingCardRequestData extends Data {

    //CONSTRUCTOR

    //Logic constructor for subclasses and Json constructor
    public StartingCardRequestData() {
        super("STARTING_CARD_REQUEST");
    }


    //PUBLIC METHODS

    public Action onServer(){
        return new StartingCardRequestAction(this.getGameID(), this.getPlayerID());
    }

    public Message onClient() {
        return null;
    }
}
