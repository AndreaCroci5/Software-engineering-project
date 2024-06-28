package it.polimi.ingsw.am40.data.passive.round;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.round.PositivePlacingMessage;
import it.polimi.ingsw.am40.client.network.RMI.RemoteInterfaceClient;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.model.CardElements;
import it.polimi.ingsw.am40.server.model.Coordinates;
import it.polimi.ingsw.am40.server.network.RMI.RemoteInterfaceServer;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Map;

/**
 * This data is used to updates the clients after a placing
 */
@JsonTypeName("POSITIVE_PLACING")
public class PositivePlacingData extends Data {
    //ATTRIBUTES
    /** Amount of points that a Player has*/
    private int score;

    /** Reference to the Map that keeps the amount of every CardElement in the PrivateBoard*/
    private Map<CardElements,Integer> elementsCounter;

    /** Reference to the ArrayList containing all the future placing legal Coordinates*/
    private ArrayList<Coordinates> placingCoordinates;

    /** ID of the last Card placed*/
    private int cardID;

    /**
     * These are the coordinates where the card was placed
     */
    private Coordinates coordsCardPlaced;

    /**
     * This is the face on which the card was placed
     */
    private String cardFace;


    //CONSTRUCTOR

    /**
     * Constructor for PositivePlacingData
     * @param nickname is the name of the active client
     * @param cardID is the ID of the last Card placed
     * @param coordsCardPlaced are the coordinates where the card was placed
     * @param cardFace is the face on which the card was placed
     * @param score is the amount of points that a Player has
     * @param elementsCounter is the reference to the Map that keeps the amount of every CardElement in the PrivateBoard
     * @param placingCoordinates is the reference to the ArrayList containing all the future placing legal Coordinates
     */
    @JsonCreator
    public PositivePlacingData(@JsonProperty("nickname") String nickname,
                               @JsonProperty("cardID") int cardID,
                               @JsonProperty("coordsCardPlaced") Coordinates coordsCardPlaced,
                               @JsonProperty("cardFace") String cardFace,
                               @JsonProperty("score") int score,
                               @JsonProperty("elementsCounter") Map<CardElements,Integer> elementsCounter,
                               @JsonProperty("placingCoordinate") ArrayList<Coordinates> placingCoordinates) {
        super("POSITIVE_PLACING", nickname);
        this.cardID = cardID;
        this.coordsCardPlaced = coordsCardPlaced;
        this.cardFace = cardFace;
        this.score = score;
        this.elementsCounter = elementsCounter;
        this.placingCoordinates = placingCoordinates;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Map<CardElements, Integer> getElementsCounter() {
        return elementsCounter;
    }

    public void setElementsCounter(Map<CardElements, Integer> elementsCounter) {
        this.elementsCounter = elementsCounter;
    }

    public ArrayList<Coordinates> getPlacingCoordinates() {
        return placingCoordinates;
    }

    public void setPlacingCoordinates(ArrayList<Coordinates> placingCoordinates) {
        this.placingCoordinates = placingCoordinates;
    }

    public int getCardID() {
        return cardID;
    }

    public void setCardID(int cardID) {
        this.cardID = cardID;
    }

    public Coordinates getCoordsCardPlaced() {
        return coordsCardPlaced;
    }

    public void setCoordsCardPlaced(Coordinates coordsCardPlaced) {
        this.coordsCardPlaced = coordsCardPlaced;
    }

    public String getCardFace() {
        return cardFace;
    }

    public void setCardFace(String cardFace) {
        this.cardFace = cardFace;
    }


    //PUBLIC METHODS


    /**
     * This method is called once the Data reaches the Server and creates the Action related to the Data sent by polymorphism
     * @return the corresponding Action on the Server
     */
    public Action onServer(){
        return null;
    }

    /**
     * This method is called once the Data reaches the Client and creates the Message related to the Data sent by polymorphism
     * @return the corresponding Message on the Client
     */
    public Message onClient() {
        return new PositivePlacingMessage(this.getNickname(),this.score,this.elementsCounter,this.placingCoordinates,this.cardID,this.coordsCardPlaced,this.cardFace);
    }

    /**
     * Method which calls the right RMI interface method for each data (with override)
     * @param skeleton the client remote interface. Null if data active
     * @param stub the server remote interface. Null if data passive
     */
    @Override
    public void doRMI(RemoteInterfaceClient skeleton, RemoteInterfaceServer stub){
        try {
            skeleton.positivePlacingPassiveRound(this);
        } catch (RemoteException e) {
            System.out.println("RMI call went wrong! message: " + e);
        }
    }
}
