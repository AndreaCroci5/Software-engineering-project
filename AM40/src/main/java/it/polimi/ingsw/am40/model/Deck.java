package it.polimi.ingsw.am40.model;
import java.util.ArrayDeque;
import java.util.Deque;

public class Deck<T> {

    /**
     * Deque of cards that represent the decks on the CommonBoard
     */
    private Deque<T> cards;

    // TO DO
    public Deck(Deque<T> cards) {
        this.cards = cards;
    }

    // TO DO
    public void iniResourceDeck() {
    }

    // TO DO
    public void iniGoldenResourceDeck() {
    }

    // TO DO
    public void iniAimDeck() {
    }

    // TO DO
    public void iniStartingDeck() {
    }

    /**
     * This method checks if the deck is empty
     * @return true in case the deck is empty, false otherwise
     */
    public boolean isEmpty() {
        return cards.isEmpty();
    }

    /**
     * This method gets the first element of the Deck and removes it
     * @return the first element of the Deck
     */
    public T pickFromTop() {
        if(cards.isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        return cards.pollFirst();
    }

    /**
     * This method adds the element at the bottom of the Deck
     * @param cardToAppend is the element to add at the bottom of the Deck
     */
    public void appendToBottom(T cardToAppend) {
        cards.addLast(cardToAppend);
    }

    // TO DO
    public void shuffle() {
    }
}
