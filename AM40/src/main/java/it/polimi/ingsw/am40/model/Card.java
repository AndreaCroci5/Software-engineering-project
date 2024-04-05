package it.polimi.ingsw.am40.model;

/**
 * The Card abstract class defines all the cards used in the game
 */


public abstract class Card {
    /**
     * Univoke identification number of a card
     */
    private int cardID;


    /**
     * Constructor for Card
     * @param cardID Identification number of the card
     */
    public Card(int cardID){
        this.cardID = cardID;
    }

    public int getCardID() {
        return this.cardID;
    }
}
