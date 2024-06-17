package it.polimi.ingsw.am40.client.ClientMessages.activeMessages.flow;

import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.data.active.flow.ReadyToPlayData;

public class ReadyToPlayMessage extends Message {

    /**
     * This message is used for the client to let the server know he wants to start the game
     * The server will respond with start game or waiting for other player
     */
    public ReadyToPlayMessage(String nickname) {
        super("READY_TO_PLAY",nickname);
    }

    /**
     * This method is used to convert the internal message of the client in
     * a data that is the object that is going through the network
     * @return the data that is going to the network
     */
    public ReadyToPlayData messageToData() {
        return new ReadyToPlayData();
    }
}
