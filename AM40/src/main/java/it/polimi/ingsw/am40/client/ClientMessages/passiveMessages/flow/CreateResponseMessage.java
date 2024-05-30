package it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.flow;

import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.network.ClientContext;
import it.polimi.ingsw.am40.client.network.States.activeStates.ReadyToPlayState;

public class CreateResponseMessage extends Message {

    /**
     * It's the name of the active client
     */
    private final String clientNickname;

    /**
     * This message is sent by the server, and it's an ack that he had created a new game
     * @param clientNickname is the name of the active client
     */
    public CreateResponseMessage(String clientNickname) {
        super("POSITIVE_CREATE");
        this.clientNickname = clientNickname;
    }

    /**
     * It sets the next state of the client state machine
     * @param context is the context of the client with his view and his network communication protocol
     */
    public void process(ClientContext context) {
        if (this.clientNickname.equalsIgnoreCase(context.getNickname())) {
            context.setState(new ReadyToPlayState());
        }
    }
}
