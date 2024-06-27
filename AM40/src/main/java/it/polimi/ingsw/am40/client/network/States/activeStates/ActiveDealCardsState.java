package it.polimi.ingsw.am40.client.network.States.activeStates;

import it.polimi.ingsw.am40.client.ClientMessages.activeMessages.firstRound.DealCardsRequestMessage;
import it.polimi.ingsw.am40.client.network.Client;
import it.polimi.ingsw.am40.client.network.State;

/**
 * In this state the client ask the server for his 3 cards
 */
public class ActiveDealCardsState implements State {

    /**
     * Send a dealCardRequest
     * @param context is the context of the client with his view and his network communication protocol
     */
    @Override
    public void execute(Client context) {
        context.getNetworkManager().send(new DealCardsRequestMessage(context.getNickname()));
    }

    /**
     * In this state the client doesn't need to write anything
     * @param context is the context of the client with his view and his network communication protocol
     * @param input is the input of the client
     */
    @Override
    public void checkInput(Client context,String input) {
        // In this state there are no inputs
    }
}
