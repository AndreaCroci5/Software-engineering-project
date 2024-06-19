package it.polimi.ingsw.am40.data.passive.flow;

import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.Action;

@JsonTypeName("CREATE_RESULT")
public class CreateResultData extends Data {
    //ATTRIBUTES
    private int numOfPlayers;
    private int playersLogged;

    //Logic constructor for subclasses
    public CreateResultData(String nickname, int numOfPlayers, int playersLogged) {
        super("CREATE_RESULT", nickname);
        this.numOfPlayers = numOfPlayers;
        this.playersLogged = playersLogged;
    }
    //Json constructor
    public CreateResultData(){

    }

    //PUBLIC METHODS


    public Action onServer(){
        return null;
    }

    public Message onClient() {
        return null;
    }
}
