package it.polimi.ingsw.am40.model;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the Deck class
 */
public class DeckTest {

    /**
     * Test case to verify the behavior of the Deck constructor when a null card list is provided
     * An IllegalArgumentException is expected to be thrown because requirements are no null list
     */
    @Test
    public void testDeckConstructorNullList() {
        assertThrows(IllegalArgumentException.class, () -> new Deck<>(null));
    }

    /**
     * This method is used to test the constructor of the Deck class when a card list is provided
     * It asserts that the deck is not empty
     */
    @Test
    public void testDeckConstructorWithCardList() {
        List<ResourceCard> cardList = new ArrayList<>();
        ArrayList<CardElements> exampleCardElements = new ArrayList<>();
        exampleCardElements.add(CardElements.FUNGI);
        exampleCardElements.add(CardElements.NONE);
        exampleCardElements.add(CardElements.FUNGI);
        exampleCardElements.add(CardElements.NONE);
        ResourceCard card = new ResourceCard(1,CardElements.FUNGI,exampleCardElements);
        cardList.add(card);
        exampleCardElements.set(0, CardElements.PLANT);
        exampleCardElements.set(3, CardElements.ANIMAL);
        exampleCardElements.set(2, CardElements.NONE);
        ResourceCard card2 = new ResourceCard(2, CardElements.PLANT, exampleCardElements);
        cardList.add(card2);
        Deck<ResourceCard> deck = new Deck<>(cardList);
        assertFalse(deck.isEmpty());
    }

    /**
     * Test case to verify if the deck just created is empty
     */
    @Test
    public void testIsEmpty() {
        Deck<ResourceCard> deck = new Deck<>();
        assertTrue(deck.isEmpty());
    }

    /**
     * Test case to verify that the deck is not empty after appending a card to the bottom
     */
    @Test
    public void testDeckNonEmpty() {
        Deck<ResourceCard> deck = new Deck<>();
        ArrayList<CardElements> exampleCardElements = new ArrayList<>();
        exampleCardElements.add(CardElements.FUNGI);
        exampleCardElements.add(CardElements.NONE);
        exampleCardElements.add(CardElements.FUNGI);
        exampleCardElements.add(CardElements.NONE);
        ResourceCard card = new ResourceCard(1,CardElements.FUNGI,exampleCardElements);
        deck.appendToBottom(card);
        assertFalse(deck.isEmpty());
    }

    /**
     * This method is used to test the behavior of picking a card from the top of an empty deck
     * An IllegalStateException is thrown when attempting to pick a card from the top
     * because requirements are not empty deck
     */
    @Test
    public void testPickFromTopEmptyDeck() {
        Deck<ResourceCard> deck = new Deck<>();
        assertThrows(IllegalStateException.class, deck::pickFromTop);
    }

    /**
     * This method is used to test the pickFromTop method of the Deck class
     * It creates a deck of resource cards, picks a card from the top
     * and checks if the picked card has the same card element as the first card added to the deck
     */
    @Test
    public void testPickFromTop() {
        List<ResourceCard> cardList = new ArrayList<>();
        ArrayList<CardElements> exampleCardElements = new ArrayList<>();
        exampleCardElements.add(CardElements.FUNGI);
        exampleCardElements.add(CardElements.NONE);
        exampleCardElements.add(CardElements.FUNGI);
        exampleCardElements.add(CardElements.NONE);
        ResourceCard card = new ResourceCard(1,CardElements.FUNGI,exampleCardElements);
        cardList.add(card);
        exampleCardElements.set(0, CardElements.PLANT);
        exampleCardElements.set(3, CardElements.ANIMAL);
        exampleCardElements.set(2, CardElements.NONE);
        ResourceCard card2 = new ResourceCard(2, CardElements.PLANT, exampleCardElements);
        cardList.add(card2);
        Deck<ResourceCard> deck = new Deck<>(cardList);
        ResourceCard topCard = deck.pickFromTop();
        assertEquals(card.getCardElement(), topCard.getCardElement());
        assertFalse(deck.isEmpty());
        ResourceCard topCard2 = deck.pickFromTop();
        assertEquals(card2.getCardElement(), topCard2.getCardElement());
        assertTrue(deck.isEmpty());
    }

    /**
     * This method is used to test the behavior of the appendToBottom method in the Deck class
     * when a null card is provided
     * It should throw an `IllegalArgumentException` because the requirements are not null card
     */
    @Test
    public void testAppendToBottomNullCard() {
        Deck<ResourceCard> deck = new Deck<>();
        assertThrows(IllegalArgumentException.class, () -> deck.appendToBottom(null));
    }

    /**
     * This method is used to test the appendToBottom method in the Deck class
     * It asserts that the deck is not empty
     * It updates the deck by providing another card
     * It asserts that the cardElements of the card added at the bottom is not equal
     * to the cardElements of the card picked from the top
     */
    @Test
    public void testAppendToBottom() {
        Deck<ResourceCard> deck = new Deck<>();
        ArrayList<CardElements> exampleCardElements = new ArrayList<>();
        exampleCardElements.add(CardElements.FUNGI);
        exampleCardElements.add(CardElements.NONE);
        exampleCardElements.add(CardElements.FUNGI);
        exampleCardElements.add(CardElements.NONE);
        ResourceCard card = new ResourceCard(1,CardElements.FUNGI,exampleCardElements);
        deck.appendToBottom(card);
        assertFalse(deck.isEmpty());
        exampleCardElements.set(0, CardElements.PLANT);
        exampleCardElements.set(3, CardElements.ANIMAL);
        exampleCardElements.set(2, CardElements.NONE);
        ResourceCard card2 = new ResourceCard(2, CardElements.PLANT, exampleCardElements);
        deck.appendToBottom(card2);
        assertNotEquals(card2.getCardElement(), deck.pickFromTop().getCardElement());
    }

    /**
     * This method is used to test the shuffle method of the Deck class when the deck is empty
     * Nothing happens because requirements require non-empty deck
     */
    @Test
    public void testShuffleEmptyDeck() {
        Deck<ResourceCard> deck = new Deck<>();
        deck.shuffle(); // assert no exception is thrown
    }

    /**
     * Test case to verify the behavior of the shuffle() method
     * when the deck is non-empty
     * It asserts that the shuffled deck is not equal to the original deck
     */
    @Test
    public void testShuffleNonEmptyDeck() {
        List<ResourceCard> cardList = new ArrayList<>();
        ArrayList<CardElements> exampleCardElements = new ArrayList<>();
        exampleCardElements.add(CardElements.FUNGI);
        exampleCardElements.add(CardElements.NONE);
        exampleCardElements.add(CardElements.FUNGI);
        exampleCardElements.add(CardElements.NONE);
        ResourceCard card = new ResourceCard(1,CardElements.FUNGI,exampleCardElements);
        cardList.add(card);
        exampleCardElements.set(0, CardElements.PLANT);
        exampleCardElements.set(3, CardElements.ANIMAL);
        exampleCardElements.set(2, CardElements.NONE);
        ResourceCard card2 = new ResourceCard(2, CardElements.PLANT, exampleCardElements);
        cardList.add(card2);
        exampleCardElements.set(3, CardElements.PLANT);
        exampleCardElements.set(2, CardElements.ANIMAL);
        exampleCardElements.set(1, CardElements.NONE);
        ResourceCard card3 = new ResourceCard(2, CardElements.PLANT, exampleCardElements);
        cardList.add(card3);
        Deck<ResourceCard> deck = new Deck<>(cardList);
        Deck<ResourceCard> original_deck = new Deck<>(cardList);
        deck.shuffle();
        assertFalse(deck.isEmpty());
        assertNotEquals(original_deck, deck);
    }

    /**
     * Combination of previous tests
     */
    @Test
    public void testAppendShufflePick() {
        ArrayList<CardElements> exampleCardElements = new ArrayList<>();
        exampleCardElements.add(CardElements.FUNGI);
        exampleCardElements.add(CardElements.NONE);
        exampleCardElements.add(CardElements.FUNGI);
        exampleCardElements.add(CardElements.NONE);
        ResourceCard card = new ResourceCard(1, CardElements.FUNGI, exampleCardElements);

        exampleCardElements.set(0, CardElements.PLANT);
        exampleCardElements.set(3, CardElements.ANIMAL);
        exampleCardElements.set(2, CardElements.NONE);
        ResourceCard card2 = new ResourceCard(2, CardElements.PLANT, exampleCardElements);

        exampleCardElements.set(3, CardElements.PLANT);
        exampleCardElements.set(2, CardElements.ANIMAL);
        exampleCardElements.set(1, CardElements.NONE);
        ResourceCard card3 = new ResourceCard(2, CardElements.PLANT, exampleCardElements);

        Deck<ResourceCard> deck = new Deck<>();
        deck.appendToBottom(card);
        deck.appendToBottom(card2);
        deck.appendToBottom(card3);
        Deck<ResourceCard> original_deck = new Deck<>();
        original_deck.appendToBottom(card);
        original_deck.appendToBottom(card2);
        original_deck.appendToBottom(card3);
        deck.shuffle();
        ResourceCard topCard = original_deck.pickFromTop();

        assertFalse(deck.isEmpty());
        assertNotEquals(original_deck, deck);
        assertEquals(card.getCardElement(), topCard.getCardElement());
    }

}

