package it.polimi.ingsw.am40.data.passive.firstRound;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.firstRound.StartingCardResultMessage;
import it.polimi.ingsw.am40.client.network.RMI.RemoteInterfaceClient;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.model.CardElements;
import it.polimi.ingsw.am40.server.model.Coordinates;
import it.polimi.ingsw.am40.server.network.RMI.RemoteInterfaceServer;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Map;


@JsonTypeName("POSITIVE_STARTING_CARD")
public class StartingCardResultData extends Data {
    //ATTRIBUTES
    private int startingCardID;
    private Coordinates startingCardCoords;
    private String cardFace;
    private ArrayList<Coordinates> placingCoordinates;
    private Map<CardElements,Integer> elementsCounter;

    //CONSTRUCTOR

    //Logic constructor for subclasses
    public StartingCardResultData(String nickname, int startingCardID, Coordinates startingCardCoords, String cardFace, ArrayList<Coordinates> placingCoordinates, Map<CardElements,Integer> elementsCounter) {
        super("POSITIVE_STARTING_CARD", nickname);
        this.startingCardID = startingCardID;
        this.startingCardCoords = startingCardCoords;
        this.cardFace = cardFace;
        this.placingCoordinates = placingCoordinates;
        this.elementsCounter = elementsCounter;
    }

    @JsonCreator
    public StartingCardResultData(@JsonProperty("nickname") String nickname,
                                  @JsonProperty("startingCardID") int startingCardID,
                                  @JsonProperty("placingCoordinates") ArrayList<Coordinates> placingCoordinates,
                                  @JsonProperty("elementsCounter") Map<CardElements,Integer> elementsCounter) {
        super("POSITIVE_STARTING_CARD", nickname);
        this.startingCardID = startingCardID;
        this.startingCardCoords = null;
        this.cardFace = null;
        this.placingCoordinates = placingCoordinates;
        this.elementsCounter = elementsCounter;
    }

    public int getStartingCardID() {
        return startingCardID;
    }

    public void setStartingCardID(int startingCardID) {
        this.startingCardID = startingCardID;
    }

    public Coordinates getStartingCardCoords() {
        return startingCardCoords;
    }

    public void setStartingCardCoords(Coordinates startingCardCoords) {
        this.startingCardCoords = startingCardCoords;
    }

    public String getCardFace() {
        return cardFace;
    }

    public void setCardFace(String cardFace) {
        this.cardFace = cardFace;
    }

    public ArrayList<Coordinates> getPlacingCoordinates() {
        return placingCoordinates;
    }

    public void setPlacingCoordinates(ArrayList<Coordinates> placingCoordinates) {
        this.placingCoordinates = placingCoordinates;
    }

    public Map<CardElements, Integer> getElementsCounter() {
        return elementsCounter;
    }

    public void setElementsCounter(Map<CardElements, Integer> elementsCounter) {
        this.elementsCounter = elementsCounter;
    }

    //PUBLIC METHODS


    public Action onServer(){
        return null;
    }

    public Message onClient() {
        return new StartingCardResultMessage(this.getNickname(),this.startingCardID,this.startingCardCoords,this.cardFace,this.placingCoordinates,this.elementsCounter);
    }

    /**
     * Method which calls the right RMI interface method for each data (with override)
     * @param skeleton the client remote interface. Null if data active
     * @param stub the server remote interface. Null if data passive
     */
    @Override
    public void doRMI(RemoteInterfaceClient skeleton, RemoteInterfaceServer stub){
        try {
            skeleton.startingCardResultPassiveFirstRound(this);
        } catch (RemoteException e) {
            System.out.println("RMI call went wrong! message: " + e);
        }
    }
}
