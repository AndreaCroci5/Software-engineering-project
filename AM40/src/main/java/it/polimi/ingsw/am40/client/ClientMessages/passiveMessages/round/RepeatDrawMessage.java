package it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.round;

import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.network.Client;
import it.polimi.ingsw.am40.client.network.States.activeStates.ActiveDrawChoiceState;

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
        super("REPEAT_DRAW",clientNickname);
        this.clientNickname = clientNickname;
    }

    /**
     * It sets the next state of the client state machine that is still a draw state
     * because there was an error
     * @param context is the context of the client with his view and his network communication protocol
     */
    public void process(Client context) {
        if (context.getNickname().equalsIgnoreCase(clientNickname)) {
            context.getViewManager().displayError();
            context.setState(new ActiveDrawChoiceState());
        }
    }
}
