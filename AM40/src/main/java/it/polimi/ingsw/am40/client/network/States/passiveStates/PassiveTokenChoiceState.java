package it.polimi.ingsw.am40.client.network.States.passiveStates;

import it.polimi.ingsw.am40.client.network.ClientContext;
import it.polimi.ingsw.am40.client.network.State;

public class PassiveTokenChoiceState implements State {

    /**
     * In this state the client wait for the others to choose their token
     * @param context is the context of the client with his view and his network communication protocol
     */
    @Override
    public void execute(ClientContext context) {
        context.getClientView().showNotYouTurn();
    }
}
