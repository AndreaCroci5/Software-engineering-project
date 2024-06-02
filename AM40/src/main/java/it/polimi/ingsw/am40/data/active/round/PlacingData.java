package it.polimi.ingsw.am40.data.active.round;

import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.actions.active.round.PlacingAction;
import it.polimi.ingsw.am40.server.model.CardFace;
import it.polimi.ingsw.am40.server.model.Coordinates;
import it.polimi.ingsw.am40.data.Data;

//FIXME CardFace must be in MAIUSC. A+N
@JsonTypeName("PLACING")
public class PlacingData extends Data {
    //ATTRIBUTES for the Execution
    /** Selection of the handCard to place based on the Arraylist index*/
    private int choice;
    /** Coordinates chosen by the Player for the Card to Place*/
    private Coordinates coordsToPlace;
    /** CardFace orientation of the Card chosen to place by the Player*/
    private String face;

    //CONSTRUCTOR

    //Logic constructor for subclasses
    public PlacingData(int choice, Coordinates coordsToPlace, String face) {
        super("PLACING");
        this.choice = choice;
        this.coordsToPlace = coordsToPlace;
        this.face = face;
    }

    //Json constructor
    public PlacingData() {

    }


    //PUBLIC METHODS

    public Action onServer(){

        return new PlacingAction(this.getGameID(), this.getPlayerID(), this.choice, this.coordsToPlace, CardFace.valueOf(this.face));
    }

    public Message onClient() {
        return null;
    }
}
