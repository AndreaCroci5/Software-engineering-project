package it.polimi.ingsw.am40.data;

import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.server.actions.Action;

@JsonTypeName("PING")
public class PingData extends Data{
    //FIXME *
    //Json constructor
    public PingData(){
        super("PING", "PING_USER");
    }

    //PUBLIC METHODS


    public Action onServer(){
        return null;
    }

    public Message onClient() {
        return null;
    }
}
