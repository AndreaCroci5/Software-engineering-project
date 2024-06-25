package it.polimi.ingsw.am40.client.smallModel;

import it.polimi.ingsw.am40.server.model.CardElements;
import it.polimi.ingsw.am40.server.model.Coordinates;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SmallModel {

    private List<SmallCard> myHand;

    private SmallCard myAimCard;

    private List<SmallCard> commonBoard;

    private SmallCardLoader cardLoader;

    private String token;

    private List<SmallCard> myGrid;

    private Map<String,Integer> scoreBoard;

    private Map<CardElements, Integer> elementsCounter;

    private Map<String, ArrayList<SmallCard>> otherPlayersGrid;

    private ArrayList<Coordinates> placingCoordinates;

    public List<SmallCard> getMyHand() {
        return myHand;
    }

    public void setMyHand(List<SmallCard> myHand) {
        this.myHand = myHand;
    }

    public SmallCard getMyAimCard() {
        return myAimCard;
    }

    public void setMyAimCard(SmallCard myAimCard) {
        this.myAimCard = myAimCard;
    }

    public List<SmallCard> getCommonBoard() {
        return commonBoard;
    }

    public void setCommonBoard(List<SmallCard> commonBoard) {
        this.commonBoard = commonBoard;
    }

    public SmallCardLoader getCardLoader() {
        return cardLoader;
    }

    public void setCardLoader(SmallCardLoader cardLoader) {
        this.cardLoader = cardLoader;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<SmallCard> getMyGrid() {
        return myGrid;
    }

    public void setMyGrid(List<SmallCard> myGrid) {
        this.myGrid = myGrid;
    }

    public Map<String, Integer> getScoreBoard() {
        return scoreBoard;
    }

    public void setScoreBoard(Map<String, Integer> scoreBoard) {
        this.scoreBoard = scoreBoard;
    }

    public Map<CardElements, Integer> getElementsCounter() {
        return elementsCounter;
    }

    public void setElementsCounter(Map<CardElements, Integer> elementsCounter) {
        this.elementsCounter = elementsCounter;
    }

    public Map<String, ArrayList<SmallCard>> getOtherPlayersGrid() {
        return otherPlayersGrid;
    }

    public void setOtherPlayersGrid(Map<String, ArrayList<SmallCard>> otherPlayersGrid) {
        this.otherPlayersGrid = otherPlayersGrid;
    }

    public ArrayList<Coordinates> getPlacingCoordinates() {
        return placingCoordinates;
    }

    public void setPlacingCoordinates(ArrayList<Coordinates> placingCoordinates) {
        this.placingCoordinates = placingCoordinates;
    }
}
