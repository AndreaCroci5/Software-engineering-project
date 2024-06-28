package it.polimi.ingsw.am40.server.model;

import it.polimi.ingsw.am40.server.model.scoreStrategy.NormalScoreType;
import it.polimi.ingsw.am40.server.model.scoreStrategy.ScoreType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;



/**
 * Test class with Black-box unit tests for the ResourceCard class
 */

public class ResourceCardBlackBoxTest {


    /**
     * Method testConstructorWithoutScorePoints tests the constructor of the ResourceCard class without scorePoints,
     * checking if the cardID, cardElement, frontEdgeResources, edgeCoverage, coordinates, cardFace and objectScoreTypeElement
     * are correctly set
     */

    @Test
    public void testConstructorWithoutScorePoints(){
        int cardID = 1;
        CardElements cardElement = CardElements.PLANT;
        List<CardElements> frontEdgeResources = new ArrayList<>();

        ResourceCard resourceCard = new ResourceCard(cardID, cardElement, frontEdgeResources);

        assertEquals(cardID, resourceCard.getCardID());
        assertEquals(cardElement, resourceCard.getCardElement());
        assertEquals(frontEdgeResources, resourceCard.getFrontEdgeResources());
        assertFalse(resourceCard.getEdgeCoverage().contains(EdgeState.FREE) || resourceCard.getEdgeCoverage().contains(EdgeState.HIDDEN));
        assertEquals(new Coordinates(0, 0), resourceCard.getCoordinates());
        assertEquals(CardFace.BACK, resourceCard.getCardFace());
        assertEquals(CardElements.NONE, resourceCard.getObjectScoreTypeElement());

    }



    /**
     * Method testConstructorWithScorePoints tests the constructor of the ResourceCard class with scorePoints,
     * checking if the cardID, cardElement, frontEdgeResources, scorePoints, edgeCoverage, coordinates, cardFace and objectScoreTypeElement
     * are correctly set
     */

    @Test
    public void testConstructorWithScorePoints(){
        int cardID = 2;
        CardElements cardElement = CardElements.ANIMAL;
        List<CardElements> frontEdgeResources = new ArrayList<>();
        int scorePoints = 5;

        ResourceCard resourceCard = new ResourceCard(cardID, cardElement, frontEdgeResources, scorePoints);

        assertEquals(cardID, resourceCard.getCardID());
        assertEquals(cardElement, resourceCard.getCardElement());
        assertEquals(frontEdgeResources, resourceCard.getFrontEdgeResources());
        assertFalse(resourceCard.getEdgeCoverage().contains(EdgeState.FREE) || resourceCard.getEdgeCoverage().contains(EdgeState.HIDDEN));
        assertEquals(new Coordinates(0, 0), resourceCard.getCoordinates());
        assertEquals(CardFace.BACK, resourceCard.getCardFace());
        assertEquals(CardElements.NONE, resourceCard.getObjectScoreTypeElement());
    }


    /**
     * Method testCountCardElements test that the method countCardElements
     * returns the correct number of CardElements
     */

    @Test
    public void testCountCardElements(){
        List<CardElements> frontEdgeResourceCard = new ArrayList<>();
        frontEdgeResourceCard.add(CardElements.ANIMAL);
        frontEdgeResourceCard.add(CardElements.INSECT);
        ResourceCard resourceCard = new ResourceCard(1, CardElements.ANIMAL, frontEdgeResourceCard);

        Map<CardElements, Integer> elementsMap = resourceCard.countCardElements();

        assertEquals(1, elementsMap.get(CardElements.INSECT));
        assertEquals(1, elementsMap.get(CardElements.ANIMAL));

    }



    /**
     * Method testCalculateScore tests that the method calculateScore returns the correct score
     */

    @Test
    public void testCalculateScore(){
        List<CardElements> frontEdgeResourceCard = new ArrayList<>();
        frontEdgeResourceCard.add(CardElements.FUNGI);
        frontEdgeResourceCard.add(CardElements.MANUSCRIPT);
        ResourceCard resourceCard = new ResourceCard(1, CardElements.FUNGI, frontEdgeResourceCard, 7);

        Map<CardElements, Integer> elementsCounter = new HashMap<>();
        elementsCounter.put(CardElements.FUNGI, 4);
        elementsCounter.put(CardElements.MANUSCRIPT, 3);

        int score = resourceCard.calculateScore(elementsCounter);
        assertEquals(7, score);

    }



    /**
     * Method testIsPlaceableAccordingRequests tests if the method IsPlaceableAccordingRequests returns true
     */

    @Test
    public void isPlaceableAccordingRequests(){
        List<CardElements> frontEdgeResourceCard = new ArrayList<>();
        ResourceCard resourceCard = new ResourceCard(1, CardElements.QUILL, frontEdgeResourceCard);
        Map<CardElements, Integer> elementsCounter = new HashMap<>();
        elementsCounter.put(CardElements.INKWELL, 1);
        elementsCounter.put(CardElements.ANIMAL, 1);
        elementsCounter.put(CardElements.INSECT, 1);
        elementsCounter.put(CardElements.MANUSCRIPT, 1);

        assertTrue(resourceCard.isPlaceableAccordingRequests(elementsCounter));

    }



    /**
     * Method testActivationOnGridBackFace tests if the method activationOnGrid changes the edgeCoverage
     */

    @Test
    public void testActivationOnGridBackFace(){
        ResourceCard resourceCard = new ResourceCard(1, CardElements.FUNGI, new ArrayList<>());
        resourceCard.setFace(CardFace.BACK);
        ArrayList<EdgeState> edgeCoverage = new ArrayList<>();
        edgeCoverage.add(EdgeState.HIDDEN);
        edgeCoverage.add(EdgeState.HIDDEN);
        edgeCoverage.add(EdgeState.HIDDEN);
        edgeCoverage.add(EdgeState.HIDDEN);
        resourceCard.setEdgeCoverage(edgeCoverage);

        resourceCard.activationOnGrid(CardFace.BACK);

        assertEquals(EdgeState.FREE, resourceCard.getEdgeCoverage().get(0));
        assertEquals(EdgeState.FREE, resourceCard.getEdgeCoverage().get(1));
        assertEquals(EdgeState.FREE, resourceCard.getEdgeCoverage().get(2));
        assertEquals(EdgeState.FREE, resourceCard.getEdgeCoverage().get(3));

    }



    /**
     * Method testActivationOnGridFrontFace test if the method activationOnGridFrontFace changes the edgeCoverage
     */

    @Test
    public void testActivationOnGridFrontFace(){
        ResourceCard resourceCard = new ResourceCard(1, CardElements.MANUSCRIPT, new ArrayList<>());
        resourceCard.setFace(CardFace.FRONT);
        ArrayList<EdgeState> edgeCoverage = new ArrayList<>();
        edgeCoverage.add(EdgeState.HIDDEN);
        edgeCoverage.add(EdgeState.FREE);
        edgeCoverage.add(EdgeState.FREE);
        edgeCoverage.add(EdgeState.HIDDEN);
        resourceCard.setEdgeCoverage(edgeCoverage);

        resourceCard.activationOnGrid(CardFace.FRONT);

        assertEquals(EdgeState.HIDDEN, resourceCard.getEdgeCoverage().get(0));
        assertEquals(EdgeState.FREE, resourceCard.getEdgeCoverage().get(1));
        assertEquals(EdgeState.FREE, resourceCard.getEdgeCoverage().get(2));
        assertEquals(EdgeState.HIDDEN, resourceCard.getEdgeCoverage().get(0));

    }



    /**
     * Method testGetCardElement tests if the method getCardElement returns che correct CardElement
     */

    @Test
    public void testGetCardElement(){
        List<CardElements> frontEdgeResourceCard = new ArrayList<>();
        frontEdgeResourceCard.add(CardElements.INKWELL);
        frontEdgeResourceCard.add(CardElements.MANUSCRIPT);
        ResourceCard resourceCard = new ResourceCard(1, CardElements.INKWELL, frontEdgeResourceCard);

        assertEquals(CardElements.INKWELL, resourceCard.getCardElement());

    }



    /**
     * Method tests if the method getCardFace returns the correct CardFace when a new card is added
     */

    @Test
    public void testGetCardFace(){
        List<CardElements> frontEdgeResourceCard = new ArrayList<>();
        frontEdgeResourceCard.add(CardElements.FUNGI);
        frontEdgeResourceCard.add(CardElements.QUILL);
        ResourceCard resourceCard = new ResourceCard(1, CardElements.FUNGI, frontEdgeResourceCard);

        assertEquals(CardFace.BACK, resourceCard.getCardFace());

    }



    /**
     * Method tests if the method setCoordinates sets the correct coordinates for
     * a new card and if the method getCoordinates returns the coordinates
     */

    @Test
    public void testSetCoordinates(){
        ResourceCard resourceCard = new ResourceCard(1, CardElements.MANUSCRIPT, new ArrayList<>());
        int newCoordinateX = 0;
        int newCoordinateY = 4;
        resourceCard.setCoordinates(newCoordinateX, newCoordinateY);

        Coordinates expectedCoordinates = new Coordinates(0, 4);

        assertEquals(expectedCoordinates, resourceCard.getCoordinates());

    }



    /**
     * Method tests if the method setFace sets the correct CardFace for
     * a new card and if the method getCardFace returns the correct CardFace
     */

    @Test
    public void testSetFace(){
        ResourceCard resourceCard = new ResourceCard(1, CardElements.PLANT, new ArrayList<>());
        CardFace newFace = CardFace.FRONT;
        resourceCard.setFace(newFace);

        assertEquals(newFace, resourceCard.getCardFace());

    }



    /**
     * Method test if the method getEdgeCoverage returns the correct EdgeCoverage
     * when a new Card is added
     */

    @Test
    public void testGetEdgeCoverage(){
        ArrayList<EdgeState> edgeCoverage= new ArrayList<>();
        edgeCoverage.add(EdgeState.HIDDEN);
        edgeCoverage.add(EdgeState.FREE);
        edgeCoverage.add(EdgeState.TAKEN);
        edgeCoverage.add(EdgeState.FREE);

        ResourceCard resourceCard = new ResourceCard(1, CardElements.INSECT, new ArrayList<>());
        resourceCard.setEdgeCoverage(edgeCoverage);

        assertEquals(edgeCoverage, resourceCard.getEdgeCoverage());

    }



    /**
     * Method test if the method getScoreType returns the correct ScoreType when a new Card is added
     */

    @Test
    public void testSetScoreType(){
        ResourceCard resourceCard = new ResourceCard(1, CardElements.PLANT, new ArrayList<>());
        ScoreType newScoreType = new NormalScoreType();
        resourceCard.setScoreType(newScoreType);

        assertEquals(newScoreType, resourceCard.getScoreType());

    }



    /**
     * Method test if the method setObjectScoreTypeElement sets the correct scoreTypeElement
     */

    @Test
    public void testSetObjectScoreTypeElement(){
        ResourceCard resourceCard = new ResourceCard(1, CardElements.NONE, new ArrayList<>());
        resourceCard.setObjectScoreTypeElement(CardElements.EMPTY);

        assertEquals(CardElements.EMPTY, resourceCard.getObjectScoreTypeElement());
    }



    /**
     * Method tests if the method setEdgeCoverage sets the correct edgeCoverage for
     * a new Card and if the method getEdgeCoverage returns the correct edgeCoverage
     */

    @Test
    public void testSetEdgeCoverage(){
        ArrayList<EdgeState> edgeCoverage = new ArrayList<>();
        edgeCoverage.add(EdgeState.TAKEN);
        edgeCoverage.add(EdgeState.FREE);
        edgeCoverage.add(EdgeState.HIDDEN);
        edgeCoverage.add(EdgeState.TAKEN);

        ResourceCard resourceCard = new ResourceCard(1, CardElements.PLANT, new ArrayList<>());
        resourceCard.setEdgeCoverage(edgeCoverage);

        assertEquals(edgeCoverage, resourceCard.getEdgeCoverage());
        assertEquals(4, resourceCard.getEdgeCoverage().size());

        for (int i =0, j = edgeCoverage.size(); i < j; i++){
            assertEquals(edgeCoverage.get(i), resourceCard.getEdgeCoverage().get(i));
        }

    }

}
