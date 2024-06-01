package it.polimi.ingsw.am40.server.actions.passive.round;

import it.polimi.ingsw.am40.server.ActionAgent;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.model.CardElements;
import it.polimi.ingsw.am40.server.model.Coordinates;
import it.polimi.ingsw.am40.server.model.Game;
import it.polimi.ingsw.am40.server.network.virtual_view.VVServer;

import java.util.ArrayList;
import java.util.Map;

/**
 * This class represent the action response made by the Server after executing a positive placing phase.
 * It works as executor for the model on the virtual view, following the MVC pattern.
 * This class carries the information about the changes made by the last PlacingAction
 */
public class PositivePlacingAction extends Action {
    //ATTRIBUTES
    /** Amount of points that a Player has*/
    private final int score;
    /** Reference to the Map that keeps the amount of every CardElement in the PrivateBoard*/
    private final Map<CardElements,Integer> elementsCounter;

    /** Reference to the ArrayList containing all the future placing legal Coordinates*/
    private final ArrayList<Coordinates> placingCoordinates;

    /** ID of the last Card placed*/
    private final int cardID;

    //CONSTRUCTOR
    /**
     * Constructor for Positive placing action response
     */
    public PositivePlacingAction(int gameID, int playerID, int cardID, int score, Map<CardElements,Integer> elementsCounter, ArrayList<Coordinates> placingCoordinates){
        super("POSITIVE_PLACING", gameID, playerID);
        this.cardID = cardID;
        this.score = score;
        this.elementsCounter = elementsCounter;
        this.placingCoordinates = placingCoordinates;
    }

    /**
     * Override of doAction for the positive response after a placing phase
     * @param agent is the VirtualView in which we perform the Action
     */
    @Override
    public void doAction(ActionAgent agent){
        VVServer v = (VVServer) agent;

    }
}
