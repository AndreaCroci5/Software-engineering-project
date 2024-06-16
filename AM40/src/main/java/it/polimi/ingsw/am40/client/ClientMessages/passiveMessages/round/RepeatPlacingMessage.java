package it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.round;

import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.network.Client;
import it.polimi.ingsw.am40.client.network.States.activeStates.ActivePlacingState;


public class RepeatPlacingMessage extends Message {

    /**
     * It's the name of the active player
     */
    private final String clientNickname;

    /**
     * This message is used to tell the client that something went wrong during the placing phase
     * @param clientNickname is the name of the active client
     */
    public RepeatPlacingMessage(String clientNickname) {
        super("REPEAT_PLACING");
        this.clientNickname = clientNickname;
    }

    /**
     * It sets the next state of the client state machine that is still a place state
     * because there was an error
     * @param context is the context of the client with his view and his network communication protocol
     */
    public void process(Client context) {
        if (context.getNickname().equalsIgnoreCase(clientNickname)) {
            context.getViewManager().displayError();
            context.setState(new ActivePlacingState());
        }
    }
}
