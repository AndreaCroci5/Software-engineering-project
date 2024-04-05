package it.polimi.ingsw.am40.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static it.polimi.ingsw.am40.model.CardElements.*;
import static it.polimi.ingsw.am40.model.CardFace.*;

/**
 * The Starting Card is a child class of Resource Card; it defines the starting
 * cards, which are the initial placeable cards on the grid.
 * They are always at (0,0) coordinates on the private board grid.
 * Unlike the resource cards, the starting ones have also resources on the back edges
 * and in the middle of the front (starting resources)
 */

public class StartingCard extends ResourceCard{
    /**
     * Resources in the middle of a stating card, called starting resources
     */
    private final List<CardElements> startingResources;

    /**
     * Resources in the back edges of a starting card
     */
    private final List<CardElements> backEdgeResources;



    //CONSTRUCTOR METHOD

    /**
     * Constructor method for StartingCard, which creates and initializes the attributes for a new starting card.
     * The parameters which are not taken in input are set on default value.
     * This constructor takes also the score points attribute.
     * This constructor is usually used by the card loader from JSON file
     *
     * @param cardID Identification number of the card
     * @param startingResources List of the starting resources
     * @param frontEdgeResources List of the front edges element ((1) top-left, (2) top-right, (3) bottom-left, (4) bottom-right)
     * @param backEdgeResources List of the back edges element ((1) top-left, (2) top-right, (3) bottom-left, (4) bottom-right)
     */
    public StartingCard(int cardID, List<CardElements> startingResources, List<CardElements> frontEdgeResources, List<CardElements> backEdgeResources) {
        super(cardID, CardElements.NONE , frontEdgeResources, 0);

        this.startingResources = new ArrayList<>();
        this.startingResources.addAll(startingResources);

        this.backEdgeResources = new ArrayList<>();
        this.backEdgeResources.addAll(backEdgeResources);
    }


    //GETTER METHODS

    /**
     * Getter for starting resources
     * @return the List of the CardElements in the middle of the card
     */
    public List<CardElements> getStartingResources(){
        return this.startingResources;
    }

    /**
     * Getter for back edge resources
     * @return the List of the CardElements in the back edges of the card
     */
    public List<CardElements> getBackEdgeResources(){
        return this.backEdgeResources;
    }


    //PUBLIC METHODS

    @Override
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


        if (this.getCardFace() == FRONT) {
            for (CardElements c : this.getFrontEdgeResources()) {
                switch (c) {
                    case FUNGI:
                        tmp = elementsMap.get(FUNGI);
                        elementsMap.replace(FUNGI, tmp, ++tmp);
                        break;
                    case PLANT:
                        tmp = elementsMap.get(PLANT);
                        elementsMap.replace(PLANT, tmp, ++tmp);
                        break;
                    case ANIMAL:
                        tmp = elementsMap.get(ANIMAL);
                        elementsMap.replace(ANIMAL, tmp, ++tmp);
                        break;
                    case INSECT:
                        tmp = elementsMap.get(INSECT);
                        elementsMap.replace(INSECT, tmp, ++tmp);
                        break;
                    case QUILL:
                        tmp = elementsMap.get(QUILL);
                        elementsMap.replace(QUILL, tmp, ++tmp);
                        break;
                    case INKWELL:
                        tmp = elementsMap.get(INKWELL);
                        elementsMap.replace(INKWELL, tmp, ++tmp);
                        break;
                    case MANUSCRIPT:
                        tmp = elementsMap.get(MANUSCRIPT);
                        elementsMap.replace(MANUSCRIPT, tmp, ++tmp);
                        break;
                }
            }


            for (CardElements c : this.getStartingResources()) {
                switch (c) {
                    case FUNGI:
                        tmp = elementsMap.get(FUNGI);
                        elementsMap.replace(FUNGI, tmp, ++tmp);
                        break;
                    case PLANT:
                        tmp = elementsMap.get(PLANT);
                        elementsMap.replace(PLANT, tmp, ++tmp);
                        break;
                    case ANIMAL:
                        tmp = elementsMap.get(ANIMAL);
                        elementsMap.replace(ANIMAL, tmp, ++tmp);
                        break;
                    case INSECT:
                        tmp = elementsMap.get(INSECT);
                        elementsMap.replace(INSECT, tmp, ++tmp);
                        break;
                    case QUILL:
                        tmp = elementsMap.get(QUILL);
                        elementsMap.replace(QUILL, tmp, ++tmp);
                        break;
                    case INKWELL:
                        tmp = elementsMap.get(INKWELL);
                        elementsMap.replace(INKWELL, tmp, ++tmp);
                        break;
                    case MANUSCRIPT:
                        tmp = elementsMap.get(MANUSCRIPT);
                        elementsMap.replace(MANUSCRIPT, tmp, ++tmp);
                        break;
                }
            }
        } else {


            for (CardElements c : this.getBackEdgeResources()) {
                switch (c) {
                    case FUNGI:
                        tmp = elementsMap.get(FUNGI);
                        elementsMap.replace(FUNGI, tmp, ++tmp);
                        break;
                    case PLANT:
                        tmp = elementsMap.get(PLANT);
                        elementsMap.replace(PLANT, tmp, ++tmp);
                        break;
                    case ANIMAL:
                        tmp = elementsMap.get(ANIMAL);
                        elementsMap.replace(ANIMAL, tmp, ++tmp);
                        break;
                    case INSECT:
                        tmp = elementsMap.get(INSECT);
                        elementsMap.replace(INSECT, tmp, ++tmp);
                        break;
                    case QUILL:
                        tmp = elementsMap.get(QUILL);
                        elementsMap.replace(QUILL, tmp, ++tmp);
                        break;
                    case INKWELL:
                        tmp = elementsMap.get(INKWELL);
                        elementsMap.replace(INKWELL, tmp, ++tmp);
                        break;
                    case MANUSCRIPT:
                        tmp = elementsMap.get(MANUSCRIPT);
                        elementsMap.replace(MANUSCRIPT, tmp, ++tmp);
                        break;
                }
            }
        }

        return elementsMap;
    }
}
