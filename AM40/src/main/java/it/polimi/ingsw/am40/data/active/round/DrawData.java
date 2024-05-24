package it.polimi.ingsw.am40.data.active.round;

import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.data.Data;

@JsonTypeName("DRAW")
public class DrawData extends Data {
    //ATTRIBUTES
    /** Choice of which type of Card to draw from the CommonBoard: resource(0) or golden(1)*/
    int choice;
    /** Possible selection on the CommonBoard: the two cards on plate(0), plate(1); or deck(2)*/
    int selection;

    //CONSTRUCTOR

    //Logic constructor for subclasses
    public DrawData(String description, int choice, int selection) {
        super("DRAW");
        this.choice = choice;
        this.selection = selection;
    }
    //Json constructor
    public DrawData() {

    }


    //PUBLIC METHODS

    public Action onServer(){
        return null;
    }

    public Message onClient() {
        return null;
    }
}
