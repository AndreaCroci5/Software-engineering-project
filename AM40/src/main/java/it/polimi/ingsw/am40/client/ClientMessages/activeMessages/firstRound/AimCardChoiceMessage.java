package it.polimi.ingsw.am40.client.ClientMessages.activeMessages.firstRound;

import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.data.active.firstRound.AimCardChoiceData;

/**
 * This message contains the ID of the personal aim card chosen by the client
 */
public class AimCardChoiceMessage extends Message {

    /**
     * This is the name of the active client
     */
    private final String clientNickname;
    /**
     * This attribute represents the ID of the AimCard chosen by the Client
     */
    private final int cardID;

    /**
     * Constructor for the AimCardChoiceMessage
     * @param nickname is the name of the active client
     * @param cardID it's the ID associated at the card choose by the client
     */
    public AimCardChoiceMessage(String nickname, int cardID) {
        super("AIM_CARD_SELECTION",nickname);
        this.clientNickname = nickname;
        this.cardID = cardID;
    }

    /**
     * This method is used to convert the internal message of the client in
     * a data that is the object that is going through the network
     * @return the data that is going to the network
     */
    public Data messageToData() {
        return new AimCardChoiceData(this.clientNickname,this.cardID);
    }
}

