package it.polimi.ingsw.am40.client.network.States.activeStates;

import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.network.ClientContext;
import it.polimi.ingsw.am40.client.network.State;

public class ActiveDrawState implements State {

    /**
     * In this state the client choose which card he wants to draw from the common board
     * A DrawMessage with user input will be sent back
     * @param context is the context of the client with his view and network choices
     */
    @Override
    public void execute(ClientContext context) {
        // execute the state by asking the user the input the game need
        Message response = context.getClientView().askDraw();

        // send back the message with user input
        context.getClientNetwork().send(response);
    }
}

