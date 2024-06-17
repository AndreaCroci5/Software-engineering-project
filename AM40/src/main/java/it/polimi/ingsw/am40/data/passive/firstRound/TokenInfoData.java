package it.polimi.ingsw.am40.data.passive.firstRound;

import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.Action;

import java.util.List;

@JsonTypeName("TOKEN_INFO")
public class TokenInfoData extends Data {
    //ATTRIBUTES
    private List<String> colors;

    //CONSTRUCTOR

    //Logic constructor for subclasses
    public TokenInfoData(String nickname, List<String> colors) {
        super("TOKEN_INFO", nickname);
        this.colors = colors;
    }
    //Json constructor
    public TokenInfoData(){

    }

    //PUBLIC METHODS


    public Action onServer(){
        return null;
    }

    public Message onClient() {
        return null;
    }

}
