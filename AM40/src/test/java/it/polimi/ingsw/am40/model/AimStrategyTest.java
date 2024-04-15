package it.polimi.ingsw.am40.model;

import it.polimi.ingsw.am40.model.aimStrategy.AimCheckerDiagonalPattern;
import it.polimi.ingsw.am40.model.aimStrategy.AimCheckerLPattern;
import it.polimi.ingsw.am40.model.aimStrategy.AimCheckerResource;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for aim strategy pattern
 */
public class AimStrategyTest {

    // AimCheckerResource


    /**
     * This method tests the return multiplier of the check method in the AimCard class
     * for the aim cards that have the resource as constraint
     * Finally, it asserts the expected return values of the check
     */
    @Test
    public void testReturnMultiplier() {
        // Setup
        PrivateBoard privateBoard = new PrivateBoard();
        privateBoard.getElementsCounter().put(CardElements.ANIMAL, 2);
        privateBoard.getElementsCounter().put(CardElements.FUNGI, 1);
        privateBoard.getElementsCounter().put(CardElements.PLANT, 4);
        privateBoard.getElementsCounter().put(CardElements.INSECT, 0);
        privateBoard.getElementsCounter().put(CardElements.QUILL, 8);

        List<CardElements> checkResources = new ArrayList<>();
        checkResources.add(CardElements.FUNGI);
        checkResources.add(CardElements.FUNGI);
        checkResources.add(CardElements.FUNGI);

        List<CardElements> checkResources2 = new ArrayList<>();
        checkResources2.add(CardElements.QUILL);
        checkResources2.add(CardElements.QUILL);
        checkResources2.add(CardElements.QUILL);

        List<CardElements> checkResources3 = new ArrayList<>();
        checkResources3.add(CardElements.ANIMAL);
        checkResources3.add(CardElements.ANIMAL);

        List<CardElements> checkResources4 = new ArrayList<>();
        checkResources4.add(CardElements.INSECT);

        List<CardElements> checkResources5 = new ArrayList<>();
        checkResources5.add(CardElements.INKWELL);

        String rotation = "null";

        AimCard card = new AimCard(1,2,checkResources,new AimCheckerResource(),rotation);
        AimCard card2 = new AimCard(2,1,checkResources2,new AimCheckerResource(),rotation);
        AimCard card3 = new AimCard(3, 3, checkResources3, new AimCheckerResource(), rotation);
        AimCard card4 = new AimCard(4, 4, checkResources4, new AimCheckerResource(), rotation);
        AimCard card5 = new AimCard(5, 5, checkResources5, new AimCheckerResource(), rotation);

        // Run the test
        final int result = card.getChecker().check(privateBoard, checkResources, rotation);
        final int result2 = card2.getChecker().check(privateBoard, checkResources2, rotation);
        final int result3 = card3.getChecker().check(privateBoard, checkResources3, rotation);
        final int result4 = card4.getChecker().check(privateBoard, checkResources4, rotation);
        final int result5 = card5.getChecker().check(privateBoard, checkResources5, rotation);

        // Verify the results
        assertEquals(0, result);
        assertEquals(2, result2);
        assertEquals(1, result3);
        assertEquals(0, result4);
        assertEquals(0, result5);
    }


    /**
     * This method tests the buildElementCounter method in the AimStrategyTest class
     * It checks if the buildElementCounter method correctly counts
     * the occurrences of each element in the checkResources list
     */
    @Test
    public void testBuildElementCounter() {
        List<CardElements> checkResources = new ArrayList<>();
        checkResources.add(CardElements.FUNGI);
        checkResources.add(CardElements.FUNGI);
        checkResources.add(CardElements.FUNGI);
        checkResources.add(CardElements.QUILL);
        checkResources.add(CardElements.QUILL);
        checkResources.add(CardElements.QUILL);
        checkResources.add(CardElements.ANIMAL);
        checkResources.add(CardElements.ANIMAL);
        HashMap<CardElements, Integer> counter = new HashMap<>();
        for (CardElements element : checkResources) {
            counter.put(element, counter.getOrDefault(element, 0) + 1);
        }
        assertEquals(3, counter.get(CardElements.FUNGI));
        assertEquals(3, counter.get(CardElements.QUILL));
        assertEquals(2, counter.get(CardElements.ANIMAL));
        assertFalse(counter.containsKey(CardElements.INKWELL));
    }




    // AimCheckerDiagonalPattern


    /**
     * This method tests the findNeighbors method in the AimCheckerDiagonalPattern class
     * It verifies if the method correctly identifies neighbors
     * based on the provided grid, next coordinates, and check resources
     * It asserts the expected output for various test scenarios
     */
    @Test
    public void testFindNeighbors() {
        ArrayList<ResourceCard> tempGrid = new ArrayList<>();
        ArrayList<Coordinates> nextCoords = new ArrayList<>();
        List<CardElements> checkResources = new ArrayList<>();
        ArrayList<ResourceCard> res = new ArrayList<>();

        ArrayList<CardElements> examplesElem = new ArrayList<>();
        examplesElem.add(CardElements.ANIMAL);
        examplesElem.add(CardElements.FUNGI);
        examplesElem.add(CardElements.PLANT);
        ResourceCard card1 = new ResourceCard(1,CardElements.FUNGI,examplesElem,1);

        ArrayList<CardElements> examplesElem2 = new ArrayList<>();
        examplesElem.add(CardElements.FUNGI);
        examplesElem.add(CardElements.FUNGI);
        ResourceCard card2 = new ResourceCard(2,CardElements.FUNGI,examplesElem2,1);

        ArrayList<CardElements> examplesElem3 = new ArrayList<>();
        examplesElem.add(CardElements.ANIMAL);
        examplesElem.add(CardElements.PLANT);
        ResourceCard card3 = new ResourceCard(3,CardElements.ANIMAL,examplesElem3,1);

        card1.setCoordinates(1,3);
        card2.setCoordinates(2,4);
        card3.setCoordinates(12,27);
        tempGrid.add(card1);
        tempGrid.add(card2);
        tempGrid.add(card3);

        Coordinates next1 = new Coordinates(1,3);
        Coordinates next2 = new Coordinates(2,4);
        nextCoords.add(next1);
        nextCoords.add(next2);

        checkResources.add(CardElements.FUNGI);
        checkResources.add(CardElements.FUNGI);
        checkResources.add(CardElements.FUNGI);


        res = AimCheckerDiagonalPattern.findNeighbors(tempGrid,nextCoords,checkResources);

        assertFalse(res.isEmpty());
        assertEquals(2, res.size());
        assertEquals(3,tempGrid.size());

        nextCoords.clear();
        Coordinates next3 = new Coordinates(11, 26);
        Coordinates next4 = new Coordinates(121, 278);
        nextCoords.add(next3);
        nextCoords.add(next4);
        res = AimCheckerDiagonalPattern.findNeighbors(tempGrid, nextCoords, checkResources);
        assertTrue(res.isEmpty());

        nextCoords.clear();
        nextCoords.add(next1);
        nextCoords.add(next4);
        res = AimCheckerDiagonalPattern.findNeighbors(tempGrid, nextCoords, checkResources);
        assertFalse(res.isEmpty());
        assertEquals(1, res.size());

        nextCoords.clear();
        nextCoords.add(next3);
        nextCoords.add(next2);
        res = AimCheckerDiagonalPattern.findNeighbors(tempGrid, nextCoords, checkResources);
        assertFalse(res.isEmpty());
        assertEquals(1, res.size());

        nextCoords.clear();
        nextCoords.add(next1);
        nextCoords.add(next4);
        tempGrid.remove(card1);
        res = AimCheckerDiagonalPattern.findNeighbors(tempGrid, nextCoords, checkResources);
        assertTrue(res.isEmpty());

        nextCoords.clear();
        nextCoords.add(next1);
        nextCoords.add(next2);
        res = AimCheckerDiagonalPattern.findNeighbors(tempGrid, nextCoords, checkResources);
        assertFalse(res.isEmpty());

        nextCoords.clear();
        nextCoords.add(next1);
        nextCoords.add(next2);
        tempGrid.remove(card2);
        res = AimCheckerDiagonalPattern.findNeighbors(tempGrid, nextCoords, checkResources);
        assertTrue(res.isEmpty());
    }

    /**
     * The testNeighCord method tests the neighborsCord method in the AimCheckerDiagonalPattern class
     * It verifies if the method correctly identifies the neighboring coordinates
     * based on the provided card, rotation and expected output coordinates
     * It asserts the expected output for various test scenarios
     */
    @Test
    public void testNeighCord() {
        Coordinates next1 = new Coordinates(6, 8);
        ResourceCard card1 = new ResourceCard(1,CardElements.FUNGI,new ArrayList<>(),1);
        card1.setCoordinates(next1);
        String rotation1 = "x";
        Coordinates expected1 = new Coordinates(5, 7);
        Coordinates expected2 = new Coordinates(4, 6);
        ArrayList<Coordinates> result1 = AimCheckerDiagonalPattern.neighborsCord(card1,rotation1);
        assertEquals(expected1, result1.getFirst());
        assertEquals(expected2, result1.getLast());

        Coordinates next2 = new Coordinates(2, 4);
        ResourceCard card2 = new ResourceCard(2,CardElements.FUNGI,new ArrayList<>(),1);
        card2.setCoordinates(next2);
        String rotation2 = "y";
        Coordinates expected3 = new Coordinates(3, 5);
        Coordinates expected4 = new Coordinates(4, 6);
        ArrayList<Coordinates> result2 = AimCheckerDiagonalPattern.neighborsCord(card2,rotation2);
        assertEquals(expected3, result2.getFirst());
        assertEquals(expected4, result2.getLast());

        Coordinates next3 = new Coordinates(1, 1);
        ResourceCard card3 = new ResourceCard(3,CardElements.FUNGI,new ArrayList<>(),1);
        card3.setCoordinates(next3);
        String rotation3 = "x";
        Coordinates expected5 = new Coordinates(0, 0);
        Coordinates expected6 = new Coordinates(-1, -1);
        ArrayList<Coordinates> result3 = AimCheckerDiagonalPattern.neighborsCord(card3,rotation3);
        assertEquals(expected5, result3.getFirst());
        assertEquals(expected6, result3.getLast());
        assertEquals(card3.getCoordinates().getX(),1);
        assertEquals(card3.getCoordinates().getY(),1);

        ArrayList<Coordinates> result4 = AimCheckerDiagonalPattern.neighborsCord(card3,"z");
        assertEquals(0,result4.size());
    }

    /**
     * This method tests the return multiplier of the check method in the AimCard class
     * for the aim cards that have the diagonal pattern as constraint
     * Finally, it asserts the expected return values of the check
     */
    @Test
    public void testCheck() {
        PrivateBoard privateBoard = new PrivateBoard();

        List<CardElements> checkResources = new ArrayList<>();
        checkResources.add(CardElements.FUNGI);
        checkResources.add(CardElements.FUNGI);
        checkResources.add(CardElements.FUNGI);
        String rotation = "x";

        ResourceCard card1 = new ResourceCard(1,CardElements.FUNGI,new ArrayList<>(),1);
        ResourceCard card2 = new ResourceCard(2,CardElements.ANIMAL,new ArrayList<>(),1);
        ResourceCard card3 = new ResourceCard(1,CardElements.FUNGI,new ArrayList<>(),1);
        ResourceCard card4 = new ResourceCard(1,CardElements.PLANT,new ArrayList<>(),1);
        privateBoard.getCardGrid().add(card2);
        privateBoard.getCardGrid().add(card4);

        AimCard card = new AimCard(1, 2, checkResources, new AimCheckerDiagonalPattern(), rotation);

        final int result = card.getChecker().check(privateBoard, checkResources, rotation);

        assertEquals(0, result);

        privateBoard.getCardGrid().add(card1);
        final int result2 = card.getChecker().check(privateBoard, checkResources, rotation);
        assertEquals(0, result2);

        card1.setCoordinates(0,0);
        card3.setCoordinates(-1,-1);
        ResourceCard card5 = new ResourceCard(1,CardElements.FUNGI,new ArrayList<>(),1);
        card5.setCoordinates(-2,-2);
        privateBoard.getCardGrid().add(card3);
        privateBoard.getCardGrid().add(card5);
        final int result3 = card.getChecker().check(privateBoard, checkResources, rotation);
        assertEquals(1, result3);

        ResourceCard card6 = new ResourceCard(1,CardElements.PLANT,new ArrayList<>(),1);
        card1.setCoordinates(5,8);
        card3.setCoordinates(4,7);
        card6.setCoordinates(3,6);
        privateBoard.getCardGrid().remove(card5);
        final int result4 = card.getChecker().check(privateBoard, checkResources, rotation);
        assertEquals(0, result4);

        ResourceCard card7 = new ResourceCard(1,CardElements.FUNGI,new ArrayList<>(),1);
        card7.setCoordinates(2, 5);
        privateBoard.getCardGrid().add(card7);
        final int result5 = card.getChecker().check(privateBoard, checkResources, rotation);
        assertEquals(0, result5);

        ResourceCard card8 = new ResourceCard(1, CardElements.FUNGI, new ArrayList<>(), 1);
        card8.setCoordinates(56, 56);
        privateBoard.getCardGrid().add(card8);
        ResourceCard card9 = new ResourceCard(1, CardElements.FUNGI, new ArrayList<>(), 1);
        card9.setCoordinates(55, 55);
        privateBoard.getCardGrid().add(card9);
        ResourceCard card10 = new ResourceCard(1, CardElements.FUNGI, new ArrayList<>(), 1);
        card10.setCoordinates(54, 54);
        privateBoard.getCardGrid().add(card10);

        privateBoard.getCardGrid().add(card5);
        card1.setCoordinates(5,8);
        card3.setCoordinates(4,7);
        card5.setCoordinates(3,6);

        final int result6 = card.getChecker().check(privateBoard, checkResources, rotation);
        assertEquals(2, result6);

        ResourceCard card11 = new ResourceCard(1, CardElements.FUNGI, new ArrayList<>(), 1);
        card11.setCoordinates(541, 541);
        privateBoard.getCardGrid().add(card11);

        final int result7 = card.getChecker().check(privateBoard, checkResources, rotation);
        assertEquals(2, result7);
    }



    // AimCheckerLPattern


    /**
     * The testNeighCord method tests the neighborsCord method in the AimCheckerLPattern class
     * It verifies if the method correctly identifies the neighboring coordinates
     * based on the provided card, rotation and expected output coordinates
     * It asserts the expected output for various test scenarios
     */
    @Test
    public void testNeighCordL () {
        Coordinates next1 = new Coordinates(6, 8);
        ResourceCard card1 = new ResourceCard(1,CardElements.FUNGI,new ArrayList<>(),1);
        card1.setCoordinates(next1);
        String rotation1 = "x";
        Coordinates expected1 = new Coordinates(7, 7);
        Coordinates expected2 = new Coordinates(7, 6);
        ArrayList<Coordinates> result1 = AimCheckerLPattern.neighborsCord(card1,rotation1);
        assertEquals(expected1, result1.getFirst());
        assertEquals(expected2, result1.getLast());

        Coordinates next2 = new Coordinates(14, 84);
        ResourceCard card2 = new ResourceCard(1,CardElements.FUNGI,new ArrayList<>(),1);
        card2.setCoordinates(next2);
        String rotation2 = "y";
        Coordinates expected3 = new Coordinates(15, 85);
        Coordinates expected4 = new Coordinates(15, 86);
        ArrayList<Coordinates> result2 = AimCheckerLPattern.neighborsCord(card2,rotation2);
        assertEquals(expected3, result2.getFirst());
        assertEquals(expected4, result2.getLast());

        Coordinates next3 = new Coordinates(18, 26);
        ResourceCard card3 = new ResourceCard(1,CardElements.FUNGI,new ArrayList<>(),1);
        card3.setCoordinates(next3);
        String rotation3 = "-x";
        Coordinates expected5 = new Coordinates(17, 27);
        Coordinates expected6 = new Coordinates(17, 28);
        ArrayList<Coordinates> result3 = AimCheckerLPattern.neighborsCord(card3,rotation3);
        assertEquals(expected5, result3.getFirst());
        assertEquals(expected6, result3.getLast());

        Coordinates next4 = new Coordinates(156, 212);
        ResourceCard card4 = new ResourceCard(1,CardElements.FUNGI,new ArrayList<>(),1);
        card4.setCoordinates(next4);
        String rotation4 = "-y";
        Coordinates expected7 = new Coordinates(155, 211);
        Coordinates expected8 = new Coordinates(155, 210);
        ArrayList<Coordinates> result4 = AimCheckerLPattern.neighborsCord(card4,rotation4);
        assertEquals(expected7, result4.getFirst());
        assertEquals(expected8, result4.getLast());

        ArrayList<Coordinates> result5 = AimCheckerDiagonalPattern.neighborsCord(card3,"z");
        assertEquals(0,result5.size());
    }

    /**
     * This method tests the findNeighbors method in the AimCheckerLPattern class
     * It verifies if the method correctly identifies neighbors
     * based on the provided grid, next coordinates, and check resources
     * It asserts the expected output for various test scenarios
     */
    @Test
    public void testFindNeighL() {
        ArrayList<ResourceCard> tempGrid = new ArrayList<>();
        ArrayList<Coordinates> tempCoords = new ArrayList<>();
        List<CardElements> checkResources = new ArrayList<>();
        ArrayList<ResourceCard> res = new ArrayList<>();

        ResourceCard card1 = new ResourceCard(1,CardElements.FUNGI,new ArrayList<>(),1);
        ResourceCard card2 = new ResourceCard(2,CardElements.FUNGI,new ArrayList<>(),1);
        ResourceCard card3 = new ResourceCard(3,CardElements.PLANT,new ArrayList<>(),1);
        ResourceCard card4 = new ResourceCard(1,CardElements.INSECT,new ArrayList<>(),1);
        ResourceCard card5 = new ResourceCard(5,CardElements.ANIMAL,new ArrayList<>(),1);
        checkResources.add(CardElements.PLANT);
        checkResources.add(CardElements.FUNGI);
        checkResources.add(CardElements.FUNGI);
        card3.setCoordinates(4,4);
        card4.setCoordinates(4,5);
        Coordinates cor1 = new Coordinates(4,4);
        Coordinates cor2 = new Coordinates(4,5);
        tempCoords.add(cor1);
        tempCoords.add(cor2);

        tempGrid.add(card3);
        tempGrid.add(card4);
        tempGrid.add(card5);

        res = AimCheckerLPattern.findNeighbors(tempGrid,tempCoords,checkResources);

        assertTrue(res.isEmpty());

        card3.setCoordinates(8,8);
        card4.setCoordinates(1,1);
        card1.setCoordinates(4,4);
        card2.setCoordinates(4,5);
        tempGrid.add(card1);
        tempGrid.add(card2);

        res = AimCheckerLPattern.findNeighbors(tempGrid,tempCoords,checkResources);
        assertFalse(res.isEmpty());
        assertEquals(2,res.size());

        tempGrid.remove(card2);
        res = AimCheckerLPattern.findNeighbors(tempGrid,tempCoords,checkResources);
        assertEquals(1,res.size());

        tempGrid.remove(card1);
        tempGrid.add(card2);
        res = AimCheckerLPattern.findNeighbors(tempGrid,tempCoords,checkResources);
        assertEquals(1,res.size());

    }

    /**
     * This method tests the return multiplier of the check method in the AimCard class
     * for the aim cards that have the L pattern as constraint
     * Finally, it asserts the expected return values of the check
     */
    @Test
    public void testCheckL() {
        PrivateBoard privateBoard = new PrivateBoard();
        List<CardElements> checkResources = new ArrayList<>();
        String rotation1 = "-x";
        ResourceCard card1 = new ResourceCard(1,CardElements.PLANT,new ArrayList<>(),1);
        ResourceCard card2 = new ResourceCard(2,CardElements.FUNGI,new ArrayList<>(),1);
        ResourceCard card3 = new ResourceCard(3,CardElements.PLANT,new ArrayList<>(),1);
        ResourceCard card4 = new ResourceCard(4,CardElements.INSECT,new ArrayList<>(),1);
        ResourceCard card5 = new ResourceCard(5,CardElements.ANIMAL,new ArrayList<>(),1);

        checkResources.add(CardElements.PLANT);
        checkResources.add(CardElements.FUNGI);
        checkResources.add(CardElements.FUNGI);

        privateBoard.getCardGrid().add(card2);
        privateBoard.getCardGrid().add(card4);
        privateBoard.getCardGrid().add(card5);

        AimCard toCheck = new AimCard(6,2,checkResources,new AimCheckerLPattern(),rotation1);

        final int result1 = toCheck.getChecker().check(privateBoard, checkResources, rotation1);
        assertEquals(0,result1);

        privateBoard.getCardGrid().add(card1);
        privateBoard.getCardGrid().add(card3);

        final int result2 = toCheck.getChecker().check(privateBoard, checkResources, rotation1);
        assertEquals(0,result2);

        card1.setCoordinates(1,1);
        ResourceCard card6 = new ResourceCard(6,CardElements.FUNGI,new ArrayList<>(),1);
        card2.setCoordinates(0,2);
        card6.setCoordinates(0,3);
        privateBoard.getCardGrid().add(card6);
        final int result3 = toCheck.getChecker().check(privateBoard, checkResources, rotation1);
        assertEquals(1,result3);

        privateBoard.getCardGrid().remove(card2);
        final int result4 = toCheck.getChecker().check(privateBoard, checkResources, rotation1);
        assertEquals(0,result4);

        privateBoard.getCardGrid().add(card2);
        ResourceCard card7 = new ResourceCard(7,CardElements.PLANT,new ArrayList<>(),1);
        ResourceCard card8 = new ResourceCard(8,CardElements.FUNGI,new ArrayList<>(),1);
        ResourceCard card9 = new ResourceCard(1,CardElements.FUNGI,new ArrayList<>(),1);
        card7.setCoordinates(-2,-2);
        card8.setCoordinates(-3,-1);
        card9.setCoordinates(-3,0);
        privateBoard.getCardGrid().add(card7);
        privateBoard.getCardGrid().add(card8);
        privateBoard.getCardGrid().add(card9);

        final int result5 = toCheck.getChecker().check(privateBoard, checkResources, rotation1);
        assertEquals(2,result5);

    }


}

