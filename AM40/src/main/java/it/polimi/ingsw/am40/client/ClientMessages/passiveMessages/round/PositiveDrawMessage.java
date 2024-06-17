package it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.round;

import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.network.Client;

public class PositiveDrawMessage extends Message {

    private final String clientNickname;

    private final int cardDrawnID;

    private final int cardReplacedID;

    private final int replacePosition;

    private final int cardOnTopOfDeck;

    public PositiveDrawMessage(String clientNickname, int cardDrawnID, int cardReplacedID, int replacePosition, int cardOnTopOfDeck) {
        super("POSITIVE_DRAW",clientNickname);
        this.clientNickname = clientNickname;
        this.cardDrawnID = cardDrawnID;
        this.cardReplacedID = cardReplacedID;
        this.replacePosition = replacePosition;
        this.cardOnTopOfDeck = cardOnTopOfDeck;
    }

    public void process(Client clientContext) {
        // UPDATE SMALL MODEL
        // SHOW INFORMATION
        // SET NEW STATE
    }
}
