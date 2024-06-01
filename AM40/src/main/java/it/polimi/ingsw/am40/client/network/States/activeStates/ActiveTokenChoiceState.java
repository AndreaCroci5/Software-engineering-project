package it.polimi.ingsw.am40.client.network.States.activeStates;

import it.polimi.ingsw.am40.client.ClientMessages.activeMessages.firstRound.TokenChoiceMessage;
import it.polimi.ingsw.am40.client.network.ClientContext;
import it.polimi.ingsw.am40.client.network.State;

public class ActiveTokenChoiceState implements State {

    /**
     * In this state the client choose which is the token he wants
     * @param context is the context of the client with his view and network choices
     */
    @Override
    public void execute(ClientContext context) {
        TokenChoiceMessage response = context.getClientView().askToken();
        context.getClientNetwork().send(response);
    }
}
