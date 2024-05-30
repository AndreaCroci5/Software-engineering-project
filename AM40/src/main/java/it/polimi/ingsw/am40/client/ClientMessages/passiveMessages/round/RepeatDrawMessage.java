package it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.round;

import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.network.ClientContext;
import it.polimi.ingsw.am40.client.network.States.activeStates.ActiveDrawState;

public class RepeatDrawMessage extends Message {

    /**
     * It's the name of the active client
     */
    private final String clientNickname;

    /**
     * It's a message that tells the client that something went wrong during the place
     * @param clientNickname is the name of the active client
     */
    public RepeatDrawMessage(String clientNickname) {
        super("REPEAT_DRAW");
        this.clientNickname = clientNickname;
    }

    /**
     * It sets the next state of the client state machine that is still a draw state
     * because there was an error
     * @param context is the context of the client with his view and his network communication protocol
     */
    public void process(ClientContext context) {
        if (context.getNickname().equalsIgnoreCase(clientNickname)) {
            context.getClientView().displayError();
            context.setState(new ActiveDrawState());
        }
    }
}
