package it.polimi.ingsw.am40.client.network.States.activeStates;

import it.polimi.ingsw.am40.client.ClientMessages.activeMessages.flow.GameIdChoiceMessage;
import it.polimi.ingsw.am40.client.network.ClientContext;
import it.polimi.ingsw.am40.client.network.State;


public class ChooseGameState implements State {

    /**
     * This state ask the client which game he wants to join after showing him
     * the list of all possible game he can join
     * @param context is the context of the client with his view and his network communication protocol
     */
    @Override
    public void execute(ClientContext context) {
        GameIdChoiceMessage response = context.getClientView().askGameToJoin();
        context.getClientNetwork().send(response);
    }
}
