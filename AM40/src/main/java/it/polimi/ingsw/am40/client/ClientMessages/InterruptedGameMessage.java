package it.polimi.ingsw.am40.client.ClientMessages;

import it.polimi.ingsw.am40.client.network.Client;
import it.polimi.ingsw.am40.client.network.States.InterruptedGameState;

/**
 * This message is sent by the server to interrupt the game
 */
public class InterruptedGameMessage extends Message {

    /**
     * The nickname of the active client
     */
    private final String clientNickname;


    /**
     * Constructor for the InterruptedGameMessage
     * @param clientNickname is the name of the active client
     */
    public InterruptedGameMessage(String clientNickname) {
        super("INTERRUPTED_GAME",clientNickname);
        this.clientNickname = clientNickname;
    }

    /**
     * It shows the two aim cards for the user choice
     * It sets the next state of the client state machine
     * @param context is the context of the client with his view and his network communication protocol
     */
    public void process(Client context) {
        context.getViewManager().displayInterruptedGame();
        context.setState(new InterruptedGameState());
    }
}