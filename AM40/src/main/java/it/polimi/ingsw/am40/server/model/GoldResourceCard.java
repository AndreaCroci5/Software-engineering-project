package it.polimi.ingsw.am40.server.model;

import it.polimi.ingsw.am40.server.model.scoreStrategy.ScoreType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static it.polimi.ingsw.am40.server.model.CardElements.*;


/**
 * The GoldResourceCard is a child class of ResourceCard; it defines the golden resource cards, which are placeable
 * objects during the game.
 * A golden resource card is a resource card which could have requirements for placing and multiplier for the
 * calculation of the score points.
 */
public class GoldResourceCard extends ResourceCard{

    //ATTRIBUTES

    /**
     * List (implemented like ArrayList) of elements required for placing
     */
    private final List<CardElements> requires;


    //CONSTRUCTOR METHOD

    /**
     * Constructor method for ResourceCard, which create and initializes the attributes for a new resource card.
     * The parameters which are not taken in input are set on default value.
     * This constructor takes also the score points attribute.
     * This constructor is usually used by the card loader from JSON file
     *
     * @param cardID             Identification number of the card
     * @param cardElement        Element of the card
     * @param frontEdgeResources List of the front edges element ((1) top-left, (2) top-right, (3) bottom-left, (4) bottom-right)
     * @param scorePoints        Points of the card
     * @param requires           List of elements required for card placing
     * @param scoreType     as reference of the strategy pattern ScoreType (statically ScoreType,
     *                      dynamically of the specific class which implements calculate)
     */
    public GoldResourceCard(int cardID, CardElements cardElement, List<CardElements> frontEdgeResources, int scorePoints, List<CardElements> requires, ScoreType scoreType) {
        super(cardID, cardElement, frontEdgeResources, scorePoints);

        this.setScoreType(scoreType);

        this.requires = new ArrayList<>();
            this.requires.addAll(requires);

    }

    /**
     * Constructor method overload for GoldResourceCard, which create and initializes the attributes for a new GoldResourceCard.
     * The parameters which are not taken in input are set on default value.
     * This constructor takes the score points attribute like the other constructor and in addition receives, as input
     * to set on the class instance attribute, the object multiplier shown on certain GoldResourceCards.
     * This constructor is usually used by the card loader from JSON file
     * @param cardID Identification number of the card
     * @param cardElement Element of the card
     * @param frontEdgeResources List of the front edges element ((1) top-left, (2) top-right, (3) bottom-left, (4) bottom-right)
     * @param scorePoints Points of the card
     * @param requires List of elements required for card placing
     * @param scoreType as reference of the strategy pattern ScoreType (statically ScoreType, dynamically of the specific class which implements calculate)
     * @param objectScoreTypeElement The object multiplier in certain GoldResourceCards
     */
    public GoldResourceCard(int cardID, CardElements cardElement, List<CardElements> frontEdgeResources, int scorePoints, List<CardElements> requires, ScoreType scoreType, CardElements objectScoreTypeElement) {
        super(cardID, cardElement, frontEdgeResources, scorePoints);

        this.setScoreType(scoreType);

        this.setObjectScoreTypeElement(objectScoreTypeElement);

        this.requires = new ArrayList<>();
        this.requires.addAll(requires);

    }


    //GETTER METHOD

    /**
     * Getter for requires
     * @return the List of the requirements
     */
    public List<CardElements> getRequires() {
        return this.requires;
    }


    //PUBLIC METHODS

    //TO BE CHECKED (it can be done with lambda probably)

    /**
     * This method checks if the requirements are satisfied
      * @param elementsCounter Map of the actual elements of the player
     * @return true if requirements are checked, false otherwise
     */
    @Override
    public boolean isPlaceableAccordingRequests(Map<CardElements, Integer> elementsCounter) {
        Map<CardElements, Integer> tmpMapCopy = new HashMap<>(elementsCounter);

        int tmp;

        for (CardElements c : this.requires) {
            switch (c){
                case FUNGI:
                    tmp = tmpMapCopy.get(FUNGI);
                    tmpMapCopy.replace(FUNGI, --tmp);
                    if(tmpMapCopy.get(FUNGI) < 0)
                        return false;
                    break;
                case PLANT:
                    tmp = tmpMapCopy.get(PLANT);
                    tmpMapCopy.replace(PLANT, --tmp);
                    if(tmpMapCopy.get(PLANT) < 0)
                        return false;
                    break;
                case ANIMAL:
                    tmp = tmpMapCopy.get(ANIMAL);
                        tmpMapCopy.replace(ANIMAL, --tmp);
                    if(tmpMapCopy.get(ANIMAL) < 0)
                        return false;
                    break;
                case INSECT:
                    tmp = tmpMapCopy.get(INSECT);
                    tmpMapCopy.replace(INSECT, --tmp);
                    if(tmpMapCopy.get(INSECT) < 0)
                        return false;
                    break;
                case QUILL:
                    tmp = tmpMapCopy.get(QUILL);
                    tmpMapCopy.replace(QUILL, --tmp);
                    if(tmpMapCopy.get(QUILL) < 0)
                        return false;
                    break;
                case INKWELL:
                    tmp = tmpMapCopy.get(INKWELL);
                    tmpMapCopy.replace(INKWELL, --tmp);
                    if(tmpMapCopy.get(INKWELL) < 0)
                        return false;
                    break;
                case MANUSCRIPT:
                    tmp = tmpMapCopy.get(MANUSCRIPT);
                    tmpMapCopy.replace(MANUSCRIPT, --tmp);
                    if(tmpMapCopy.get(MANUSCRIPT) < 0)
                        return false;
                    break;

                case EMPTY, NONE:
                    break;
            }
        }

        return true;
    }
}
