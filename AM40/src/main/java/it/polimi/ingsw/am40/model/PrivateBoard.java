package it.polimi.ingsw.am40.model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static it.polimi.ingsw.am40.model.CardElements.*;


/**
 * The PrivateBoard class represents the place where a Player keeps all the cards and
 * contains all the data related the game logic.
 * The cards possessed by a Player are in the handDeck and the cards placed are in the cardGrid.
 * A Player can play a card in one of the position contained in the placingCoordinates.
 * In the case of placing a goldCard or checking an aimCard, the elementsCounter eases the checking operation.
 */
public class PrivateBoard {
    //REFERENCE ATTRIBUTES

    /**Reference to the three cards held by a Player in his hands*/
    private ArrayList<ResourceCard> handDeck;
    /**Reference to all the card placed by a Player*/
    private ArrayList<ResourceCard> cardGrid;
    /**
     * Reference to all the possible coordinates where a Player can place a card
     * After every placing the Arraylist is refreshed
     */
    private ArrayList<Coordinates> placingCoordinates;
    /**Reference to a Dictionary where the amount of every CardElement that a Player possess is counted*/
    private Map<CardElements, Integer> elementsCounter;



    //CONSTRUCTOR METHOD

    /**
     * Constructor for private board.
     *
     */
    public PrivateBoard(){
        this.cardGrid = new ArrayList<>();
        this.handDeck = new ArrayList<>();
        this.placingCoordinates = new ArrayList<>();
        this.elementsCounter = new HashMap<>();

        this.elementsCounter.put(FUNGI, 0);
        this.elementsCounter.put(PLANT, 0);
        this.elementsCounter.put(ANIMAL, 0);
        this.elementsCounter.put(INSECT, 0);
        this.elementsCounter.put(INKWELL, 0);
        this.elementsCounter.put(QUILL, 0);
        this.elementsCounter.put(MANUSCRIPT, 0);
    }


    //GETTER METHODS

    /**
     * Getter for handDeck reference
     * @return handDeck as a reference to an ArrayList of ResourceCard
     */
    public ArrayList<ResourceCard> getHandDeck() {
        return handDeck;
    }

    /**
     * Getter for cardGrid reference
     * @return cardGrid as a reference to an ArrayList of ResourceCard
     */
    public ArrayList<ResourceCard> getCardGrid() {
        return cardGrid;
    }

    /**
     * Getter for placingCoordinates reference
     * @return placingCoordinates as a reference to an ArrayList of Coordinates
     */
    public ArrayList<Coordinates> getPlacingCoordinates() {
        return placingCoordinates;
    }

    /**
     * Getter for elementsCounter reference
     * @return elementsCounter as a reference to a Map of CardElements and Integer
     */
    public Map<CardElements, Integer> getElementsCounter() {
        return elementsCounter;
    }


    //PUBLIC METHODS

    /**
     * This method takes the Card drawn in the draw phase and adds it to the Player's private handDeck
     * @param card is the card that a player chose to pick from the CommonBoard
     */
    public void addCardToHand(ResourceCard card) {
        this.handDeck.add(card);
    }

    /**
     * This method serves as a mean to choose a Card from the handDeck and then, by giving the input choice,
     * returns the Card that will be placed with the corresponding method
     * @param choice is the index of the card chosen by a Player
     * @return the card chosen as a ResourceCard
     * Note: in case of an illegal choice index, the first card will always be returned
     */
    public ResourceCard takeCardFromHand(int choice) {
        ResourceCard cardChosen;
        switch (choice) {
            case 0:
                cardChosen = this.handDeck.remove(0);
                return cardChosen;
            case 1:
                cardChosen = this.handDeck.remove(1);
                return cardChosen;
            case 2:
                cardChosen = this.handDeck.remove(2);
                return cardChosen;
            default:
                cardChosen = this.handDeck.remove(0);
                return cardChosen;
        }
    }

    /**
     * This method checks if a card can be placed on a player's cardGrid in his PrivateBoard after a Player choose: a card,
     * the coordinates of the placement and the CardFace orientation.
     * If a Card is a GoldResourceCard type and CardFace is set to front, it checks the requirements
     * @param card is the card chosen by a player to be placed
     * @param coordinates are the coordinates chosen by a player to indicate where the card will be placed
     * @param cardFace is the orientation of the card chosen by a player
     * @return true if a card can be placed, false otherwise
     */
    public boolean checkPlacing(ResourceCard card, Coordinates coordinates, CardFace cardFace) {
        if (checkCoordinates(coordinates) && checkGoldResourceCardRequirements(card, cardFace)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method simulates the placing action performed by a player by adding the card chosen from the handDeck to
     * cardGrid and setting the cardFace to the right orientation and the Coordinates position
     * @param card is the card chosen by a player to be placed
     * @param coordinates are the coordinates chosen by a player to indicate where the card will be placed
     * @param cardFace is the orientation of the card chosen by a player
     */
    public void placing(ResourceCard card, Coordinates coordinates, CardFace cardFace) {
        card.setFace(cardFace);
        card.setCoordinates(coordinates);
        this.cardGrid.add(card);
    }

    /**
     * This method serves as a mean to inform the Player on how many points he gained by placing a Card on the cardGrid
     * @return the amount of points that the last Card gives after being placed
     * Note: if a ResourceCard is placed and has no scorePoints shown, the increase of the score will be 0
     */
    public int refreshPoints() {
        ResourceCard lastCardAdded = this.cardGrid.get(this.cardGrid.size()-1);
        return lastCardAdded.calculateScore(this.elementsCounter);
    }

    /**
     * This method refresh the elementsCounter attribute by removing the elements covered by the last card positioned and
     * adding the elements present on the last card edges if CardFace is FRONT, otherwise the resource on the centre if
     * the CardFace is BACK
     */
    public void refreshElementsCounter(){
        //Pivot Card
        ResourceCard tmp = this.cardGrid.get(this.cardGrid.size()-1);
        //Creation of a Map withAdjacent Coordinates
        Map<Integer, Coordinates> adjacentCoordinates = findAdjacentCoordinatesForRefreshing(tmp);
        //Removal of covered elements
        for (int i=0; i<4; i++) {
            if (tmp.getEdgeCoverage().get(i) == EdgeState.TAKEN) {
                for (ResourceCard c : this.cardGrid){
                    if (adjacentCoordinates.get(i).equals(c.getCoordinates())) {
                        if(c.getFrontEdgeResources().get(c.getFrontEdgeResources().size()-1-i) != CardElements.EMPTY){
                            int lastElementAmount = this.elementsCounter.get(c.getFrontEdgeResources().get(c.getFrontEdgeResources().size()-1-i));
                            this.elementsCounter.replace(c.getFrontEdgeResources().get(c.getFrontEdgeResources().size()-1-i), --lastElementAmount);
                        }
                    }
                }
            }
        }
        //Adding the elements of the new Card
        if (tmp.getCardFace() == CardFace.FRONT) {
            for (int i=0; i<4; i++) {
                if(tmp.getEdgeCoverage().get(i) == EdgeState.TAKEN) {
                    if(tmp.getFrontEdgeResources().get(i) != CardElements.EMPTY){
                        int lastElementAmount = this.elementsCounter.get(tmp.getFrontEdgeResources().get(i));
                        this.elementsCounter.replace(tmp.getFrontEdgeResources().get(i), ++lastElementAmount);
                    }
                }
            }
        } else {
            int lastElementAmount = this.elementsCounter.get(tmp.getCardElement());
            this.elementsCounter.replace(tmp.getCardElement(), ++lastElementAmount);
        }
    }

    /**
     * This method refreshes the placingCoordinates Arraylist after a Card is placed.
     * There are two phases: the first where the Coordinates of the last added Card to the cardGrid are removed,
     * the second phase consists in adding the new Coordinates available only if the Edges State of the last added Card are FREE
     */
    public void refreshPlacingCoordinates(){
        //Remove the coordinates from placingCoordinates of the last Card placed
        ResourceCard pivot = this.cardGrid.get(this.cardGrid.size()-1);
        for (Coordinates c: this.placingCoordinates) {
            if(c.equals(pivot.getCoordinates()))
                this.placingCoordinates.remove(c);
        }
        //Refresh by adding the new Coordinates
        //Creates the adjacentCoordinates
        Map<Integer, Coordinates> adjacentCoordinates = findAdjacentCoordinatesForRefreshing(pivot);
        //Check if the new coordinates are already present in placingCoordinates
        for(Coordinates c : this.placingCoordinates) {
            for (int i=0; i<4; i++) {
                if(adjacentCoordinates.get(i).equals(c))
                    adjacentCoordinates.remove(i);
            }
        }
        //Add the new coordinates only if the EdgesState are Free
        for(int i=0; i<4; i++){
            if (pivot.getEdgeCoverage().get(i) == EdgeState.FREE)
                this.placingCoordinates.add(adjacentCoordinates.get(i));
        }
    }

    //PRIVATE METHODS

    //      checkPlacing related methods

    /**
     * This method checks if a GoldCard is being placed with CardFace == FRONT, in that case it checks if the requirements
     * on a GoldResourceCard are satisfied. On the other hand, if the orientation is BACK the check is skipped by returning true
     * @param card is the card chosen by a player to be placed
     * @param cardFace is the orientation of the card chosen by a player
     * @return true if the card satisfy the condition or his CardFace is BACK, false if the requirements are not satisfied
     */
    private boolean checkGoldResourceCardRequirements(ResourceCard card, CardFace cardFace){
        if(cardFace == CardFace.FRONT){
            return card.isPlaceableAccordingRequests(this.elementsCounter);
        } else {
            return true;
        }
    }

    /**
     * This method checks if a card can be placed by looking for a correspondence of its coordinates with a coordinate in
     * placingCoordinates ArrayList and if this Card covers multiple edges belonging to Cards already placed
     * @param coordinates are the coordinates chosen by a player to indicate where the card will be placed
     * @return true if the placing position satisfy all the conditions illustrated, false otherwise
     */
    private boolean checkCoordinates(Coordinates coordinates){
        if (checkCoordinatesAvailability(coordinates) && checkMultiplePlacing(coordinates)){
            return true;
        } else {
            return false;
        }
    }

    //      checkCoordinates related methods

    /**
     * This method checks if the given coordinates are present in placingCoordinates ArrayList
     * @param coordinates are the coordinates chosen by a player to indicate where the card will be placed
     * @return true if there is a coordinate with the same x and y in the placingCoordinates ArrayList
     */
    private boolean checkCoordinatesAvailability(Coordinates coordinates){
        for (Coordinates current: this.placingCoordinates) {
            if (current.equals(coordinates)) return true;
        }
        return false;
    }

    /**
     * This method checks if a Card that's being placed in a certain position given by the coordinats could potentially
     * cover multiple edges belonging to adjacent cards.
     * Starting with the adjacent coordinates, it gets the corresponding cards already placed (if they exist),
     * then proceeds to check the edges
     * @param coordinates are the coordinates chosen by a player to indicate where the card will be placed
     * @return true if a card covers multiple edges of adjacent cards in a legal way, false otherwise
     */
    private boolean checkMultiplePlacing(Coordinates coordinates){
        ArrayList<Coordinates> adjacentCoordinates = createAdjacentCoordinatesForPlacing(coordinates);
        Map<ResourceCard, Integer> adjacentCards = fromAdjacentCoordinatesToCardsForPlacing(adjacentCoordinates);
        return checkAdjacentEdges(adjacentCards);
    }

    //      checkMultiplePlacing related methods
    //Checker

    /**
     * This method checks on every potential adjacentCard of a Card that's chosen to be placed.
     * For every adjacent Card it checks if the edge that could be potentially covered is FREE.
     * The index of the potentially covered edges are Symmetrical to the Map Value of the adjacentCards:
     * for example a Top-Left AdjacentCard needs a check on his Bottom-Right EdgeState,
     * a Top-Right AdjacentCard needs a check on his Bottom-Left EdgeState,
     * @param adjacentCards is the Map of the adjacentCards
     * @return true if a card covers only FREE EdgeStates, false otherwise
     */
    private boolean checkAdjacentEdges(Map<ResourceCard, Integer> adjacentCards) {
        EdgeState tmp;
        for (Map.Entry<ResourceCard, Integer> entry : adjacentCards.entrySet()) {
            tmp = entry.getKey().getEdgeCoverage().get(entry.getValue());
            if(tmp == EdgeState.HIDDEN || tmp == EdgeState.TAKEN)
                return false;
        }
        return true;
    }

    //Factory: From Coordinates to Cards

    /**
     * This method creates an ArrayList of adjacentCoordinates by using, as a pivot, the coordinates chosen
     * during the placing phase by a Player.
     * @param coordinates are the coordinates chosen by a player to indicate where the card will be placed
     * @return the four adjacent coordinate in this order: (0)Bottom-Right (1)Bottom-Left (2)Top-Right (3)Top-Left
     */
    private ArrayList<Coordinates> createAdjacentCoordinatesForPlacing(Coordinates coordinates){
        ArrayList<Coordinates> adjacentCoordinates = new ArrayList<>();
        int x = coordinates.getX();
        int y = coordinates.getY();
        adjacentCoordinates.add(new Coordinates(x+1, y));
        adjacentCoordinates.add(new Coordinates(x, y-1));
        adjacentCoordinates.add(new Coordinates(x, y+1));
        adjacentCoordinates.add(new Coordinates(x-1, y));
        return adjacentCoordinates;
    }

    /**
     * This method search in a Player's PrivateBoard's CardGrid if there is a Card placed in each of the adjacentCoordinates
     * and then returns a Map of the cards found with their Map Value corresponding to the index of the adjacentCoordinate
     * @param adjacentCoordinates is the ArrayList of the four adjacentCoordinates of the Card that's chosen to be placed
     * @return a Map of adjacentCards found in the cardGrid as Map Key , with their Map Value corresponding to the index of the adjacentCoordinate
     */
    private Map<ResourceCard, Integer> fromAdjacentCoordinatesToCardsForPlacing(ArrayList<Coordinates> adjacentCoordinates){
        Map<ResourceCard, Integer> adjacentCards = new HashMap<>();
        for (int i=0; i<4; i++){
            for (ResourceCard card : this.cardGrid) {
                if (adjacentCoordinates.get(i).equals(card.getCoordinates())) {
                    adjacentCards.put(card, i);
                }
            }
        }
        return adjacentCards;
    }

    // refreshElementsCounter related methods

    /**
     * This method creates a Map of adjacentCoordinates of a card placed.
     * The Key represents through the Coordinates value the position of the nearby Cards:
     * (0)Top-Left (1)Top-Right (2)Bottom-Left (3)Bottom-Right
     * @param card is the Card used as pivot
     * @return a Map of adjacentCoordinates
     */
    private Map<Integer, Coordinates> findAdjacentCoordinatesForRefreshing(ResourceCard card){
        int pivotX = card.getCoordinates().getX();
        int pivotY = card.getCoordinates().getY();
        Map<Integer, Coordinates> adjacentCoordinates = new HashMap<>();
        adjacentCoordinates.put(0, new Coordinates(pivotX-1, pivotY));
        adjacentCoordinates.put(1, new Coordinates(pivotX,pivotY+1));
        adjacentCoordinates.put(2, new Coordinates(pivotX,pivotY-1));
        adjacentCoordinates.put(3, new Coordinates(pivotX+1, pivotY));
        return adjacentCoordinates;
    }


}
