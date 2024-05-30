package it.polimi.ingsw.am40.client.ClientMessages.activeMessages.flow;

import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.data.active.flow.JoinRequestData;

public class JoinRequestMessage extends Message {

    /**
     * Constructor fot the JoinMessage
     * This message it's the request of the client to join an existing game
     */
    public JoinRequestMessage() {
        super("JOIN_GAME");
    }

    /**
     * This method is used to convert the internal message of the client in
     * a data that is the object that is going through the network
     * @return the data that is going to the network
     */
    public JoinRequestData messageToData() {
        return new JoinRequestData();
    }


}
