package it.polimi.ingsw.am40.client.network.States.activeStates;

import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.network.ClientContext;
import it.polimi.ingsw.am40.client.network.State;

public class ActivePlacingState implements State {

    /**
     * In this state the client choose which card he wants to play and where
     * A PlacingMessage with the input of the user will be sent back
     * @param context is the context of the client with his view and network choices
     */
    @Override
    public void execute(ClientContext context) {
        Message response = context.getClientView().askPlacing();
        context.getClientNetwork().send(response);
    }
}
