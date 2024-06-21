package it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.flow;

import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.network.Client;

public class NotEnoughPlayerMessage extends Message {

    public NotEnoughPlayerMessage(String nickname) {
        super("NOT_ENOUGH_PLAYER",nickname);
    }

    @Override
    public void process(Client context) {
        // nothing to do
    }
}
