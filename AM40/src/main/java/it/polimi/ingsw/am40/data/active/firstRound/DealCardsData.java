package it.polimi.ingsw.am40.data.active.firstRound;

import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.data.Data;

@JsonTypeName("CARDS_DEAL")
public class DealCardsData extends Data {

    //CONSTRUCTOR

    //Logic constructor for subclasses
    public DealCardsData(String description) {
        super("CARDS_DEAL");
    }
    //Json constructor
    public  DealCardsData() {

    }

    //PUBLIC METHODS

    public Action onServer(){
        return null;
    }

    public Message onClient() {
        return null;
    }


}
