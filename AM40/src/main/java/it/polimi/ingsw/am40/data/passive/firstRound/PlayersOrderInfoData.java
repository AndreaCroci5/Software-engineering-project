package it.polimi.ingsw.am40.data.passive.firstRound;

import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.Action;

import java.util.ArrayList;

@JsonTypeName("PLAYERS_ORDER_INFO")
public class PlayersOrderInfoData extends Data {
    //ATTRIBUTES
    private ArrayList<String> players;

    //CONSTRUCTOR
    //Logic constructor for subclasses
    public PlayersOrderInfoData(ArrayList<String> players) {
        super("PLAYERS_ORDER_INFO");
        this.players = players;
    }


    //PUBLIC METHODS

    public Action onServer(){
        return null;
    }

    public Message onClient() {
        return null;
    }
}
