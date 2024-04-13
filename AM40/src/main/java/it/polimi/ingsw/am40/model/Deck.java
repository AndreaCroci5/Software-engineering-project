package it.polimi.ingsw.am40.model;
import java.util.*;

public class Deck<T> {

    /**
     * Deque of cards that represent the decks on the CommonBoard
     */
    private Deque<T> cards;


    // CONSTRUCTOR METHOD

    /**
     * Constructor method for Deck class
     * (There is also a constructor overloading)
     */
    public Deck(){
        this.cards = new ArrayDeque<>();
    }

    /**
     * Constructor method for Deck class
     * Requirements : cardList cannot be null
     * @param cardList List of cards of the type of the Deck to initialize a deck
     */
    public Deck(List<T> cardList){
        if (cardList == null) {
            throw new IllegalArgumentException("Card list cannot be null");
        }
        this.cards = new ArrayDeque<>();
        this.cards.addAll(cardList);
    }

    /**
     * This method checks if the deck is empty
     * @return true in case the deck is empty, false otherwise
     */
    public boolean isEmpty() {
        return (cards == null || cards.isEmpty());
    }

    /**
     * This method gets the first element of the Deck and removes it
     * Requirements: deck cannot be null or empty
     * @return the first element of the Deck
     */
    public T pickFromTop() {
        if(cards == null || cards.isEmpty()) {
            throw new IllegalStateException("Deck is empty or null");
        }
        return cards.pollFirst();
    }

    /**
     * This method adds the element at the bottom of the Deck
     * Requirements: cardToAppend cannot be null
     * @param cardToAppend is the element to add at the bottom of the Deck
     */
    public void appendToBottom(T cardToAppend) {
        if (cardToAppend == null) {
            throw new IllegalArgumentException("Card to append cannot be null");
        }
        cards.addLast(cardToAppend);
    }

    /**
     * This method performs a shuffle on the deck
     */
    public void shuffle() {
        if (!cards.isEmpty()) {
            List<T> temp = new ArrayList<>(List.copyOf(cards));
            Collections.shuffle(temp);
            cards.clear();
            cards.addAll(temp);
        }
    }
}
