package it.polimi.ingsw.am40.data.active.firstRound;

import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.active.firstRound.DealCardsRequestAction;

//FIXME Rename by adding request between cards and data in class name A+N
@JsonTypeName("CARDS_DEAL")
public class DealCardsData extends Data {

    //CONSTRUCTOR
    //Logic constructor and Json constructor for subclasses
    public  DealCardsData() {
        super("CARDS_DEAL");
    }

    //PUBLIC METHODS

    public Action onServer(){
        return new DealCardsRequestAction(this.getGameID(), this.getPlayerID());
    }

    public Message onClient() {
        return null;
    }


}
