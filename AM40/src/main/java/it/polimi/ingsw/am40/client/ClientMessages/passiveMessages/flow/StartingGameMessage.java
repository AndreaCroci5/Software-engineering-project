package it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.flow;

import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.network.ClientContext;
import it.polimi.ingsw.am40.client.network.States.activeStates.InitGameState;

public class StartingGameMessage extends Message {

    /**
     * This message used to let the client know that the game is started
     */
    public StartingGameMessage() {
        super("STARTING_GAME");
    }

    /**
     * It sets the next state of the client state machine
     * @param context is the context of the client with his view and his network communication protocol
     */
    public void process(ClientContext context) {
        context.setState(new InitGameState());
    }
}
