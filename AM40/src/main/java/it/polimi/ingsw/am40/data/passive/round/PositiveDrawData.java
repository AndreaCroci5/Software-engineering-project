package it.polimi.ingsw.am40.data.passive.round;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.network.RMI.RemoteInterfaceClient;
import it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.round.PositiveDrawMessage;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.network.RMI.RemoteInterfaceServer;

import java.rmi.RemoteException;

@JsonTypeName("POSITIVE_DRAW")
public class PositiveDrawData extends Data {
    //ATTRIBUTES+
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

    @JsonCreator
    public PositiveDrawData(@JsonProperty("nickname") String nickname,
                            @JsonProperty("cardDrawnID") int cardDrawnID,
                            @JsonProperty("cardReplaceID") int cardReplacedID,
                            @JsonProperty("replacePosition") int replacePosition,
                            @JsonProperty("cardOnTopOfDeck") int cardOnTopOfDeck) {
        super("POSITIVE_DRAW", nickname);
        this.cardDrawnID = cardDrawnID;
        this.cardReplacedID = cardReplacedID;
        this.replacePosition = replacePosition;
        this.cardOnTopOfDeck = cardOnTopOfDeck;
    }

    public int getCardDrawnID() {
        return cardDrawnID;
    }

    public void setCardDrawnID(int cardDrawnID) {
        this.cardDrawnID = cardDrawnID;
    }

    public int getCardReplacedID() {
        return cardReplacedID;
    }

    public void setCardReplacedID(int cardReplacedID) {
        this.cardReplacedID = cardReplacedID;
    }

    public int getReplacePosition() {
        return replacePosition;
    }

    public void setReplacePosition(int replacePosition) {
        this.replacePosition = replacePosition;
    }

    public int getCardOnTopOfDeck() {
        return cardOnTopOfDeck;
    }

    public void setCardOnTopOfDeck(int cardOnTopOfDeck) {
        this.cardOnTopOfDeck = cardOnTopOfDeck;
    }

    //PUBLIC METHODS


    public Action onServer(){
        return null;
    }

    public Message onClient() {
        return new PositiveDrawMessage(this.getNickname(),this.cardDrawnID,this.cardReplacedID,this.replacePosition,this.cardOnTopOfDeck);
    }

    /**
     * Method which calls the right RMI interface method for each data (with override)
     * @param skeleton the client remote interface. Null if data active
     * @param stub the server remote interface. Null if data passive
     */
    @Override
    public void doRMI(RemoteInterfaceClient skeleton, RemoteInterfaceServer stub){
        try {
            skeleton.positiveDrawPassiveRound(this);
        } catch (RemoteException e) {
            System.out.println("RMI call went wrong! message: " + e);
        }
    }
}
