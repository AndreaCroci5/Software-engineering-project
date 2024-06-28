package it.polimi.ingsw.am40.server.model;

import java.util.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Test class with Black-box unit tests for the StartingCard class
 */

public class StartingCardBlackBoxTest {


    /**
     * Tests the constructor of the StartingCard class with a valid input and check if the object is correctly created,
     * with the correct attributes and values set.
     */

    @Test
    public void testConstructor(){
        List<CardElements> startingResources = new ArrayList<>();
        List<CardElements> frontEdgeResources = new ArrayList<>();
        List<CardElements> backEdgeResources = new ArrayList<>();

        StartingCard startingCard = new StartingCard(1, startingResources, frontEdgeResources, backEdgeResources);

        assertEquals(1, startingCard.getCardID());
        assertEquals(startingResources, startingCard.getStartingResources());
        assertEquals(frontEdgeResources, startingCard.getFrontEdgeResources());
        assertEquals(backEdgeResources, startingCard.getBackEdgeResources());

    }


    /**
     * Tests the countCardElements method of the StartingCard class with a valid input and check
     * if the method returns the correct map of elements.
     */

    @Test
    public void testCountCardElements(){
        List<CardElements> startingResources = new ArrayList<>();
        List<CardElements> frontEdgeResources = new ArrayList<>();
        StartingCard startingCard = new StartingCard(1, startingResources, frontEdgeResources, new ArrayList<>());

        Map<CardElements, Integer> expectedElementsMap = new HashMap<>();
        expectedElementsMap.put(CardElements.FUNGI, 0);
        expectedElementsMap.put(CardElements.PLANT, 0);
        expectedElementsMap.put(CardElements.ANIMAL, 0);
        expectedElementsMap.put(CardElements.INSECT, 0);
        expectedElementsMap.put(CardElements.QUILL, 0);
        expectedElementsMap.put(CardElements.INKWELL, 0);
        expectedElementsMap.put(CardElements.MANUSCRIPT, 0);

        assertEquals(expectedElementsMap, startingCard.countCardElements());

    }


    /**
     * Tests the activationOnGrid method of the StartingCard class with a valid input and check if the method
     * correctly activates the card on the grid.
     *
     */

    @Test
    public void testActivationOnGrid(){
        List<CardElements> startingResources = new ArrayList<>();
        List<CardElements> frontEdgeResources = new ArrayList<>();
        List<CardElements> backEdgeResources = new ArrayList<>();
        StartingCard startingCard = new StartingCard(1, startingResources, frontEdgeResources, backEdgeResources);

        startingCard.activationOnGrid(CardFace.BACK);

        assertEquals(backEdgeResources, startingCard.getFrontEdgeResources());
        assertEquals(new ArrayList<>(), startingCard.getBackEdgeResources());

        for (EdgeState edgeState : startingCard.getEdgeCoverage()) {
            assertEquals(EdgeState.FREE, edgeState);
        }

    }



    /**
     * Tests the testCountCardElements method of the StartingCard class with a valid input and check,
     * if the method returns the correct map of elements.
     */

    @Test
    public void testCountCardElementsFrontFace() {
        int cardID = 1;
        List<CardElements> startingResources = Arrays.asList(CardElements.FUNGI, CardElements.PLANT, CardElements.FUNGI);
        List<CardElements> frontEdgeResources = Arrays.asList(CardElements.ANIMAL, CardElements.INSECT, CardElements.QUILL, CardElements.INKWELL);
        List<CardElements> backEdgeResources = Arrays.asList(CardElements.PLANT, CardElements.MANUSCRIPT, CardElements.FUNGI, CardElements.ANIMAL);

        StartingCard startingCard = new StartingCard(cardID, startingResources, frontEdgeResources, backEdgeResources);


        Map<CardElements, Integer> expectedCount = new HashMap<>();
        expectedCount.put(CardElements.FUNGI, 0);
        expectedCount.put(CardElements.ANIMAL, 1);
        expectedCount.put(CardElements.INSECT, 1);
        expectedCount.put(CardElements.QUILL, 1);
        expectedCount.put(CardElements.INKWELL, 1);
        expectedCount.put(CardElements.MANUSCRIPT, 0);


        Map<CardElements, Integer> elementsCount = startingCard.countCardElements();

        for (CardElements element : expectedCount.keySet()) {
            assertEquals(expectedCount.get(element), elementsCount.get(element), "Count for " + element + " is incorrect");
        }
    }



    /**
     * Test the CountCardElementsBackFace method of the StartingCard class with a valid input and check,
     * if the method returns the correct map of elements for the back face of the card.
     */

    @Test
    public void testCountCardElementsBackFace() {
        int cardID = 2;
        List<CardElements> startingResources = Arrays.asList(CardElements.FUNGI, CardElements.PLANT, CardElements.ANIMAL);
        List<CardElements> frontEdgeResources = Arrays.asList(CardElements.ANIMAL, CardElements.INSECT, CardElements.QUILL, CardElements.INKWELL);
        List<CardElements> backEdgeResources = Arrays.asList(CardElements.PLANT, CardElements.MANUSCRIPT, CardElements.FUNGI, CardElements.ANIMAL);


        StartingCard startingCard = new StartingCard(cardID, startingResources, frontEdgeResources, backEdgeResources);


        Map<CardElements, Integer> expectedCount = new HashMap<>();
        expectedCount.put(CardElements.FUNGI, 0);
        expectedCount.put(CardElements.PLANT, 0);
        expectedCount.put(CardElements.ANIMAL, 1);
        expectedCount.put(CardElements.INSECT, 1);
        expectedCount.put(CardElements.QUILL, 1);
        expectedCount.put(CardElements.INKWELL, 1);
        expectedCount.put(CardElements.MANUSCRIPT, 0);


        Map<CardElements, Integer> elementsCount = startingCard.countCardElements();

        for (CardElements element : expectedCount.keySet()) {
            assertEquals(expectedCount.get(element), elementsCount.get(element), "Count for " + element + " is incorrect");
        }
    }



    /**
     * Tests the CountCardElementsWithEmptyLists method of the StartingCard class with a valid input and check,
     * if the method returns the correct map of elements for the card with empty lists.
     */

    @Test
    public void testCountCardElementsWithEmptyLists() {
        int cardID = 2;
        List<CardElements> startingResources = new ArrayList<>();
        List<CardElements> frontEdgeResources = new ArrayList<>();
        List<CardElements> backEdgeResources = new ArrayList<>();

        StartingCard startingCard = new StartingCard(cardID, startingResources, frontEdgeResources, backEdgeResources);


        Map<CardElements, Integer> expectedCount = new HashMap<>();
        expectedCount.put(CardElements.FUNGI, 0);
        expectedCount.put(CardElements.PLANT, 0);
        expectedCount.put(CardElements.ANIMAL, 0);
        expectedCount.put(CardElements.INSECT, 0);
        expectedCount.put(CardElements.QUILL, 0);
        expectedCount.put(CardElements.INKWELL, 0);
        expectedCount.put(CardElements.MANUSCRIPT, 0);


        Map<CardElements, Integer> elementsCount = startingCard.countCardElements();

        for (CardElements element : expectedCount.keySet()) {
            assertEquals(expectedCount.get(element), elementsCount.get(element), "Count for " + element + " is incorrect");
        }
    }



    /**
     * Tests the CountCardElementsFrontFaceWithMultipleCounts method of the StartingCard class with a valid input and check,
     * if the method returns the correct map of elements for the front face of the card with multiple counts.
     */

    @Test
    public void testCountCardElementsFrontFaceWithMultipleCounts() {
        int cardID = 1;
        List<CardElements> startingResources = Arrays.asList(CardElements.FUNGI, CardElements.FUNGI, CardElements.PLANT);
        List<CardElements> frontEdgeResources = Arrays.asList(CardElements.FUNGI, CardElements.FUNGI, CardElements.FUNGI, CardElements.FUNGI);
        List<CardElements> backEdgeResources = Arrays.asList(CardElements.MANUSCRIPT, CardElements.MANUSCRIPT, CardElements.MANUSCRIPT, CardElements.MANUSCRIPT);

        StartingCard startingCard = new StartingCard(cardID, startingResources, frontEdgeResources, backEdgeResources);


        Map<CardElements, Integer> expectedCount = new HashMap<>();
        expectedCount.put(CardElements.FUNGI, 4);
        expectedCount.put(CardElements.PLANT, 0);
        expectedCount.put(CardElements.ANIMAL, 0);
        expectedCount.put(CardElements.INSECT, 0);
        expectedCount.put(CardElements.QUILL, 0);
        expectedCount.put(CardElements.INKWELL, 0);
        expectedCount.put(CardElements.MANUSCRIPT, 0);


        Map<CardElements, Integer> elementsCount = startingCard.countCardElements();


        for (CardElements element : expectedCount.keySet()) {
            assertEquals(expectedCount.get(element), elementsCount.get(element), "Count for " + element + " is incorrect");
        }
    }

}
