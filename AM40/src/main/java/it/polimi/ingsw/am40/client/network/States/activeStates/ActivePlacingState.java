package it.polimi.ingsw.am40.client.network.States.activeStates;

import it.polimi.ingsw.am40.client.network.Client;
import it.polimi.ingsw.am40.client.network.State;

public class ActivePlacingState implements State {

    /**
     * In this state the client choose which card he wants to play and where
     * A PlacingMessage with the input of the user will be sent back
     * @param context is the context of the client with his view and network choices
     */
    @Override
    public void execute(Client context) {
        // **TO FIX**
    }

    @Override
    public void checkInput(Client context,String input) {
        // **TO FIX**
    }
}
