package it.polimi.ingsw.am40.model;

import it.polimi.ingsw.am40.model.scoreStrategy.ObjectScoreType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PrivateBoardTest {

    //TO be tested in future
    private ResourceCard resourceCardCreator(){
        ArrayList<CardElements> exampleCardElements = new ArrayList<>();
        exampleCardElements.add(CardElements.FUNGI);
        exampleCardElements.add(CardElements.NONE);
        exampleCardElements.add(CardElements.FUNGI);
        exampleCardElements.add(CardElements.NONE);
        ResourceCard card = new ResourceCard(1, CardElements.FUNGI, exampleCardElements);
        return card;
    }

    //To be tested
    private GoldResourceCard goldResourceCardCreator(){
        ArrayList<CardElements> frontEdgeRes = new ArrayList<>();
        frontEdgeRes.add(CardElements.NONE);
        frontEdgeRes.add(CardElements.NONE);
        frontEdgeRes.add(CardElements.NONE);
        frontEdgeRes.add(CardElements.QUILL);
        ArrayList<CardElements> requires = new ArrayList<>();
        requires.add(CardElements.FUNGI);
        requires.add(CardElements.FUNGI);
        requires.add(CardElements.ANIMAL);
        GoldResourceCard card = new GoldResourceCard(41, CardElements.FUNGI, frontEdgeRes, 2,requires, new ObjectScoreType());
        return card;
    }

    //To be tested
    private PrivateBoard privateBoardCreator(){
        PrivateBoard privateBoard = new PrivateBoard();
        ArrayList<CardElements> exampleCardElements = new ArrayList<>();
        exampleCardElements.add(CardElements.FUNGI);
        exampleCardElements.add(CardElements.NONE);
        exampleCardElements.add(CardElements.FUNGI);
        exampleCardElements.add(CardElements.NONE);
        for (int i=1; i<4; i++){
            privateBoard.addCardToHand(new ResourceCard(i, CardElements.FUNGI, exampleCardElements));
        }
        return privateBoard;
    }

    //Not used now, but will be used in future
    /*
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

     */

    //HandDeck Cards flow tests
    @Test
    void addCardToHand() {
        PrivateBoard privateBoard = new PrivateBoard();
        ResourceCard exampleCard = resourceCardCreator();
        privateBoard.addCardToHand(exampleCard);
        int handDeckSize = privateBoard.getHandDeck().size();
        assertEquals(1, handDeckSize);

    }


    @Test
    void takeCardFromHand() {
        takeCardFromHandLegalChoice();
        takeCardFromHandIllegalChoice();
    }

    @Test
    void takeCardFromHandLegalChoice() {
        PrivateBoard privateBoard = privateBoardCreator();
        int targetCardID = privateBoard.getHandDeck().get(1).getCardID();
        ResourceCard c = privateBoard.takeCardFromHand(1);
        assertEquals(targetCardID, c.getCardID());
        assertEquals(2, c.getCardID());
        assertEquals(2, privateBoard.getHandDeck().size());

    }

    @Test
    void takeCardFromHandIllegalChoice() {
        PrivateBoard privateBoard = privateBoardCreator();
        int targetCardID = privateBoard.getHandDeck().get(0).getCardID();
        ResourceCard c = privateBoard.takeCardFromHand(7);
        assertEquals(targetCardID, c.getCardID());
        assertEquals(1, c.getCardID());
        assertEquals(2, privateBoard.getHandDeck().size());

    }



    //CheckPlacing tests
    @Test
    void checkLegalPlacing() {
        PrivateBoard privateBoard = privateBoardCreator();
        privateBoard.getPlacingCoordinates().add(new Coordinates(0,1));
        assertTrue(privateBoard.checkPlacing(resourceCardCreator(), new Coordinates(0,1), CardFace.FRONT));
    }

    @Test
    void checkIllegalPlacing() {
        PrivateBoard privateBoard = privateBoardCreator();
        privateBoard.getPlacingCoordinates().add(new Coordinates(0,4));
        assertFalse(privateBoard.checkPlacing(resourceCardCreator(), new Coordinates(0,1), CardFace.FRONT));
    }

    @Test
    void checkGoldenCardLegalFrontPlacing(){
        PrivateBoard privateBoard = privateBoardCreator();
        privateBoard.getPlacingCoordinates().add(new Coordinates(0,1));
        privateBoard.getElementsCounter().replace(CardElements.FUNGI, 2);
        privateBoard.getElementsCounter().replace(CardElements.ANIMAL, 1);
        assertTrue(privateBoard.checkPlacing(goldResourceCardCreator(), new Coordinates(0,1), CardFace.FRONT));
    }

    @Test
    void checkGoldenCardIllegalFrontPlacingWorst(){
        PrivateBoard privateBoard = privateBoardCreator();
        privateBoard.getPlacingCoordinates().add(new Coordinates(0,1));
        assertFalse(privateBoard.checkPlacing(goldResourceCardCreator(), new Coordinates(0,1), CardFace.FRONT));
    }

    @Test
    void checkGoldenCardIllegalFrontPlacingClose(){
        PrivateBoard privateBoard = privateBoardCreator();
        privateBoard.getPlacingCoordinates().add(new Coordinates(0,1));
        privateBoard.getElementsCounter().replace(CardElements.FUNGI, 2);
        assertFalse(privateBoard.checkPlacing(goldResourceCardCreator(), new Coordinates(0,1), CardFace.FRONT));
    }

    @Test
    void checkGoldenCardLegalBackPlacing(){
        PrivateBoard privateBoard = privateBoardCreator();
        privateBoard.getPlacingCoordinates().add(new Coordinates(0,1));
        privateBoard.getElementsCounter().replace(CardElements.FUNGI, 2);
        privateBoard.getElementsCounter().replace(CardElements.ANIMAL, 1);
        assertTrue(privateBoard.checkPlacing(goldResourceCardCreator(), new Coordinates(0,1), CardFace.BACK));
    }

    @Test
    void checkGoldenCardIllegalBackPlacingWorst(){
        PrivateBoard privateBoard = privateBoardCreator();
        privateBoard.getPlacingCoordinates().add(new Coordinates(0,1));
        assertTrue(privateBoard.checkPlacing(goldResourceCardCreator(), new Coordinates(0,1), CardFace.BACK));
    }

    @Test
    void checkGoldenCardIllegalBackPlacingClose(){
        PrivateBoard privateBoard = privateBoardCreator();
        privateBoard.getPlacingCoordinates().add(new Coordinates(0,1));
        privateBoard.getElementsCounter().replace(CardElements.FUNGI, 2);
        assertTrue(privateBoard.checkPlacing(goldResourceCardCreator(), new Coordinates(0,1), CardFace.BACK));
    }
















    //Tests that will be updated in the next days
        /*
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