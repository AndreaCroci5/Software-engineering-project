package it.polimi.ingsw.am40.client.ClientMessages.activeMessages;

import it.polimi.ingsw.am40.client.ClientMessages.Message;

public class AimCardChoiceMessage extends Message {

    private int cardID;

    public AimCardChoiceMessage(int cardID) {
        super("AIM_CARD_SELECTION");
        this.cardID = cardID;
    }
}
