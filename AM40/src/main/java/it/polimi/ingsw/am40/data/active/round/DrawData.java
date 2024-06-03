package it.polimi.ingsw.am40.data.active.round;

import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.active.round.DrawAction;

@JsonTypeName("DRAW")
public class DrawData extends Data {
    //ATTRIBUTES
    /** Choice of which type of Card to draw from the CommonBoard: resource(0) or golden(1)*/
    private String choice;
    /** Possible selection on the CommonBoard: the two cards on plate(0), plate(1); or deck(2)*/
    private int selection;

    //CONSTRUCTOR

    //Logic constructor for subclasses
    public DrawData(String choice, int selection) {
        super("DRAW");
        this.choice = choice;
        this.selection = selection;
    }
    //Json constructor
    public DrawData() {
    }


    //PUBLIC METHODS

    public Action onServer(){
        //String parse to an Integer
        int source;
        if (this.choice.equalsIgnoreCase("res")){
            source = 0;
        } else {
            source = 1;
        }
        return new DrawAction(this.getGameID(), this.getPlayerID(), source, selection);
    }

    public Message onClient() {
        return null;
    }
}
