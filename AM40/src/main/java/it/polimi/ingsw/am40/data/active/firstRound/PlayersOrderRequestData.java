package it.polimi.ingsw.am40.data.active.firstRound;

import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.data.Data;

@JsonTypeName("PLAYERS_ORDER_REQUEST")
public class PlayersOrderRequestData extends Data {
    //FIXME constructor in all requests
    //CONSTRUCTOR
    //Logic and Json constructor for subclasses
    public PlayersOrderRequestData() {
        super("PLAYERS_ORDER_REQUEST");
    }

    //PUBLIC METHODS
    //TODO Add Action on Server and add implementation of onServer()
}
