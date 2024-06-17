package it.polimi.ingsw.am40.data.passive.flow;

import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.Action;

@JsonTypeName("LAST_ROUNDS")
public class LastRoundsInfoData extends Data {
    //ATTRIBUTES

    //CONSTRUCTOR

    //Logic constructor for subclasses
    public LastRoundsInfoData(String nickname) {
        super("LAST_ROUNDS", nickname);
    }
    //Json constructor
    public LastRoundsInfoData(){

    }

    //PUBLIC METHODS


    public Action onServer(){
        return null;
    }

    public Message onClient() {
        return null;
    }
}
