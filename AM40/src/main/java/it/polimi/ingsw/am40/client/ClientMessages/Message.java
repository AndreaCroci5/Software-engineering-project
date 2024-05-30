package it.polimi.ingsw.am40.client.ClientMessages;

import it.polimi.ingsw.am40.client.network.ClientContext;
import it.polimi.ingsw.am40.data.Data;

public abstract class Message {
    /**
     * It's the description of which information the message transport
     */
    private final String description;

    /**
     * Constructor for messages
     * @param description it's the description of which information the message transport
     */
    public Message(String description) {
        this.description = description;
    }

    /**
     * It is used to update the state in the client state machine
     * @param context is the context of the client with his view and his network communication protocol
     */
    public void process(ClientContext context) {}

    /**
     * This method is used to convert an internal client message in a data object
     * that is going to the network
     * @return a new Data associated to the Message
     */
    public Data messageToData() {
        return null;
    }

}
