package it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.flow;

import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.network.Client;
import it.polimi.ingsw.am40.client.network.States.activeStates.SetUpState;

/**
 * This message is sent by the server went something went wrong with the creation of the game
 */
public class FailedCreationMessage extends Message {

    /**
     * Constructor for FailedCreationMessage
     * @param nickname is the name of the active client
     */
    public FailedCreationMessage(String nickname) {
        super("FAILED_CREATION", nickname);
    }

    /**
     * It sets as the new state the previous state due to the creation error
     * @param context is the context of the client with his view and his network communication protocol
     */
    public void process(Client context) {
        if (context.getNickname().equalsIgnoreCase(this.getNickname())) {
            context.setState(new SetUpState());
        }
    }

}
