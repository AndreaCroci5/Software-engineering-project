package it.polimi.ingsw.am40.data.passive.firstRound;

import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.Action;

@JsonTypeName("NEGATIVE_TOKEN_COLOR")
public class NegativeTokenColorData extends Data {
    //ATTRIBUTES
    /** Nickname of the Player that has just chosen the Token Color*/
    String playerNickname;

    //CONSTRUCTOR

    //Logic constructor for subclasses
    public NegativeTokenColorData(String playerNickname) {
        super("NEGATIVE_TOKEN_COLOR");
        this.playerNickname = playerNickname;
    }
    //Json constructor
    public NegativeTokenColorData(){

    }

    //PUBLIC METHODS


    public Action onServer(){
        return null;
    }

    public Message onClient() {
        return null;
    }
}
