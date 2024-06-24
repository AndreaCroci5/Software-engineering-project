package it.polimi.ingsw.am40.client.network.States.passiveStates;


import it.polimi.ingsw.am40.client.network.Client;
import it.polimi.ingsw.am40.client.network.State;


public class PassivePlacingState implements State {

    /**
     * In this state the client wait for the others to place and draw a card
     * @param context is the context of the client with his view and his network communication protocol
     */
    @Override
    public void execute(Client context) {

    }

    /**
     * In this state the client can only see information, is not his turn
     * @param context is the context of the client with his view and his network communication protocol
     * @param input is the input of the client
     */
    @Override
    public void checkInput(Client context, String input) {
        context.getViewManager().showNotYouTurn();
        context.getViewManager().showPossibleInputs();
    }

}