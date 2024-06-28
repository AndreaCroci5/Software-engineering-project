package it.polimi.ingsw.am40.client.smallModel;

import it.polimi.ingsw.am40.server.model.CardElements;
import it.polimi.ingsw.am40.server.model.Coordinates;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Class for saving the information of the game that a client needs
 */
public class SmallModel {

    /**
     * These are the card on client's hand
     */
    private List<SmallCard> myHand;

    /**
     * This is the private aim card of the client
     */
    private SmallCard myAimCard;

    /**
     * This is the common board of the game
     */
    private List<SmallCard> commonBoard;

    /**
     * This is a reference to the loader of the cards
     */
    private SmallCardLoader cardLoader;

    /**
     * This is the personal token of the client
     */
    private String token;

    /**
     * This is the private board of the client
     */
    private List<SmallCard> myGrid;

    /**
     * This is the scoreboard of the game
     */
    private Map<String,Integer> scoreBoard;

    /**
     * This is the counter of the elements in the private board of a client
     */
    private Map<CardElements, Integer> elementsCounter;

    /**
     * These are the boards of the other players
     */
    private Map<String, ArrayList<SmallCard>> otherPlayersGrid;

    /**
     * These are the coordinates where the client can place the nest card
     */
    private ArrayList<Coordinates> placingCoordinates;

    /**
     * Getter for myHand
     * @return the cards in my hand
     */
    public List<SmallCard> getMyHand() {
        return myHand;
    }

    /**
     * Setter for my hands
     * @param myHand sets the card in my hand
     */
    public void setMyHand(List<SmallCard> myHand) {
        this.myHand = myHand;
    }

    /**
     * Getter for myAimCard
     * @return my aim card
     */
    public SmallCard getMyAimCard() {
        return myAimCard;
    }

    /**
     * Setter for myAimCard
     * @param myAimCard sets my aim card
     */
    public void setMyAimCard(SmallCard myAimCard) {
        this.myAimCard = myAimCard;
    }

    /**
     * Getter for the common board
     * @return the common board of the game
     */
    public List<SmallCard> getCommonBoard() {
        return commonBoard;
    }

    /**
     * Setter for the commonBoard
     * @param commonBoard sets the common board of the game
     */
    public void setCommonBoard(List<SmallCard> commonBoard) {
        this.commonBoard = commonBoard;
    }

    /**
     * Getter for cardLoader
     * @return a reference to cardLoader
     */
    public SmallCardLoader getCardLoader() {
        return cardLoader;
    }

    /**
     * Setter for cardLoader
     * @param cardLoader sets the cardLoader
     */
    public void setCardLoader(SmallCardLoader cardLoader) {
        this.cardLoader = cardLoader;
    }

    /**
     * Getter for Token
     * @return my Token
     */
    public String getToken() {
        return token;
    }

    /**
     * Setter for Token
     * @param token sets my token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Getter for myGrid
     * @return my private board
     */
    public List<SmallCard> getMyGrid() {
        return myGrid;
    }

    /**
     * Setter for myGrid
     * @param myGrid sets my private board
     */
    public void setMyGrid(List<SmallCard> myGrid) {
        this.myGrid = myGrid;
    }

    /**
     * Getter for scoreBoard
     * @return the scoreBoard of the game
     */
    public Map<String, Integer> getScoreBoard() {
        return scoreBoard;
    }

    /**
     * Setter for scoreBoard
     * @param scoreBoard sets the scoreBoard of the game
     */
    public void setScoreBoard(Map<String, Integer> scoreBoard) {
        this.scoreBoard = scoreBoard;
    }

    /**
     * Getter for elementsCounter
     * @return the elements counter of a client private board
     */
    public Map<CardElements, Integer> getElementsCounter() {
        return elementsCounter;
    }

    /**
     * Setter for elementsCounter
     * @param elementsCounter sets the elements counter
     */
    public void setElementsCounter(Map<CardElements, Integer> elementsCounter) {
        this.elementsCounter = elementsCounter;
    }

    /**
     * Getter for otherPlayersGrid
     * @return the grid of the other players
     */
    public Map<String, ArrayList<SmallCard>> getOtherPlayersGrid() {
        return otherPlayersGrid;
    }

    /**
     * Setter for otherPlayersGrid
     * @param otherPlayersGrid sets the other players grid
     */
    public void setOtherPlayersGrid(Map<String, ArrayList<SmallCard>> otherPlayersGrid) {
        this.otherPlayersGrid = otherPlayersGrid;
    }

    /**
     * Getter for placingCoordinates
     * @return the placing coordinates
     */
    public ArrayList<Coordinates> getPlacingCoordinates() {
        return placingCoordinates;
    }

    /**
     * Setter for placingCoordinates
     * @param placingCoordinates sets the placing coordinates
     */
    public void setPlacingCoordinates(ArrayList<Coordinates> placingCoordinates) {
        this.placingCoordinates = placingCoordinates;
    }
}
