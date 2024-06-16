package it.polimi.ingsw.am40.data.passive.round;

import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.Action;

@JsonTypeName("REPEAT_PLACING")
public class RepeatPlacingData extends Data {
    //ATTRIBUTES
    /** Nickname of the Player that has just made the draw*/
    private String playerNickname;

    //CONSTRUCTOR

    //Logic constructor for subclasses
    public RepeatPlacingData(String playerNickname){
        super("REPEAT_PLACING");
        this.playerNickname = playerNickname;
    }
    //Json constructor
    public RepeatPlacingData(){

    }


    //PUBLIC METHODS


    public Action onServer(){
        return null;
    }

    public Message onClient() {
        return null;
    }
}
