package it.polimi.ingsw.am40.client.ClientMessages.activeMessages;

import it.polimi.ingsw.am40.client.ClientMessages.Message;

public class StarterCardChoiceMessage extends Message {

    private int cardID;

    public StarterCardChoiceMessage(int cardID) {
        super("STARTING_CARD_SELECTION");
        this.cardID = cardID;
    }
}
