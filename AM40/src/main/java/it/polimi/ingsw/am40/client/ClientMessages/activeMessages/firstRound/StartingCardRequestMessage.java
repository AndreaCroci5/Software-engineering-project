package it.polimi.ingsw.am40.client.ClientMessages.activeMessages.firstRound;

import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.data.active.firstRound.StartingCardRequestData;

public class StartingCardRequestMessage extends Message {

    private final String clientNickname;

    /**
     * This message is a request from the client to the server for the starting card
     */
    public StartingCardRequestMessage(String nickname) {
        super("STARTING_CARD_REQUEST",nickname);
        this.clientNickname = nickname;
    }

    /**
     * This method is used to convert the internal message of the client in
     * a data that is the object that is going through the network
     * @return the data that is going to the network
     */
    public Data messageToData() {
        return new StartingCardRequestData(this.clientNickname);
    }
}

