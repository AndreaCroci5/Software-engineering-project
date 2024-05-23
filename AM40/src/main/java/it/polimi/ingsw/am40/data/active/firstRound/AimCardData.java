package it.polimi.ingsw.am40.data.active.firstRound;

import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.data.Data;

public class AimCardData extends Data {
    //ATTRIBUTES
    /** This attribute represents one the AimCard chosen by the Client: (0) the first, (1) the second*/
    int choice;

    //CONSTRUCTOR
    public AimCardData(String description, int choice) {
        super("AIM_CARD_SELECTION");
        this.choice = choice;
    }

    //PUBLIC METHODS

    public Action onServer(){
        return null;
    }

    public Message onClient() {
        return null;
    }
}
