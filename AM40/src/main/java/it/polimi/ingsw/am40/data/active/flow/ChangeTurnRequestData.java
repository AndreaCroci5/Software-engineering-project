package it.polimi.ingsw.am40.data.active.flow;

import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.Action;

@JsonTypeName("CHANGE_TURN")
public class ChangeTurnRequestData extends Data {
    //CONSTRUCTOR

    //Logic constructor for subclasses
    public ChangeTurnRequestData(String description) {
        super("CHANGE_TURN");
    }
    //Json constructor
    public ChangeTurnRequestData() {

    }

    //PUBLIC METHODS

    public Action onServer(){
        return null;
    }

    public Message onClient() {
        return null;
    }
}
