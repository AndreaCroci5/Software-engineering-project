package it.polimi.ingsw.am40.data.passive.round;

import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.model.CardElements;
import it.polimi.ingsw.am40.server.model.Coordinates;

import java.util.ArrayList;
import java.util.Map;

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
    private Coordinates coordsCardPlaced;
    private String cardFace;


    //CONSTRUCTOR

    //Logic constructor for subclasses
    public PositivePlacingData(String nickname, int cardID, Coordinates coordsCardPlaced, String cardFace, int score, Map<CardElements,Integer> elementsCounter, ArrayList<Coordinates> placingCoordinates){
        super("POSITIVE_PLACING", nickname);
        this.cardID = cardID;
        this.coordsCardPlaced = coordsCardPlaced;
        this.cardFace = cardFace;
        this.score = score;
        this.elementsCounter = elementsCounter;
        this.placingCoordinates = placingCoordinates;
    }
    //Json constructor
    public PositivePlacingData(){

    }

    //PUBLIC METHODS


    public Action onServer(){
        return null;
    }

    public Message onClient() {
        return null;
    }
}
