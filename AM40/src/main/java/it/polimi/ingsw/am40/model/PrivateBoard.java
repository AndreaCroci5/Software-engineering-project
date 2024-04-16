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
        if (checkCoordinatesAvailability(coordinates)) {
            if(cardFace == CardFace.FRONT) {
                return checkGoldResourceCardRequirements(card, cardFace);
            } else {
                return true;
            }
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
        //Placing basic logic
        card.setFace(cardFace);
        card.setCoordinates(coordinates);
        this.cardGrid.add(card);

        //If a card is placed on the back, it sets the all the edges to FREE
        if(cardFace == CardFace.BACK) {
            ArrayList<EdgeState> freeEdges = new ArrayList<>();
            for (int i = 0; i<4; i++) {
                freeEdges.add(EdgeState.FREE);
            }
            card.setEdgeCoverage(freeEdges);
        }

        //Creates Adjacent Cards
        Map<Integer, Coordinates> adjacentCoordinates= findAdjacentCoordinatesAfterPlacing(card);
        Map<Integer, ResourceCard> adjacentCards = fromAdjacentCoordinatesToCardsAfterPlacing(adjacentCoordinates);

        //Change EdgeStates
        //Change Card Placed EdgeState
        for(Map.Entry<Integer,ResourceCard> entry : adjacentCards.entrySet()) {
            card.getEdgeCoverage().set(entry.getKey(), EdgeState.TAKEN);
        }
        //Change Adjacent Cards EdgeState
        for (int i=0; i<4; i++) {
            if (card.getEdgeCoverage().get(i) == EdgeState.TAKEN) {
                //4-1-i is the value of the symmetrical corner of the Card according the Map key which represent the coordinate
                adjacentCards.get(i).getEdgeCoverage().set(4-1-i, EdgeState.TAKEN);
            }

        }
    }

    /**
     * This method serves as a mean to inform the Player on how many points he gained by placing a Card on the cardGrid
     * @return the amount of points that the last Card gives after being placed
     * Note: if a ResourceCard is placed and has no scorePoints shown, the increase of the score will be 0
     * Note: if the Card is placed with CardFace == BACK it will be returned 0 as score increase
     */
    public int refreshPoints() {
        ResourceCard lastCardAdded = this.cardGrid.get(this.cardGrid.size()-1);
        if(lastCardAdded.getCardFace() == CardFace.FRONT)
            return lastCardAdded.calculateScore(this.elementsCounter);
        return 0;
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
        Map<Integer, Coordinates> adjacentCoordinates = findAdjacentCoordinatesAfterPlacing(tmp);
        //Removal of covered elements
        for (int i=0; i<4; i++) {
            if (tmp.getEdgeCoverage().get(i) == EdgeState.TAKEN) {
                for (ResourceCard c : this.cardGrid){
                    if (adjacentCoordinates.get(i).equals(c.getCoordinates())) {
                        if(c.getFrontEdgeResources().get(c.getFrontEdgeResources().size()-1-i) != CardElements.EMPTY && c.getCardFace() == CardFace.FRONT){
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
                if(tmp.getEdgeCoverage().get(i) == EdgeState.TAKEN || tmp.getEdgeCoverage().get(i) == EdgeState.FREE) {
                    if(tmp.getFrontEdgeResources().get(i) != CardElements.EMPTY && tmp.getFrontEdgeResources().get(i) != NONE){
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
        for (int i=0; i<this.placingCoordinates.size(); i++) {
            if(this.placingCoordinates.get(i).equals(pivot.getCoordinates())){
                this.placingCoordinates.remove(i);
                break;
            }

        }
        //Refresh by adding the new Coordinates
        //Creates the adjacentCoordinates of the last card placed, named pivot here
        Map<Integer, Coordinates> adjacentCoordinates = findAdjacentCoordinatesAfterPlacing(pivot);
        //Check if the new coordinates are already present in placingCoordinates
        //If present, it deletes them in order to do a complete refresh
        for (int j=0; j<4; j++) {
            for(int i=0; i<this.placingCoordinates.size(); i++) {
                if(adjacentCoordinates.get(j).equals(this.placingCoordinates.get(i)))
                    placingCoordinates.remove(i);
            }
        }
        //Remove from adjacentCoordinates the ones that touch a HIDDEN or TAKEN Edge of pivot
        for(int i=0; i<4; i++){
            if (pivot.getEdgeCoverage().get(i) != EdgeState.FREE)
                adjacentCoordinates.remove(i);
        }

        //COORDINATES ADDING

        //Check the possible multiplePlacing from the possible presence of "Neighbour" cards of the adjacent Coordinates
        //If present, it checks the multiple placing cases, then adds the legal Coordinates to placingCoordinates ArrayList
        for (Map.Entry<Integer, Coordinates> entry : adjacentCoordinates.entrySet()) {
            if(checkMultiplePlacing(entry.getValue())) {
                this.placingCoordinates.add(entry.getValue());
            }
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


    //      refreshPlacingCoordinates related methods

    /**
     * This method checks if a Card that's being placed in a certain position given by the coordinats could potentially
     * cover multiple edges belonging to adjacent cards.
     * Starting with the adjacent coordinates, it gets the corresponding cards already placed (if they exist),
     * then proceeds to check the edges
     * @param coordinates are the coordinates chosen by a player to indicate where the card will be placed
     * @return true if a card covers multiple edges of adjacent cards in a legal way, false otherwise
     */
    private boolean checkMultiplePlacing(Coordinates coordinates){
        ArrayList<Coordinates> adjacentCoordinates = fromAdjacentCreateNeighbourCoordinates(coordinates);
        Map<ResourceCard, Integer> adjacentCards = fromNeighbourCoordinatesToCards(adjacentCoordinates);
        return checkNeighbourEdges(adjacentCards);
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
    private boolean checkNeighbourEdges(Map<ResourceCard, Integer> adjacentCards) {
        EdgeState tmp;
        for (Map.Entry<ResourceCard, Integer> entry : adjacentCards.entrySet()) {
            tmp = entry.getKey().getEdgeCoverage().get(entry.getValue());
            if(tmp == EdgeState.HIDDEN || tmp == EdgeState.TAKEN)
                return false;
        }
        return true;
    }

    //Factory: From Coordinates to Cards for refreshing

    /**
     * This method creates an ArrayList of adjacentCoordinates by using, as a pivot, the coordinates chosen
     * during the placing phase by a Player.
     * @param coordinates are the coordinates chosen by a player to indicate where the card will be placed
     * @return the four adjacent coordinate in this order: (0)Bottom-Right (1)Bottom-Left (2)Top-Right (3)Top-Left
     */
    private ArrayList<Coordinates> fromAdjacentCreateNeighbourCoordinates(Coordinates coordinates){
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
    private Map<ResourceCard, Integer> fromNeighbourCoordinatesToCards(ArrayList<Coordinates> adjacentCoordinates){
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

    // FACTORY: refreshElementsCounter and refreshPlacingCoordinates related methods

    /**
     * This method creates a Map of adjacentCoordinates of a card placed.
     * The Key represents through the Coordinates value the position of the nearby Cards:
     * (0)Top-Left (1)Top-Right (2)Bottom-Left (3)Bottom-Right
     * @param card is the Card used as pivot
     * @return a Map of adjacentCoordinates
     */
    private Map<Integer, Coordinates> findAdjacentCoordinatesAfterPlacing(ResourceCard card){
        int pivotX = card.getCoordinates().getX();
        int pivotY = card.getCoordinates().getY();
        Map<Integer, Coordinates> adjacentCoordinates = new HashMap<>();
        adjacentCoordinates.put(0, new Coordinates(pivotX-1, pivotY));
        adjacentCoordinates.put(1, new Coordinates(pivotX,pivotY+1));
        adjacentCoordinates.put(2, new Coordinates(pivotX,pivotY-1));
        adjacentCoordinates.put(3, new Coordinates(pivotX+1, pivotY));
        return adjacentCoordinates;
    }


    private Map<Integer, ResourceCard> fromAdjacentCoordinatesToCardsAfterPlacing(Map<Integer, Coordinates> adjacentCoordinates) {
        Map<Integer, ResourceCard> adjacentCards = new HashMap<>();
        for (int i=0; i<4; i++) {
            for(ResourceCard card: this.cardGrid) {
                if(adjacentCoordinates.get(i).equals((card.getCoordinates()))) {
                    adjacentCards.put(i,card);
                }
            }
        }
        return adjacentCards;
    }
}
