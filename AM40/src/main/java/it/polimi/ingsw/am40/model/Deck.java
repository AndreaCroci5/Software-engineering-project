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
        this.cards = new ArrayDeque<>();
        this.cards.addAll(cardList);
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
     * Requirements: deck cannot be null or empty
     * @return the first element of the Deck
     */
    public T pickFromTop() {
        return cards.pollFirst();
    }

    /**
     * This method adds the element at the bottom of the Deck
     * Requirements: cardToAppend cannot be null, deck can be empty but not null
     * @param cardToAppend is the element to add at the bottom of the Deck
     */
    public void appendToBottom(T cardToAppend) {
        cards.addLast(cardToAppend);
    }

    /**
     * This method performs a shuffle on the deck
     * Requirements: cards cannot be null or empty
     */
    public void shuffle() {
            List<T> temp = new ArrayList<>(List.copyOf(cards));
            Collections.shuffle(temp);
            cards.clear();
            cards.addAll(temp);
    }

    /**
     * This method calculate the size of the deck
     * Requirements: cards cannot be null or empty
     * @return the number of cards in the deck
     */
    public int size() {
        return cards.size();
    }

}
