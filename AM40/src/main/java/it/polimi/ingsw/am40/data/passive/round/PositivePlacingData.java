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
    /** Nickname of the Player that has just made the draw*/
    String playerNickname;

    /** Amount of points that a Player has*/
    int score;

    /** Reference to the Map that keeps the amount of every CardElement in the PrivateBoard*/
    Map<CardElements,Integer> elementsCounter;

    /** Reference to the ArrayList containing all the future placing legal Coordinates*/
    ArrayList<Coordinates> placingCoordinates;

    /** ID of the last Card placed*/
    int cardID;


    //CONSTRUCTOR

    //Logic constructor for subclasses
    public PositivePlacingData(String playerNickname, int cardID, int score, Map<CardElements,Integer> elementsCounter, ArrayList<Coordinates> placingCoordinates){
        super("POSITIVE_PLACING");
        this.playerNickname = playerNickname;
        this.cardID = cardID;
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
