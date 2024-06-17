package it.polimi.ingsw.am40.data.passive.flow;

import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.flow.ChangeTurnResponseMessage;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.Action;

@JsonTypeName("CHANGE_TURN_INFO")
public class ChangeTurnInfoData extends Data {
    //ATTRIBUTES
    /** Nickname of the next Player that has the right to play*/
    private String nextActivePlayer;

    //CONSTRUCTOR

    //Logic constructor for subclasses
    public ChangeTurnInfoData(String nickname, String nextActivePlayer) {
        super("CHANGE_TURN_INFO", nickname);
        this.nextActivePlayer = nextActivePlayer;
    }
    //Json constructor
    public ChangeTurnInfoData(){

    }

    //PUBLIC METHODS


    public Action onServer(){
        return null;
    }

    public Message onClient() {
        return new ChangeTurnResponseMessage(this.nextActivePlayer);
    }
}
