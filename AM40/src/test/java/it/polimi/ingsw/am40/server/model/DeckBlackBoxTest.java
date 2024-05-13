package it.polimi.ingsw.am40.server.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Test class with Black-box unit tests for the Deck class
 */

public class DeckBlackBoxTest {


    /**
     * This method test if the deck is empty after his creation
     */

    @Test
    public void testIsEmpty(){
        Deck<ResourceCard> deck = new Deck<>();
        ArrayList<CardElements> cardElements = new ArrayList<>();
        cardElements.add(CardElements.ANIMAL);
        cardElements.add(CardElements.INSECT);
        cardElements.add(CardElements.FUNGI);
        cardElements.add(CardElements.NONE);
        ResourceCard card1 = new ResourceCard(1, CardElements.ANIMAL, cardElements);
        deck.appendToBottom(card1);
        assertFalse(deck.isEmpty());
        deck.pickFromTop();
        assertTrue(deck.isEmpty());
    }


    /**
     * Method testPickFromTop tests that the deck is no empty and
     * that the card last card added is the card that is drawn
     */

    @Test
    public void testPickFromTop(){
        List<ResourceCard> cardList = new ArrayList<>();
        ArrayList<CardElements> cardElements = new ArrayList<>();
        cardElements.add(CardElements.ANIMAL);
        ResourceCard card = new ResourceCard(1, CardElements.ANIMAL, cardElements);
        cardList.add(card);
        Deck<ResourceCard> deck = new Deck<>(cardList);
        ResourceCard topCard = deck.pickFromTop();

        assertEquals(card.getCardElement(),topCard.getCardElement());
        assertTrue(deck.isEmpty());

        ResourceCard secondTopCard = deck.pickFromTop();

        assertNull(secondTopCard);
        assertTrue(deck.isEmpty());
    }


    /**
     * Method testSizeAfterShuffle tests the size of the deck that is not empty
     * after shuffling and check that the size remain unchanged
     */

    @Test
    public void testSizeAfterShuffle(){
        List<ResourceCard> cardList = new ArrayList<>();
        ArrayList<CardElements> cardElements = new ArrayList<>();
        cardElements.add(CardElements.ANIMAL);
        cardElements.add(CardElements.PLANT);
        cardElements.add(CardElements.NONE);
        cardElements.add(CardElements.FUNGI);

        ResourceCard card1 = new ResourceCard(1, CardElements.ANIMAL, cardElements);
        cardList.add(card1);
        ResourceCard card2 = new ResourceCard(2, CardElements.FUNGI, cardElements);
        cardList.add(card2);
        ResourceCard card3 = new ResourceCard(3, CardElements.PLANT, cardElements);
        cardList.add(card3);

        Deck<ResourceCard> deck = new Deck<>(cardList);

        assertEquals(3, deck.size());
        deck.shuffle();
        assertEquals(3, deck.size());
    }


    /**
     * Method testAppendToBottom tests if the card is correctly added to the deck
     */

    @Test
    public void testAppendToBottom(){
        ArrayList<CardElements> cardElements = new ArrayList<>();
        Deck<ResourceCard> deck = new Deck<>();
        cardElements.add(CardElements.ANIMAL);
        cardElements.add(CardElements.INSECT);
        cardElements.add(CardElements.FUNGI);
        cardElements.add(CardElements.NONE);
        ResourceCard card1 = new ResourceCard(1, CardElements.ANIMAL, cardElements);
        deck.appendToBottom(card1);
        assertEquals(1,deck.size());
        cardElements.set(2, CardElements.ANIMAL);
        cardElements.set(1, CardElements.INSECT);
        cardElements.set(0, CardElements.NONE);
        cardElements.set(3, CardElements.FUNGI);
        ResourceCard card2 = new ResourceCard(2, CardElements.INSECT, cardElements);
        deck.appendToBottom(card2);
        assertEquals(2, deck.size());
        assertEquals(card1.getCardElement(), deck.pickFromTop().getCardElement());
        assertEquals(card2.getCardElement(), deck.pickFromTop().getCardElement());
    }


    /**
     * Method testShuffle tests if after the shuffle the order of the cards is changed
     */

    @Test
    public void testShuffle(){
        List<ResourceCard> cardList = new ArrayList<>();
        ArrayList<CardElements> cardElements = new ArrayList<>();
        cardElements.add(CardElements.FUNGI);
        cardElements.add(CardElements.INSECT);
        cardElements.add(CardElements.ANIMAL);
        cardElements.add(CardElements.PLANT);

        ResourceCard card1 = new ResourceCard(1, CardElements.PLANT, cardElements);
        cardList.add(card1);
        ResourceCard card2 = new ResourceCard(2, CardElements.ANIMAL, cardElements);
        cardList.add(card2);
        ResourceCard card3 = new ResourceCard(3, CardElements.FUNGI, cardElements);
        cardList.add(card3);

        Deck<ResourceCard> initialDeck = new Deck<>(cardList);
        initialDeck.shuffle();
        Deck<ResourceCard> afterShuffleDeck = new Deck<>(cardList);
        assertNotEquals(initialDeck, afterShuffleDeck);
        assertFalse(initialDeck.isEmpty());
        assertFalse(afterShuffleDeck.isEmpty());
    }
}
