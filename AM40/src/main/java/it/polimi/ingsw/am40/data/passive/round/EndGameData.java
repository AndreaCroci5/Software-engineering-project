package it.polimi.ingsw.am40.data.passive.round;

import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.Action;

import java.util.List;

@JsonTypeName("ENDGAME")
public class EndGameData extends Data {
    //ATTRIBUTES
    /** Names of the Players that won the Game in case of a tie. If the winner is only one, the List contains only one Player*/
    List<String> winners;

    //CONSTRUCTOR

    //Logic constructor for subclasses
    public EndGameData(List<String> winners) {
        super("ENDGAME");
        this.winners = winners;
    }
    //Json constructor
    public EndGameData(){

    }

    //PUBLIC METHODS


    public Action onServer(){
        return null;
    }

    public Message onClient() {
        return null;
    }
}
