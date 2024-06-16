package it.polimi.ingsw.am40.data.passive.firstRound;

import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.model.CardElements;
import it.polimi.ingsw.am40.server.model.Coordinates;

import java.util.ArrayList;
import java.util.Map;


@JsonTypeName("POSITIVE_STARTING_CARD")
public class StartingCardResultData extends Data {
    //ATTRIBUTES
    /** Nickname of the Player that has just chosen the StartingCard CardFace*/
    private String playerNickname;
    private int startingCardID;
    private Coordinates startingCardCoords;
    private String cardFace;
    private ArrayList<Coordinates> placingCoordinates;
    private Map<CardElements,Integer> elementsCounter;

    //CONSTRUCTOR

    //Logic constructor for subclasses
    public StartingCardResultData(String playerNickname, int startingCardID, Coordinates startingCardCoords, String cardFace, ArrayList<Coordinates> placingCoordinates, Map<CardElements,Integer> elementsCounter) {
        super("POSITIVE_STARTING_CARD");
        this.playerNickname = playerNickname;
        this.startingCardID = startingCardID;
        this.startingCardCoords = startingCardCoords;
        this.cardFace = cardFace;
        this.placingCoordinates = placingCoordinates;
        this.elementsCounter = elementsCounter;
    }
    //Json constructor
    public StartingCardResultData(){

    }

    //PUBLIC METHODS


    public Action onServer(){
        return null;
    }

    public Message onClient() {
        return null;
    }
}
