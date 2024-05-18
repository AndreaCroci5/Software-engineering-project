package it.polimi.ingsw.am40.server.actions.passive.round;

import it.polimi.ingsw.am40.server.actions.Action;

public class PositiveDrawAction extends Action {
    //ATTRIBUTES
    /** ID of the Card drawn*/
    int cardDrawnID;

    /** ID of the new Card that replaced the CardDrawn*/
    int cardReplacedID;

    /** This attribute refers to the location from where the Player has drawn a Card:
     * (0) plate, (1) plate, (2) deck
     */
    int replacePosition;

    /** ID of the new Card on the top of the Deck*/
    int cardOnTopOfDeck;

    public PositiveDrawAction(int gameID, int playerID, int cardDrawnID, int cardReplacedID, int replacePosition, int cardOnTopOfDeck) {
        super("POSITIVE_DRAW", gameID, playerID);
        this.cardDrawnID = cardDrawnID;
        this.cardReplacedID = cardReplacedID;
        this.replacePosition = replacePosition;
        this.cardOnTopOfDeck = cardOnTopOfDeck;
    }
}
