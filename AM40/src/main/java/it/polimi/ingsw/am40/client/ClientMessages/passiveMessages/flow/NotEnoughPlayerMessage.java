package it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.flow;

import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.network.Client;

public class NotEnoughPlayerMessage extends Message {

    /**
     * This message is used to tell the player that the game is not starting and he has to wait other players
     * @param nickname is the name of the active client
     */
    public NotEnoughPlayerMessage(String nickname) {
        super("NOT_ENOUGH_PLAYER",nickname);
    }

    /**
     * Nothing to process with this message
     * @param context is the context of the client with his view and his network communication protocol
     */
    @Override
    public void process(Client context) {
    }
}
