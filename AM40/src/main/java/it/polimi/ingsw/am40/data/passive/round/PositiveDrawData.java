package it.polimi.ingsw.am40.data.passive.round;

import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.Action;

@JsonTypeName("POSITIVE_DRAW")
public class PositiveDrawData extends Data {
    //ATTRIBUTES+
    /** Nickname of the Player that has just made the draw*/
    private String playerNickname;

    /** ID of the Card drawn*/
    private int cardDrawnID;

    /** ID of the new Card that replaced the CardDrawn*/
    private int cardReplacedID;

    /** This attribute refers to the location from where the Player has drawn a Card:
     * (0) plate, (1) plate, (2) deck
     */
    private int replacePosition;

    /** ID of the new Card on the top of the Deck*/
    private int cardOnTopOfDeck;

    //CONSTRUCTOR

    //Logic constructor for subclasses
    public PositiveDrawData(String playerNickname, int cardDrawnID, int cardReplacedID, int replacePosition, int cardOnTopOfDeck) {
        super("POSITIVE_DRAW");
        this.playerNickname = playerNickname;
        this.cardDrawnID = cardDrawnID;
        this.cardReplacedID = cardReplacedID;
        this.replacePosition = replacePosition;
        this.cardOnTopOfDeck = cardOnTopOfDeck;
    }
    //Json constructor
    public PositiveDrawData(){

    }

    //PUBLIC METHODS


    public Action onServer(){
        return null;
    }

    public Message onClient() {
        return null;
    }
}
