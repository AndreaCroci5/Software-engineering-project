package it.polimi.ingsw.am40.client.network.States.activeStates;

import it.polimi.ingsw.am40.client.ClientMessages.activeMessages.firstRound.StartingCardChoiceMessage;
import it.polimi.ingsw.am40.client.network.ClientContext;
import it.polimi.ingsw.am40.client.network.State;

public class ActiveStartingCardChoiceState implements State {

    /**
     * In this state after showing the client his starting card
     * he will choose if place this card face up or face down
     * @param context is the context of the client with his view and his network communication protocol
     */
    @Override
    public void execute(ClientContext context) {
        StartingCardChoiceMessage response = context.getClientView().askStartingCardFace();
        context.getClientNetwork().send(response);
    }
}
