package it.polimi.ingsw.am40.data.passive.firstRound;

import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.firstRound.PositiveTokenColorMessage;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.Action;

@JsonTypeName("POSITIVE_TOKEN_COLOR")
public class PositiveTokenColorData extends Data {
    //ATTRIBUTES
    /** Color of the Token chosen by the Player*/
    private String color;



    //CONSTRUCTOR

    //Logic constructor for subclasses
    public PositiveTokenColorData(String nickname, String color) {
        super("POSITIVE_TOKEN_COLOR", nickname);
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
        return new PositiveTokenColorMessage(this.getNickname(), this.color);
    }
}
