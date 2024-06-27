package it.polimi.ingsw.am40.client.ClientMessages;

import it.polimi.ingsw.am40.client.network.Client;

public class FailedHostNameMessage extends Message{

    public FailedHostNameMessage(String nickname) {
        super("FAILED_HOST_NAME", nickname);
    }

    public void process(Client context) {
        System.out.println(">This nickname is not available " + this.getNickname());
        System.out.println(">The application is closing...");
        System.exit(0);
    }
}
