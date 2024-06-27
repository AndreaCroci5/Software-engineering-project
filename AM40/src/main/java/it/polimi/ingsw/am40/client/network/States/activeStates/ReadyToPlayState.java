package it.polimi.ingsw.am40.client.network.States.activeStates;

import it.polimi.ingsw.am40.client.network.Client;
import it.polimi.ingsw.am40.client.network.State;

/**
 * In this state the client send a message to the server that is ready to play
 * The server will respond with a StartingGame message if the number of players it's okay
 */
public class ReadyToPlayState implements State {

    /**
     * Execute for this state
     * @param context is the context of the client with his view and his network communication protocol
     */
    @Override
    public void execute(Client context) {

    }

    /**
     * In this state the client doesn't have to write anything
     * @param context is the context of the client with his view and his network communication protocol
     * @param input is the input of the client
     */
    @Override
    public void checkInput(Client context, String input) {

    }
}
