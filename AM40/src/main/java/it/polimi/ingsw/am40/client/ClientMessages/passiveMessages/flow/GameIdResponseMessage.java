package it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.flow;

import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.network.ClientContext;
import it.polimi.ingsw.am40.client.network.States.activeStates.ReadyToPlayState;

public class GameIdResponseMessage extends Message {

    /**
     * This message is sent by the server, and it's an ack that client joined the game he chose
     */
    public GameIdResponseMessage() {
        super("POSITIVE_GAME_CHOICE");
    }

    /**
     * It sets the next state of the client state machine
     * @param context is the context of the client with his view and his network communication protocol
     */
    public void process(ClientContext context) {
        context.setState(new ReadyToPlayState());
    }

}
