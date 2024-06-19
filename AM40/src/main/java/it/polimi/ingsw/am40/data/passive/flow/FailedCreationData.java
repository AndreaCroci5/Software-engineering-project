package it.polimi.ingsw.am40.data.passive.flow;

import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.Action;

@JsonTypeName("FAILED_CREATION")
public class FailedCreationData extends Data {
    public FailedCreationData(String nickname) {
        super("FAILED_CREATION", nickname);
    }
    //Json constructor
    public FailedCreationData(){

    }

    //PUBLIC METHODS


    public Action onServer(){
        return null;
    }

    public Message onClient() {
        return null;
    }
}
