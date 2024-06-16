package it.polimi.ingsw.am40.data.active.firstRound;

import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.actions.active.firstRound.PlayersOrderRequestAction;


@JsonTypeName("PLAYERS_ORDER_REQUEST")
public class PlayersOrderRequestData extends Data {
    //CONSTRUCTOR
    //Logic and Json constructor for subclasses
    public PlayersOrderRequestData() {
        super("PLAYERS_ORDER_REQUEST");
    }

    //PUBLIC METHODS

    public Action onServer(){
        return new PlayersOrderRequestAction(this.getGameID(), this.getPlayerID());
    }

    public Message onClient() {
        return null;
    }
}
