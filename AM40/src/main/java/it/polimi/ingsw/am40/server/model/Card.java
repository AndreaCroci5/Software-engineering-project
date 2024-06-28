package it.polimi.ingsw.am40.server.model;

/**
 * The Card abstract class defines all the cards used in the game
 */


public abstract class Card {
    /**
     * Univocal identification number of a card
     */
    private final int cardID;


    /**
     * Constructor for Card
     * @param cardID Identification number of the card
     */
    public Card(int cardID){
        this.cardID = cardID;
    }

    /**
     * Getter dor cardID attribute
     * @return the ID of the card
     */
    public int getCardID() {
        return this.cardID;
    }
}
