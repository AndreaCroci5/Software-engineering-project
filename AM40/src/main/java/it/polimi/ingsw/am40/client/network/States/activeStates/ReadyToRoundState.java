package it.polimi.ingsw.am40.client.network.States.activeStates;

import it.polimi.ingsw.am40.client.network.Client;
import it.polimi.ingsw.am40.client.network.State;

/**
 * This state is used to wait for all the client doing their first round of the game
 */
public class ReadyToRoundState implements State {

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
        context.getViewManager().showNotYouTurn();
    }
}
