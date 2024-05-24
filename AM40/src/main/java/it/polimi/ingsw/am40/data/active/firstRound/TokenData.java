package it.polimi.ingsw.am40.data.active.firstRound;

import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.data.Data;

@JsonTypeName("TOKEN_SELECTION")
public class TokenData extends Data {

    //CONSTRUCTOR

    //Logic constructor for subclasses
    public TokenData(String description) {
        super("TOKEN_SELECTION");
    }
    //Json constructor
    public TokenData() {

    }

    //PUBLIC METHODS

    public Action onServer(){
        return null;
    }

    public Message onClient() {
        return null;
    }
}
