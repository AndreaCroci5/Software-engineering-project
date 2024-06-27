package it.polimi.ingsw.am40.client.ClientMessages;

import it.polimi.ingsw.am40.data.ClientDisconnectedData;
import it.polimi.ingsw.am40.data.Data;

/**
 * This message is sent by the client that quit the game
 */
public class ClientDisconnectedMessage extends Message{

    /**
     * Constructor for the ClientDisconnectedMessage
     * @param nickname is the name of the active client
     */
    public ClientDisconnectedMessage(String nickname) {
        super("CLIENT_DISCONNECTED", nickname);
    }

    /**
     * This method is used to convert the internal message of the client in
     * a data that is the object that is going through the network
     * @return the data that is going to the network
     */
    public Data messageToData() {
        return new ClientDisconnectedData(this.getNickname());
    }
}
