package it.polimi.ingsw.am40.server.actions.passive.firstRound;

import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.data.passive.firstRound.StartingCardResultData;
import it.polimi.ingsw.am40.server.ActionAgent;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.model.CardElements;
import it.polimi.ingsw.am40.server.model.Coordinates;

import java.util.ArrayList;
import java.util.Map;

/**
 * This class serves as a mean to notify to the VirtualView which then will notify the client by using the Network interface
 * that the StartingCard CardFace chosen is correctly set
 */
public class StartingCardResultAction extends Action {
    //ATTRIBUTES
    private int startingCardID;
    private Coordinates startingCardCoords;
    private String cardFace;
    private ArrayList<Coordinates> placingCoordinates;
    private Map<CardElements,Integer> elementsCounter;

    //CONSTRUCTOR
    /**
     * Constructor for Positive Starting Card CardFace selection response
     */
    public StartingCardResultAction(int gameID, int playerID, int startingCardID, Coordinates startingCardCoords, String cardFace, ArrayList<Coordinates> placingCoordinates, Map<CardElements,Integer> elementsCounter){
        super("POSITIVE_STARTING_CARD", gameID, playerID);
        this.startingCardID = startingCardID;
        this.startingCardCoords = startingCardCoords;
        this.cardFace = cardFace;
        this.placingCoordinates = placingCoordinates;
        this.elementsCounter = elementsCounter;
    }

    /**
     * Override of doAction for the positive response after a StartingCard Face selection phase
     * @param agent is the Game in which we perform the Action
     */
    @Override
    public void doAction(ActionAgent agent){

    }

    public Data dataCreator() {
        return null;
    }
}
