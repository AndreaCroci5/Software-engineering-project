package it.polimi.ingsw.am40.model;


import java.util.ArrayList;
import java.util.Map;

//Author of the notes: Andrea
//TODO Add the constructor once the JSON is ready
//FIXME Once the Cards are pushed on the Repo fix references and Card Classes
//TODO Add the Game logic methods linked to the draw and place phases

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


}
