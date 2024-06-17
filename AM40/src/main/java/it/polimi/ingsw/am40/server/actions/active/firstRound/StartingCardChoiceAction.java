package it.polimi.ingsw.am40.server.actions.active.firstRound;

import it.polimi.ingsw.am40.server.ActionAgent;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.actions.passive.firstRound.StartingCardResultAction;
import it.polimi.ingsw.am40.server.model.CardElements;
import it.polimi.ingsw.am40.server.model.CardFace;
import it.polimi.ingsw.am40.server.model.Coordinates;
import it.polimi.ingsw.am40.server.model.Game;

import java.util.ArrayList;
import java.util.Map;

/**
 * This class represent the Action made by the Server in response of an input coming through the network made
 * by the Client that chooses the phase of his StartingCard
 */
public class StartingCardChoiceAction extends Action{
    //ATTRIBUTES
    /** CardFace chosen by the Client for the Starting Card*/
    private final CardFace cardFace;

    //CONSTRUCTOR
    /**
     * Constructor for StartingCardChoiceAction
     */
    public StartingCardChoiceAction(String nickname, int gameID, int playerID, CardFace cardFace){
        super("STARTING_CARD_CHOICE", nickname, gameID, playerID);
        this.cardFace = cardFace;
    }

    /**
     * Override of doAction for the StartingCard face selection
     * @param agent is the Game in which we perform the Action
     */
    @Override
    public void doAction(ActionAgent agent){
        Game gameContext = (Game) agent;
        //Face Change
        gameContext.placeStartingCard(this.cardFace);
        //Data fetch
        int startingCardID = gameContext.getPlayers().get(gameContext.getIndexOfPlayingPlayer()).getPrivateBoard().getCardGrid().getFirst().getCardID();
        Coordinates startingCardCoords = gameContext.getPlayers().get(gameContext.getIndexOfPlayingPlayer()).getPrivateBoard().getCardGrid().getFirst().getCoordinates();
        ArrayList<Coordinates> placingCoordinates = gameContext.getPlayers().get(gameContext.getIndexOfPlayingPlayer()).getPrivateBoard().getPlacingCoordinates();
        Map<CardElements,Integer> elementsCounter = gameContext.getPlayers().get(gameContext.getIndexOfPlayingPlayer()).getPrivateBoard().getElementsCounter();
        //Changes Notification
        gameContext.notifyListeners(new StartingCardResultAction(this.getNickname(), this.getGameID(), this.getPlayerID(), startingCardID, startingCardCoords, this.cardFace.toString(), placingCoordinates, elementsCounter), gameContext.getListeners());
    }
}
