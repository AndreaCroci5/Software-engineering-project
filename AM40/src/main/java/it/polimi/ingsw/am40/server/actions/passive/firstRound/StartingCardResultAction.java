package it.polimi.ingsw.am40.server.actions.passive.firstRound;

import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.data.passive.firstRound.StartingCardResultData;
import it.polimi.ingsw.am40.server.ActionAgent;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.model.CardElements;
import it.polimi.ingsw.am40.server.model.Coordinates;
import it.polimi.ingsw.am40.server.network.virtual_view.VVServer;

import java.util.ArrayList;
import java.util.Map;

/**
 * This class serves as a mean to notify to the VirtualView which then will notify the client by using the Network interface
 * that the StartingCard CardFace chosen is correctly set
 */
public class StartingCardResultAction extends Action {
    //ATTRIBUTES
    /**
     * ID of the StartingCard
     */
    private int startingCardID;

    /**
     * Reference to the Coordinates of the StartingCard (0,0)
     */
    private Coordinates startingCardCoords;

    /**
     * Reference to the CardFace of the StartingCar chosen by the Player
     */
    private String cardFace;

    /**
     * Reference to the ArrayList containing the Coordinates of all the possible future placings
     */
    private ArrayList<Coordinates> placingCoordinates;

    /**
     * Reference to the elementsCounter Map updated to the last placing
     */
    private Map<CardElements,Integer> elementsCounter;

    //CONSTRUCTOR
    public StartingCardResultAction(String nickname, int gameID, int playerID, int startingCardID, Coordinates startingCardCoords, String cardFace, ArrayList<Coordinates> placingCoordinates, Map<CardElements,Integer> elementsCounter){
        super("POSITIVE_STARTING_CARD", nickname, gameID, playerID);
        this.startingCardID = startingCardID;
        this.startingCardCoords = startingCardCoords;
        this.cardFace = cardFace;
        this.placingCoordinates = placingCoordinates;
        this.elementsCounter = elementsCounter;
    }

    /**
     * Override of doAction for the positive response after a StartingCard Face selection phase
     * @param agent is the VVServer from which the Action is transformed into a Data and sent
     */
    @Override
    public void doAction(ActionAgent agent){
        VVServer v = (VVServer) agent;
        v.sendOnNetworkBroadcastInAParty(this.getGameID(), dataCreator());
    }

    /**
     * Override of dataCreator for the creation of the respective result Data
     * @return a StartingCardInfoData
     */
    public Data dataCreator() {
        return new StartingCardResultData(this.getNickname(), this.startingCardID, this.startingCardCoords, this.cardFace, this.placingCoordinates, this.elementsCounter);
    }
}
