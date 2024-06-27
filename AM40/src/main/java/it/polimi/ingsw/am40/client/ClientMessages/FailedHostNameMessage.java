package it.polimi.ingsw.am40.client.ClientMessages;

import it.polimi.ingsw.am40.client.network.Client;

/**
 * This message is sent by the server if the client choose a host name that is already logged in the server
 */
public class FailedHostNameMessage extends Message{

    /**
     * Constructor for the FailedHostNameMessage
     * @param nickname is the name of the active client
     */
    public FailedHostNameMessage(String nickname) {
        super("FAILED_HOST_NAME", nickname);
    }

    /**
     * Close the application
     * @param context is the context of the client with his view and his network communication protocol
     */
    public void process(Client context) {
        System.out.println(">This nickname is not available " + this.getNickname());
        System.out.println(">The application is closing...");
        System.exit(0);
    }
}
