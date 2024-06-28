package it.polimi.ingsw.am40.data.active.firstRound;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.network.RMI.RemoteInterfaceClient;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.actions.active.firstRound.StartingCardChoiceAction;
import it.polimi.ingsw.am40.server.model.CardFace;
import it.polimi.ingsw.am40.server.network.RMI.RemoteInterfaceServer;

import java.rmi.RemoteException;

/**
 * This class contains the information that will be carried by being sent on the network.
 * This class is also the bridge from StartingCardChoiceMessage on the Client to the StartingCardChoiceAction on Server
 */
@JsonTypeName("STARTING_CARD_CHOICE")
public class StartingCardChoiceData extends Data {
    //ATTRIBUTES
    /**
     * Face of the StartingCard chosen by the Player
     */
    private String face;

    //CONSTRUCTOR

    @JsonCreator
    public StartingCardChoiceData(@JsonProperty("nickname") String nickname,
                                  @JsonProperty("face") String face) {
        super("STARTING_CARD_CHOICE", nickname);
        this.face = face;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    //PUBLIC METHODS

    /**
     * Override of the method onServer that returns the related StartingCardChoiceAction on the Server
     * @return a StartingCardChoiceAction
     */
    @Override
    public Action onServer(){
        return new StartingCardChoiceAction(this.getNickname(), this.getGameID(), this.getPlayerID(), CardFace.valueOf(this.face.toUpperCase()));
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
            stub.choiceStartingCardActiveFirstRound(this);
        } catch (RemoteException e) {
            System.out.println("RMI call went wrong! message: " + e);
        }
    }
}
