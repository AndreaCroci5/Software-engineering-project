package it.polimi.ingsw.am40.data.passive.firstRound;

import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.Action;

//TODO Add card attributes for broadcasting

@JsonTypeName("POSITIVE_STARTING_CARD")
public class StartingCardResultData extends Data {
    //ATTRIBUTES
    /** Nickname of the Player that has just chosen the StartingCard CardFace*/
    String playerNickname;

    //CONSTRUCTOR

    //Logic constructor for subclasses
    public StartingCardResultData(String playerNickname) {
        super("POSITIVE_STARTING_CARD");
        this.playerNickname = playerNickname;
    }
    //Json constructor
    public StartingCardResultData(){

    }

    //PUBLIC METHODS


    public Action onServer(){
        return null;
    }

    public Message onClient() {
        return null;
    }
}
