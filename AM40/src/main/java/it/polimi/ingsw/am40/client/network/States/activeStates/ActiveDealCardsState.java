package it.polimi.ingsw.am40.client.network.States.activeStates;

import it.polimi.ingsw.am40.client.ClientMessages.activeMessages.firstRound.DealCardsRequestMessage;
import it.polimi.ingsw.am40.client.network.ClientContext;
import it.polimi.ingsw.am40.client.network.State;

public class ActiveDealCardsState implements State {

    /**
     * In this state the client ask the server for his 3 cards
     * @param context is the context of the client with his view and his network communication protocol
     */
    @Override
    public void execute(ClientContext context) {
        context.getClientNetwork().send(new DealCardsRequestMessage());
    }
}
