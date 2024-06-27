package it.polimi.ingsw.am40.client.ClientMessages.activeMessages.firstRound;

import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.data.active.firstRound.AimCardRequestData;

/**
 * It's the message that ask the server to send the two aim cards
 * The client will choose which one of the two aim cards he wants
 */
public class AimCardRequestMessage extends Message {

    /**
     * It's the name of the active client
     */
    private final String clientNickname;

    /**
     * Constructor for AimCardRequestMessage
     * @param nickname is the name of the active client
     */
    public AimCardRequestMessage(String nickname) {
        super("AIM_CARD_REQUEST",nickname);
        this.clientNickname = nickname;
    }

    /**
     * This method is used to convert the internal message of the client in
     * a data that is the object that is going through the network
     * @return the data that is going to the network
     */
    public Data messageToData() {
        return new AimCardRequestData(this.clientNickname);
    }
}

