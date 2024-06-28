package it.polimi.ingsw.am40.data.active.round;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.network.RMI.RemoteInterfaceClient;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.actions.active.round.PlacingAction;
import it.polimi.ingsw.am40.server.model.CardFace;
import it.polimi.ingsw.am40.server.model.Coordinates;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.network.RMI.RemoteInterfaceServer;

import java.rmi.RemoteException;

/**
 * This class contains the information that will be carried by being sent on the network.
 * This class is also the bridge from PlacingMessage on the Client to the PlacingAction on Server
 */
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

    @JsonCreator
    public PlacingData(@JsonProperty("nickname") String nickname,
                       @JsonProperty("choice") int choice,
                       @JsonProperty("Coordinates") Coordinates coordinates,
                       @JsonProperty("face") String face) {
        super("PLACING", nickname);
        this.choice = choice;
        this.coordsToPlace = coordinates;
        this.face = face;
    }

    public int getChoice() {
        return choice;
    }

    public void setChoice(int choice) {
        this.choice = choice;
    }

    public Coordinates getCoordsToPlace() {
        return coordsToPlace;
    }

    public void setCoordsToPlace(Coordinates coordsToPlace) {
        this.coordsToPlace = coordsToPlace;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    //PUBLIC METHODS

    /**
     * Override of the method onServer that returns the related PlacingAction on the Server
     * @return a PlacingAction
     */
    @Override
    public Action onServer(){
        return new PlacingAction(this.getNickname(), this.getGameID(), this.getPlayerID(), this.choice, this.coordsToPlace, CardFace.valueOf(this.face.toUpperCase()));
    }

    public Message onClient() {
        return null;
    }

    /**
     * Method which calls the right RMI interface method for each data (with override)
     * @param skeleton the client remote interface. Null if data active
     * @param stub the server remote interface. Null if data passive
     */
    @Override
    public synchronized void doRMI(RemoteInterfaceClient skeleton, RemoteInterfaceServer stub){
        try {
            stub.placingActiveRound(this);
        } catch (RemoteException e) {
            System.out.println("RMI call went wrong! message: " + e);
        }
    }
}
