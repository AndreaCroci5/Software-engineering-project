package it.polimi.ingsw.am40.client.ClientMessages;

import it.polimi.ingsw.am40.client.network.Client;
import it.polimi.ingsw.am40.data.Data;

/**
 * This represents the abstract idea of a Message that is how the parts of the client communicate
 */
public abstract class Message {

    /**
     * It's the name of the active client
     */
    private final String nickname;
    /**
     * It's the description of which information the message transport
     */
    private final String description;

    /**
     * Getter for nickname
     * @return the nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Getter for the description
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Constructor for messages
     * @param description it's the description of which information the message transport
     */
    public Message(String description, String nickname) {
        this.description = description;
        this.nickname = nickname;
    }

    /**
     * It is used to update the state in the client state machine
     * @param context is the context of the client with his view and his network communication protocol
     */
    public void process(Client context) {}

    /**
     * This method is used to convert an internal client message in a data object
     * that is going to the network
     * @return a new Data associated to the Message
     */
    public Data messageToData() {
        return null;
    }

}
