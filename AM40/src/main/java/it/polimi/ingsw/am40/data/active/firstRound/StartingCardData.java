package it.polimi.ingsw.am40.data.active.firstRound;

import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.data.Data;

@JsonTypeName("STARTING_CARD_SELECTION")
public class StartingCardData extends Data {

    //CONSTRUCTOR

    //Logic constructor for subclasses and Json constructor
    public StartingCardData() {
        super("STARTING_CARD_SELECTION");
    }


    //PUBLIC METHODS

    public Action onServer(){
        return null;
    }

    public Message onClient() {
        return null;
    }
}
