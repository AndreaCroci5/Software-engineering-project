package it.polimi.ingsw.am40.data.active.round;

import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.model.CardFace;
import it.polimi.ingsw.am40.server.model.Coordinates;
import it.polimi.ingsw.am40.data.Data;

@JsonTypeName("PLACING")
public class PlacingData extends Data {
    //ATTRIBUTES for the Execution
    /** Selection of the handCard to place based on the Arraylist index*/
    int choice;
    /** Coordinates chosen by the Player for the Card to Place*/
    Coordinates coordsToPlace;
    /** CardFace orientation of the Card chosen to place by the Player*/
    CardFace face;

    public PlacingData(String description, int choice, Coordinates coordsToPlace, CardFace face) {
        super("PLACING");
        this.choice = choice;
        this.coordsToPlace = coordsToPlace;
        this.face = face;
    }


    //PUBLIC METHODS

    public Action onServer(){
        return null;
    }

    public Message onClient() {
        return null;
    }
}
