package it.polimi.ingsw.am40.server.model;

import it.polimi.ingsw.am40.server.model.*;
import it.polimi.ingsw.am40.server.model.aimStrategy.AimCheckerDiagonalPattern;
import it.polimi.ingsw.am40.server.model.aimStrategy.AimCheckerLPattern;
import it.polimi.ingsw.am40.server.model.aimStrategy.AimCheckerResource;
import it.polimi.ingsw.am40.server.model.scoreStrategy.NormalScoreType;
import it.polimi.ingsw.am40.server.model.scoreStrategy.ObjectScoreType;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static it.polimi.ingsw.am40.server.model.CommonBoard.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The CommonBoardTest class contains test methods for the CommonBoard class
 */
public class CommonBoardTest {

    /**
     * This method tests the first constructor of the CommonBoard class
     * It creates a new CommonBoard object and checks that all the necessary fields are not null and have a size of 0
     * It also checks that the plate arrays have a length of 2
     */
    @Test
    public void testCommonBoardConstructor1() {
        // All stuff created should be not null and should have size 0
        CommonBoard commonBoard = new CommonBoard();
        assertNotNull(commonBoard);
        assertNotNull(commonBoard.getAimDeck());
        assertNotNull(commonBoard.getResourceDeck());
        assertNotNull(commonBoard.getGoldenResourceDeck());
        assertNotNull(commonBoard.getStartingDeck());

        assertEquals(0, commonBoard.getAimDeck().size());
        assertEquals(0, commonBoard.getResourceDeck().size());
        assertEquals(0, commonBoard.getGoldenResourceDeck().size());
        assertEquals(0, commonBoard.getStartingDeck().size());

        assertEquals(2, plateResourceCard.length);
        assertEquals(2, plateGoldenResourceCard.length);
        assertEquals(2, plateAimCard.length);
    }

    /**
     * This method tests the second constructor of the CommonBoard class
     * It creates a new CommonBoard object with the given decks
     * and checks that all the necessary fields are initialized correctly
     * It also checks that the plate arrays have a length of 2
     */
    @Test
    public void testCommonBoardConstructor2() {
        Deck<ResourceCard> resourceDeck = new Deck<>();
        Deck<GoldResourceCard> goldenResourceDeck = new Deck<>();
        Deck<StartingCard> startingDeck = new Deck<>();
        Deck<AimCard> aimDeck = new Deck<>();

        List<Deck<? extends Card>> decks = Arrays.asList(resourceDeck, goldenResourceDeck, startingDeck, aimDeck);

        CommonBoard commonBoard = new CommonBoard(decks);

        assertEquals(resourceDeck, commonBoard.getResourceDeck());
        assertEquals(goldenResourceDeck, commonBoard.getGoldenResourceDeck());
        assertEquals(startingDeck, commonBoard.getStartingDeck());
        assertEquals(aimDeck, commonBoard.getAimDeck());

        assertEquals(2, plateResourceCard.length);
        assertEquals(2, plateGoldenResourceCard.length);
        assertEquals(2, plateAimCard.length);
    }


    /**
     * This method tests the third constructor of the CommonBoard class
     * It creates a new CommonBoard object using the given decks and
     * checks that the necessary fields are correctly initialized
     * It also checks that the plate arrays have a length of 2
     */
    @Test
    public void testCommonBoardConstructor3() {
        Deck<ResourceCard> resDeck = new Deck<>();
        Deck<GoldResourceCard> goldResDeck = new Deck<>();
        Deck<StartingCard> startDeck = new Deck<>();
        Deck<AimCard> aimDeck = new Deck<>();

        CommonBoard commonBoard = new CommonBoard(resDeck, goldResDeck, startDeck, aimDeck);

        assertSame(resDeck, commonBoard.getResourceDeck());
        assertSame(goldResDeck, commonBoard.getGoldenResourceDeck());
        assertSame(startDeck, commonBoard.getStartingDeck());
        assertSame(aimDeck, commonBoard.getAimDeck());

        assertEquals(2, plateResourceCard.length);
        assertEquals(2, plateGoldenResourceCard.length);
        assertEquals(2, plateAimCard.length);
    }

    /**
     * This method tests the iniCommonBoard method of the CommonBoard class.
     * It initializes the CommonBoard object with cards and checks that the necessary fields are correctly initialized
     * It also checks the size of the decks and the length of the plate arrays
     */
    @Test
    public void testIniCommonBoard() {
        CommonBoard commonBoard = new CommonBoard();

        ArrayList<CardElements> exampleCardElements = new ArrayList<>();
        exampleCardElements.add(CardElements.FUNGI);
        exampleCardElements.add(CardElements.NONE);
        exampleCardElements.add(CardElements.FUNGI);
        exampleCardElements.add(CardElements.NONE);
        ResourceCard card = new ResourceCard(1,CardElements.FUNGI,exampleCardElements);
        ArrayList<CardElements> exampleCardElements2 = new ArrayList<>();
        exampleCardElements2.add(CardElements.PLANT);
        exampleCardElements2.add(CardElements.ANIMAL);
        exampleCardElements2.add(CardElements.FUNGI);
        exampleCardElements2.add(CardElements.NONE);
        ResourceCard card2 = new ResourceCard(2,CardElements.PLANT,exampleCardElements2);
        ArrayList<CardElements> exampleCardElements5 = new ArrayList<>();
        exampleCardElements5.add(CardElements.FUNGI);
        exampleCardElements5.add(CardElements.ANIMAL);
        exampleCardElements5.add(CardElements.FUNGI);
        exampleCardElements5.add(CardElements.FUNGI);
        ResourceCard card5 = new ResourceCard(5,CardElements.ANIMAL,exampleCardElements5);
        commonBoard.getResourceDeck().appendToBottom(card);
        commonBoard.getResourceDeck().appendToBottom(card2);
        commonBoard.getResourceDeck().appendToBottom(card5);

        ArrayList<CardElements> exampleRequires = new ArrayList<>();
        exampleRequires.add(CardElements.FUNGI);
        exampleRequires.add(CardElements.FUNGI);
        exampleRequires.add(CardElements.ANIMAL);
        GoldResourceCard card3 = new GoldResourceCard(3, CardElements.ANIMAL, exampleCardElements, 2, exampleRequires, new ObjectScoreType());
        ArrayList<CardElements> exampleRequires2 = new ArrayList<>();
        exampleRequires2.add(CardElements.PLANT);
        exampleRequires2.add(CardElements.PLANT);
        exampleRequires2.add(CardElements.PLANT);
        GoldResourceCard card4 = new GoldResourceCard(4, CardElements.FUNGI, exampleCardElements2, 2, exampleRequires2, new NormalScoreType());
        ArrayList<CardElements> exampleRequires3 = new ArrayList<>();
        exampleRequires3.add(CardElements.ANIMAL);
        exampleRequires3.add(CardElements.ANIMAL);
        exampleRequires3.add(CardElements.ANIMAL);
        GoldResourceCard card6 = new GoldResourceCard(6, CardElements.FUNGI, exampleCardElements5, 3, exampleRequires3, new NormalScoreType());
        commonBoard.getGoldenResourceDeck().appendToBottom(card3);
        commonBoard.getGoldenResourceDeck().appendToBottom(card4);
        commonBoard.getGoldenResourceDeck().appendToBottom(card6);

        StartingCard card7 = new StartingCard(7,exampleCardElements,exampleCardElements2,exampleRequires);
        StartingCard card8 = new StartingCard(8,exampleCardElements5,exampleRequires3,exampleRequires2);
        commonBoard.getStartingDeck().appendToBottom(card7);
        commonBoard.getStartingDeck().appendToBottom(card8);

        AimCard card9 = new AimCard(9,2,exampleRequires,new AimCheckerLPattern(), "y");
        AimCard card10 = new AimCard(10,3,exampleRequires2,new AimCheckerDiagonalPattern(),"x");
        AimCard card11 = new AimCard(11,2,exampleRequires3,new AimCheckerResource(),"null");
        commonBoard.getAimDeck().appendToBottom(card9);
        commonBoard.getAimDeck().appendToBottom(card10);
        commonBoard.getAimDeck().appendToBottom(card11);

        commonBoard.iniCommonBoard();
        assertEquals(2,plateAimCard.length);
        assertEquals(2,plateResourceCard.length);
        assertEquals(2,plateGoldenResourceCard.length);
        assertEquals(1,commonBoard.getAimDeck().size());
        assertEquals(1,commonBoard.getResourceDeck().size());
        assertEquals(1,commonBoard.getGoldenResourceDeck().size());
        assertEquals(2,commonBoard.getStartingDeck().size());

        assertNotNull(commonBoard.pickFromResourcePlate(0));
        assertNotNull(commonBoard.pickFromResourcePlate(1));
        assertNotNull(commonBoard.pickFromGoldenPlate(0));
        assertNotNull(commonBoard.pickFromGoldenPlate(1));

        assertEquals(2,plateAimCard.length);
        assertEquals(2,plateResourceCard.length);
        assertEquals(2,plateGoldenResourceCard.length);

        commonBoard.addCardToResourcePlate(0);
        commonBoard.addCardToGoldenPlate(1);

        assertTrue(commonBoard.getResourceDeck().isEmpty());
        assertTrue(commonBoard.getGoldenResourceDeck().isEmpty());

        commonBoard.addCardToResourcePlate(1);
        commonBoard.addCardToGoldenPlate(0);

        assertNotNull(plateResourceCard[0]);
        assertNotNull(plateGoldenResourceCard[1]);
        assertNull(plateResourceCard[1]);
        assertNull(plateGoldenResourceCard[0]);
    }


    /**
     * This method tests the deckEmptinessCheck method of the CommonBoard class
     * It checks whether the resource deck and golden deck are empty or not
     */
    @Test
    public void testDecksEmptinessCheck() {
        // decks empty
        CommonBoard commonBoard = new CommonBoard();
        assertTrue(commonBoard.deckEmptinessCheck());

        // resource deck not empty
        ArrayList<CardElements> exampleCardElements = new ArrayList<>();
        exampleCardElements.add(CardElements.FUNGI);
        exampleCardElements.add(CardElements.NONE);
        exampleCardElements.add(CardElements.FUNGI);
        exampleCardElements.add(CardElements.NONE);
        ResourceCard card = new ResourceCard(1,CardElements.FUNGI,exampleCardElements);
        commonBoard.getResourceDeck().appendToBottom(card);
        assertFalse(commonBoard.deckEmptinessCheck());

        // decks empty
        commonBoard.getResourceDeck().pickFromTop();
        assertTrue(commonBoard.deckEmptinessCheck());

        // golden deck not empty
        ArrayList<CardElements> exampleRequires = new ArrayList<>();
        exampleRequires.add(CardElements.FUNGI);
        exampleRequires.add(CardElements.FUNGI);
        exampleRequires.add(CardElements.ANIMAL);
        GoldResourceCard card2 = new GoldResourceCard(1, CardElements.ANIMAL, exampleCardElements, 2, exampleRequires, new ObjectScoreType());
        commonBoard.getGoldenResourceDeck().appendToBottom(card2);
        assertFalse(commonBoard.deckEmptinessCheck());

        // decks empty
        commonBoard.getGoldenResourceDeck().pickFromTop();
        assertTrue(commonBoard.deckEmptinessCheck());

        // both decks not empty
        commonBoard.getResourceDeck().appendToBottom(card);
        commonBoard.getGoldenResourceDeck().appendToBottom(card2);
        assertFalse(commonBoard.deckEmptinessCheck());

        // decks empty
        commonBoard.getResourceDeck().pickFromTop();
        commonBoard.getGoldenResourceDeck().pickFromTop();
        assertTrue(commonBoard.deckEmptinessCheck());

    }

    /**
     * This method tests the pickFromResourcePlate method of the CommonBoard class
     * It creates a new CommonBoard object, adds a resource card to the resource plate at index 0,
     * picks the card from the resource plate at index 0 and checks if the picked card is not null
     * and has the same card element as the added card
     */
    @Test
    public void testPickFromResourcePlate() {
        CommonBoard commonBoard = new CommonBoard();
        ArrayList<CardElements> exampleCardElements = new ArrayList<>();
        exampleCardElements.add(CardElements.FUNGI);
        exampleCardElements.add(CardElements.NONE);
        exampleCardElements.add(CardElements.FUNGI);
        exampleCardElements.add(CardElements.NONE);
        ResourceCard card = new ResourceCard(1,CardElements.FUNGI,exampleCardElements);
        commonBoard.getResourceDeck().appendToBottom(card);
        commonBoard.addCardToResourcePlate(0);
        ResourceCard pickedCard = commonBoard.pickFromResourcePlate(0);
        assertNotNull(pickedCard);
        assertEquals(card.getCardElement(), pickedCard.getCardElement());
    }

    /**
     * This method tests the pickFromGoldenPlate method of the CommonBoard class.
     * It creates a new CommonBoard object, adds a gold card to the golden plate at the index 1,
     * picks the card from the golden plate at the specified index, and checks if the picked card is not null
     * and has the same card element as the added card
     */
    @Test
    public void testPickFromGoldenPlate() {
        CommonBoard commonBoard = new CommonBoard();
        ArrayList<CardElements> exampleCardElements = new ArrayList<>();
        exampleCardElements.add(CardElements.FUNGI);
        exampleCardElements.add(CardElements.NONE);
        exampleCardElements.add(CardElements.FUNGI);
        exampleCardElements.add(CardElements.NONE);
        ArrayList<CardElements> exampleRequires = new ArrayList<>();
        exampleRequires.add(CardElements.FUNGI);
        exampleRequires.add(CardElements.FUNGI);
        exampleRequires.add(CardElements.ANIMAL);
        GoldResourceCard card = new GoldResourceCard(1, CardElements.ANIMAL, exampleCardElements, 2, exampleRequires, new ObjectScoreType());
        commonBoard.getGoldenResourceDeck().appendToBottom(card);
        commonBoard.addCardToGoldenPlate(1);
        GoldResourceCard pickedCard = commonBoard.pickFromGoldenPlate(1);
        assertNotNull(pickedCard);
        assertEquals(card.getCardElement(), pickedCard.getCardElement());
    }


    /**
     * Test the behavior of the addCardToResourcePlate method when the resource plate is empty
     * It creates a new instance of CommonBoard and adds a resource card to the resource plate at index 0
     * It then picks a card from the resource plate at index 0 and checks if the picked card is null
     */
    @Test
    public void testAddCardToResourcePlateEmpty() {
        CommonBoard commonBoard = new CommonBoard();
        commonBoard.addCardToResourcePlate(1);
        ResourceCard addedCard = commonBoard.pickFromResourcePlate(0);
        assertNull(addedCard);
    }

    /**
     * This method tests the behavior of the addCardToResourcePlate method when the resource plate is not empty
     * It creates a new instance of CommonBoard and adds a resource card to the resource plate at index 0
     * It then picks a card from the resource plate at index 0 and checks if the picked card is not null and
     * has the same card element as the added card
     */
    @Test
    public void testAddCardToResourcePlateNotEmpty() {
        CommonBoard commonBoard = new CommonBoard();
        ArrayList<CardElements> exampleCardElements = new ArrayList<>();
        exampleCardElements.add(CardElements.FUNGI);
        exampleCardElements.add(CardElements.NONE);
        exampleCardElements.add(CardElements.FUNGI);
        exampleCardElements.add(CardElements.NONE);
        ResourceCard card = new ResourceCard(1,CardElements.FUNGI,exampleCardElements);
        commonBoard.getResourceDeck().appendToBottom(card);
        commonBoard.addCardToResourcePlate(0);
        ResourceCard addedCard = commonBoard.pickFromResourcePlate(0);
        assertNotNull(addedCard);
        assertEquals(card.getCardElement(), addedCard.getCardElement());
    }

    /**
     * This method tests the addCardToGoldenPlate method of the CommonBoard class when the golden plate is empty
     * It creates a new instance of CommonBoard, adds a gold card to the golden plate at index 0
     * and checks if the added card is null when picked from the golden plate at index 0
     */
    @Test
    public void testAddCardToGoldenPlateEmpty() {
        CommonBoard commonBoard = new CommonBoard();
        commonBoard.addCardToGoldenPlate(1);
        GoldResourceCard addedCard = commonBoard.pickFromGoldenPlate(0);
        assertNull(addedCard);
    }

    /**
     * This method tests the behavior of the addCardToGoldenPlate method when the resource plate is not empty
     * It creates a new instance of CommonBoard and adds a gold card to the golden plate at index 0
     * It then picks a card from the golden plate at index 0 and checks if the picked card is not null and
     * has the same card element as the added card
     */
    @Test
    public void testAddCardToGoldenPlateNotEmpty() {
        CommonBoard commonBoard = new CommonBoard();
        ArrayList<CardElements> exampleCardElements = new ArrayList<>();
        exampleCardElements.add(CardElements.FUNGI);
        exampleCardElements.add(CardElements.NONE);
        exampleCardElements.add(CardElements.FUNGI);
        exampleCardElements.add(CardElements.NONE);
        ArrayList<CardElements> exampleRequires = new ArrayList<>();
        exampleRequires.add(CardElements.FUNGI);
        exampleRequires.add(CardElements.FUNGI);
        exampleRequires.add(CardElements.ANIMAL);
        GoldResourceCard card = new GoldResourceCard(1,CardElements.ANIMAL,exampleCardElements,2,exampleRequires, new ObjectScoreType());
        commonBoard.getGoldenResourceDeck().appendToBottom(card);
        commonBoard.addCardToGoldenPlate(1);
        GoldResourceCard addedCard = commonBoard.pickFromGoldenPlate(1);
        assertNotNull(addedCard);
        assertEquals(card.getCardElement(), addedCard.getCardElement());
    }

}
