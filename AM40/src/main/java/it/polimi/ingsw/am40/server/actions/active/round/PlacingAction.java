package it.polimi.ingsw.am40.server.actions.active.round;

import it.polimi.ingsw.am40.server.exceptions.model.RepeatPlacingException;
import it.polimi.ingsw.am40.server.ActionAgent;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.actions.passive.round.PositivePlacingAction;
import it.polimi.ingsw.am40.server.actions.passive.round.RepeatPlacingAction;
import it.polimi.ingsw.am40.server.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This class represent the action performed by the Server after receiving via network a PLACING command from the Client.
 * It works as executor for the controller on the model, following the MVC pattern
 */
public class PlacingAction extends Action {
    //ATTRIBUTES for the Execution
    /** Selection of the handCard to place based on the Arraylist index*/
    private int choice;
    /** Coordinates chosen by the Player for the Card to Place*/
    private Coordinates coordsToPlace;
    /** CardFace orientation of the Card chosen to place by the Player*/
    private final CardFace face;


    //CONSTRUCTOR
    /**
     * Constructor for PLACING action
     */
    public PlacingAction(String nickname, int gameID, int playerID, int choice, Coordinates coordsToPlace, CardFace face ){
        super("PLACING", nickname, gameID, playerID);
        this.choice = choice;
        this.coordsToPlace = coordsToPlace;
        this.face = face;
    }

    /**
     * Override of doAction for the Placing Phase
     * @param agent is the Game in which we perform the Action
     */
    @Override
    public void doAction(ActionAgent agent){
        Game gameContext = (Game) agent;
        //PlacingCheck
        try{
            if(gameContext.checkPlaceCard(this.choice, this.coordsToPlace, this.face)) {
                //Data Setup for the response
                Player playerData = gameContext.getPlayers().get(gameContext.getIndexOfPlayingPlayer());
                //PrivateBoard State Save
                Map<CardElements, Integer> tmpElementsCounter = new HashMap<>();
                tmpElementsCounter.putAll(playerData.getPrivateBoard().getElementsCounter());

                //Placing
                gameContext.placeCard(this.choice, this.coordsToPlace, this.face);

                //DATA SETUP in Positive Placing case
                int cardID = playerData.getPrivateBoard().getCardGrid().getLast().getCardID();
                String cardFace = playerData.getPrivateBoard().getCardGrid().getLast().getCardFace().toString();
                Coordinates coordsCardPlaced = playerData.getPrivateBoard().getCardGrid().getLast().getCoordinates();
                int score = playerData.getScore();
                Map<CardElements,Integer> elementsCounter = playerData.getPrivateBoard().elementsCounterDifferences(tmpElementsCounter);
                ArrayList<Coordinates> placingCoordinates = playerData.getPrivateBoard().getPlacingCoordinates();
                //Response Action creation
                gameContext.notifyListeners(new PositivePlacingAction(this.getNickname(), this.getGameID(), this.getPlayerID(), cardID, coordsCardPlaced, cardFace, score, elementsCounter, placingCoordinates), gameContext.getListeners());
            } else {
                throw new RepeatPlacingException();
            }
        } catch (RepeatPlacingException e) {
            gameContext.notifyListeners(new RepeatPlacingAction(this.getNickname(), this.getGameID(),this.getPlayerID()), gameContext.getListeners());
        }
    }
}
