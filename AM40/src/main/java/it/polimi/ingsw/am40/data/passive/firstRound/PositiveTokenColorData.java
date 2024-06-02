package it.polimi.ingsw.am40.data.passive.firstRound;

import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.Action;

@JsonTypeName("POSITIVE_TOKEN_COLOR")
public class PositiveTokenColorData extends Data {
    //ATTRIBUTES
    /** Nickname of the Player that has just chosen the Token Color*/
    private String playerNickname;

    /** Color of the Token chosen by the Player*/
    private String color;



    //CONSTRUCTOR

    //Logic constructor for subclasses
    public PositiveTokenColorData(String playerNickname, String color) {
        super("POSITIVE_TOKEN_COLOR");
        this.playerNickname = playerNickname;
        this.color = color;
    }
    //Json constructor
    public PositiveTokenColorData(){

    }

    //PUBLIC METHODS


    public Action onServer(){
        return null;
    }

    public Message onClient() {
        return null;
    }
}
