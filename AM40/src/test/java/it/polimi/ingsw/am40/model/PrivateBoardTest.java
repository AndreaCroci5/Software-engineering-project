package it.polimi.ingsw.am40.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PrivateBoardTest {

    //Not used now, but will be used in future
    /*
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

     */

    @Test
    void addCardToHand() {
        PrivateBoard privateBoard = new PrivateBoard();
        ArrayList<CardElements> exampleCardElements = new ArrayList<>();
        exampleCardElements.add(CardElements.FUNGI);
        exampleCardElements.add(CardElements.NONE);
        exampleCardElements.add(CardElements.FUNGI);
        exampleCardElements.add(CardElements.NONE);
        ResourceCard exampleCard = new ResourceCard(1, CardElements.FUNGI, exampleCardElements);
        int handDeckSize = privateBoard.getHandDeck().size();
        privateBoard.addCardToHand(exampleCard);
        assertEquals(1, privateBoard.getHandDeck().size());

    }


    //Tests that will be updated in the next days
    /*
    @Test
    void takeCardFromHand() {

    }

     */
    /*
    @Test
    void checkPlacing() {
    }

    @Test
    void placing() {
    }

    @Test
    void refreshPoints() {
    }

    @Test
    void refreshElementsCounter() {
    }

    @Test
    void refreshPlacingCoordinates() {
    }

     */
}