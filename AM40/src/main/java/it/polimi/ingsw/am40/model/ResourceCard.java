package it.polimi.ingsw.am40.model;

import it.polimi.ingsw.am40.model.scoreStrategy.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import static it.polimi.ingsw.am40.model.CardElements.*;
import static it.polimi.ingsw.am40.model.CardFace.BACK;
import static it.polimi.ingsw.am40.model.EdgeState.FREE;
import static it.polimi.ingsw.am40.model.EdgeState.HIDDEN;

/**
 * The ResourceCard is a child class of Cards; it defines the resource cards, which are placeable
 * objects during the game.
 * A resource card has its own type and has visible resources in the four edges;
 * it can be placed with the front or back face visible.
 * Once positioned, the card will appear in the grid on the player private board
 * with its specific coordinates.
 */

public class ResourceCard extends Card{

    //"ENUM" ATTRIBUTES

    /**
     * Current facing of the resource card
     */
    private CardFace face;

    /**
     * Element of the card
     */
    private final CardElements cardElement;


    //COORDINATES ATTRIBUTE

    /**
     * Coordinates of the card.
     * They are initialized at (0,0) and redefined once placed
     */
    private Coordinates coordinates;


    //POINTS ATTRIBUTES

    /**
     * Points of a card
     */
    private final int scorePoints;

    /**
     * Type of score multiplier of a card
     */
    private ScoreType scoreType;

    /**
     * Type of element needed to verify object type score
     */
    private CardElements objectScoreTypeElement;


    //EDGE ATTRIBUTES

    /**
     * List (implemented like ArrayList) of elements of the edges in the front of a card
     */
    private final List<CardElements> frontEdgeResources;

    /**
     * List (implemented like ArrayList) of the coverage state of the edges of a card
     */
    private final List<EdgeState> edgeCoverage;




    //CONSTRUCTOR METHODS

    /**
     * Constructor method for ResourceCard, which creates and initializes the attributes for a new resource card.
     * The parameters which are not taken in input are set on default value.
     * Another constructor uses overloading to set card points too.
     * This constructor is usually used by the card loader from JSON file
      * @param cardID Identification number of the card
     * @param cardElement Element of the card
     * @param frontEdgeResources List of the front edges element ((1) top-left, (2) top-right, (3) bottom-left, (4) bottom-right)
     */
    public ResourceCard(int cardID, CardElements cardElement, List<CardElements> frontEdgeResources ){
        super(cardID);

        this.cardElement = cardElement;

        this.objectScoreTypeElement = NONE;

        this.frontEdgeResources = new ArrayList<>();
        this.frontEdgeResources.addAll(frontEdgeResources);

        this.edgeCoverage = new ArrayList<>();

        //For "NONE" element, it will be assigned the state "HIDDEN"; otherwise, "FREE"

        for (CardElements c : frontEdgeResources) {
            if (c == NONE){
                edgeCoverage.add(HIDDEN);
            } else {
                edgeCoverage.add(FREE);
            }
        }

        this.coordinates = new Coordinates(0, 0);
        this.face = BACK;

        this.scorePoints = 0;
        this.scoreType = new NormalScoreType();
    }

    /**
     * Constructor method for ResourceCard, which create and initializes the attributes for a new resource card.
     * The parameters which are not taken in input are set on default value.
     * This constructor takes also the score points attribute.
     * This constructor is usually used by the card loader from JSON file
     * @param cardID Identification number of the card
     * @param cardElement Element of the card
     * @param frontEdgeResources List of the front edges element ((1) top-left, (2) top-right, (3) bottom-left, (4) bottom-right)
     * @param scorePoints Points (normal score type) of the card
     */
    public ResourceCard(int cardID, CardElements cardElement, List<CardElements> frontEdgeResources, int scorePoints ){
        super(cardID);

        this.cardElement = cardElement;

        this.objectScoreTypeElement = NONE;

        this.frontEdgeResources = new ArrayList<>();
        this.frontEdgeResources.addAll(frontEdgeResources);

        this.edgeCoverage = new ArrayList<>();

        this.scorePoints = scorePoints;

        //For "NONE" element, it will be assigned the state "HIDDEN"; otherwise, "FREE"

        for (CardElements c : frontEdgeResources) {
            if (c == CardElements.NONE){
                edgeCoverage.add(HIDDEN);
            } else {
                edgeCoverage.add(FREE);
            }
        }

        this.coordinates = new Coordinates(0, 0);
        this.face = BACK;

        this.scoreType = new NormalScoreType();
    }


    //GETTER METHODS

    /**
     * Getter for face
     * @return face as CardFace
     */
    public CardFace getCardFace() {
        return this.face;
    }

    /**
     * Getter for card element
     * @return card element as CardElements
     */
    public CardElements getCardElement() {
        return this.cardElement;
    }

    /**
     * Getter for front edge resources
     * @return front edge resources as a List of CardElements
     */
    public List<CardElements> getFrontEdgeResources() {
        return this.frontEdgeResources;
    }

    /**
     * Getter for coordinates
     * @return the coordinates as Coordinates
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Getter for edge coverage
     * @return the edge coverage as List of EdgeState
     */
    public List<EdgeState> getEdgeCoverage() {
        return this.edgeCoverage;
    }

    /**
     * Getter for score type
     * @return the score type attribute as a reference to the related strategy pattern
     */
    public ScoreType getScoreType() {
        return this.scoreType;
    }

    /**
     * Getter for object score type element
     * @return the type of element for object score type
     */
    public CardElements getObjectScoreTypeElement() {
        return objectScoreTypeElement;
    }


    //SETTER METHODS

    /**
     * Setter for coordinates
     * @param newCoordinates Coordinates of the card passed as Coordinates object
     */
    public void setCoordinates(Coordinates newCoordinates) {
        this.coordinates = newCoordinates;
    }

    /**
     * Setter for coordinates
     * @param newX New X value
     * @param newY New Y value
     */
    public void setCoordinates(int newX, int newY) {
        this.coordinates.setX(newX);
        this.coordinates.setY(newY);
    }

    /**
     * Setter for card face
     * @param newFace Card face as CardFace
     */
    public void setFace(CardFace newFace) {
        this.face = newFace;
    }

    /**
     * Setter for card face
     * @param newFace Card face as a string
     */
    public void setFace(String newFace) {
        this.face = CardFace.valueOf(newFace);
    }

    /**
     * Setter for score type
     * @param scoreType as reference to the related strategy pattern
     */
    public void setScoreType(ScoreType scoreType) {
        this.scoreType = scoreType;
    }

    /**
     * Setter for score type object element
     */
    public void setObjectScoreTypeElement(CardElements objectScoreTypeElement) {
        this.objectScoreTypeElement = objectScoreTypeElement;
    }

    /**
     * Setter for edgeCoverage
     * @param newCoverage is the ArrayList of all the new EdgeState
     */
    public void setEdgeCoverage(ArrayList<EdgeState> newCoverage) {
        this.edgeCoverage.clear();
        for (int i=0; i<4; i++) {
            this.edgeCoverage.add(i,newCoverage.get(i));
        }
    }


    //PUBLIC METHODS

    /**
     * This method analyses the card edges and return a map with the couples "resource type" - "number of apparitions".
     * It is used by the private board and by the player to know how many resources he has
     * @return The map of the resources in the edges of a card
     */
    public Map<CardElements, Integer> countCardElements(){
        Map<CardElements, Integer> elementsMap = new HashMap<>();

        //Initialization of the map

        elementsMap.put(FUNGI, 0);
        elementsMap.put(PLANT, 0);
        elementsMap.put(ANIMAL, 0);
        elementsMap.put(INSECT, 0);
        elementsMap.put(QUILL, 0);
        elementsMap.put(INKWELL, 0);
        elementsMap.put(MANUSCRIPT, 0);

        int tmp;

        //For each edge element, the value of a key is incremented if it matches

        for (CardElements c : this.getFrontEdgeResources()) {
            switch (c){
                case FUNGI:
                    tmp = elementsMap.get(FUNGI);
                    elementsMap.replace(FUNGI, tmp , ++tmp);
                    break;
                case PLANT:
                    tmp = elementsMap.get(PLANT);
                    elementsMap.replace(PLANT, tmp , ++tmp);
                    break;
                case ANIMAL:
                    tmp = elementsMap.get(ANIMAL);
                    elementsMap.replace(ANIMAL, tmp , ++tmp);
                    break;
                case INSECT:
                    tmp = elementsMap.get(INSECT);
                    elementsMap.replace(INSECT, tmp , ++tmp);
                    break;
                case QUILL:
                    tmp = elementsMap.get(QUILL);
                    elementsMap.replace(QUILL, tmp , ++tmp);
                    break;
                case INKWELL:
                    tmp = elementsMap.get(INKWELL);
                    elementsMap.replace(INKWELL, tmp , ++tmp);
                    break;
                case MANUSCRIPT:
                    tmp = elementsMap.get(MANUSCRIPT);
                    elementsMap.replace(MANUSCRIPT, tmp , ++tmp);
                    break;
                default:
                    break;
            }
        }

        return elementsMap;
    }

    /**
     * This method serves as a mean to increase Player's score after a Card is placed
     * @param elementsCounter is the Map of every element on a Player's PrivateBoard's cardGrid
     * @return the amount of points given by a card to increase the score
     */
    public int calculateScore(Map<CardElements, Integer> elementsCounter){
        return this.scoreType.calculate((ArrayList<EdgeState>) this.edgeCoverage, elementsCounter, this.scorePoints, this.objectScoreTypeElement);
    }

    /**
     * This method checks if a card can be placed
     * @param elementsCounter Map of the actual elements of the player
     * @return true because a ResourceCard can always be placed
     */
    public boolean isPlaceableAccordingRequests(Map<CardElements, Integer> elementsCounter){
        return true;
    }

}

