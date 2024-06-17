package it.polimi.ingsw.am40.server.actions.passive.round;

import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.data.passive.round.PositiveDrawData;
import it.polimi.ingsw.am40.server.actions.Action;

/**
 * This class serves as a mean to notify to the VirtualView which then will notify the client by using the Network interface.
 * This event is called when a draw phase goes without throwing exceptions, that means that the draw is correctly made.
 * This class carries the information about the consequences that the last DrawAction has made
 */
public class PositiveDrawAction extends Action {
    //ATTRIBUTES
    /** ID of the Card drawn*/
    private final int cardDrawnID;

    /** ID of the new Card that replaced the CardDrawn*/
    private final int cardReplacedID;

    /** This attribute refers to the location from where the Player has drawn a Card:
     * (0) plate, (1) plate, (2) deck
     */
    private final int replacePosition;

    /** ID of the new Card on the top of the Deck*/
    private final int cardOnTopOfDeck;

    public PositiveDrawAction(String nickname, int gameID, int playerID, int cardDrawnID, int cardReplacedID, int replacePosition, int cardOnTopOfDeck) {
        super("POSITIVE_DRAW", nickname, gameID, playerID);
        this.cardDrawnID = cardDrawnID;
        this.cardReplacedID = cardReplacedID;
        this.replacePosition = replacePosition;
        this.cardOnTopOfDeck = cardOnTopOfDeck;
    }

    public Data dataCreator() {
        return new PositiveDrawData(this.getNickname(), this.cardDrawnID, this.cardReplacedID, this.replacePosition, this.cardOnTopOfDeck);
    }
}
