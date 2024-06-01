package it.polimi.ingsw.am40.client.network.States.activeStates;

import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.network.ClientContext;
import it.polimi.ingsw.am40.client.network.State;


public class ActiveAimCardChoiceState implements State {

    /**
     * In this state the client select which aimCard he wants between the two options
     * A AimCardChoiceMessage with the user input will be sent back
     * @param context is the context of the client with his view and network choices
     */
    @Override
    public void execute(ClientContext context) {
        Message response = context.getClientView().askAimCard();
        context.getClientNetwork().send(response);
    }
}
