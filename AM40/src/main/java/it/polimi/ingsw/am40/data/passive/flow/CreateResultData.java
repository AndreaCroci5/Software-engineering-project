package it.polimi.ingsw.am40.data.passive.flow;

import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.Action;

@JsonTypeName("CREATE_RESULT")
public class CreateResultData extends Data {

    //Logic constructor for subclasses
    public CreateResultData(String nickname) {
        super("CREATE_RESULT", nickname);}
    //Json constructor
    public CreateResultData(){

    }

    //PUBLIC METHODS


    public Action onServer(){
        return null;
    }

    public Message onClient() {
        return ;
    }
}
