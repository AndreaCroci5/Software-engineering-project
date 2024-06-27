package it.polimi.ingsw.am40.client.network.States.passiveStates;

import it.polimi.ingsw.am40.client.network.Client;
import it.polimi.ingsw.am40.client.network.State;

/**
 * In this state the client wait for the others to choose their aim card
 */
public class PassiveAimCardChoiceState implements State {

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
