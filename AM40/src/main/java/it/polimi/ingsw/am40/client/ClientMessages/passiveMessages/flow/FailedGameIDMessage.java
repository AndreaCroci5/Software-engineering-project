package it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.flow;

import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.network.Client;
import it.polimi.ingsw.am40.client.network.States.activeStates.SetUpState;

/**
 * This message is sent by the server when something went wrong when joining a match
 */
public class FailedGameIDMessage extends Message {

    /**
     * Constructor for the FailedGameIDMessage
     * @param nickname is the name of the active client
     */
    public FailedGameIDMessage(String nickname) {
        super("FAILED_GAME_ID", nickname);
    }

    /**
     * It sets as the new state the previous state due to the joining error
     * @param context is the context of the client with his view and his network communication protocol
     */
    public void process(Client context) {
        if (context.getNickname().equalsIgnoreCase(this.getNickname())) {
            context.getViewManager().showFailedGameID();
            context.setState(new SetUpState());
        }
    }
}
