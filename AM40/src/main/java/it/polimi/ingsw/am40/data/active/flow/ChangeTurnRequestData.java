package it.polimi.ingsw.am40.data.active.flow;

import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.actions.active.flow.ChangeTurnRequestAction;

//FIXME make unique constructor A+N
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
        return new ChangeTurnRequestAction(this.getGameID(), this.getPlayerID());
    }

    public Message onClient() {
        return null;
    }
}
