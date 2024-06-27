package it.polimi.ingsw.am40.client.network.States.activeStates;

import it.polimi.ingsw.am40.client.network.Client;
import it.polimi.ingsw.am40.client.network.State;

/**
 * In this state the client set his username
 */
public class UsernameChoiceState implements State {

    /**
     * Asks for the initialisation
     * @param context is the context of the client with his view and his network communication protocol
     */
    @Override
    public void execute(Client context) {
        context.getViewManager().displayInitialisation();
    }

    /**
     * Checks if the nickname is the same as the one that he used to log in
     * @param context is the context of the client with his view and his network communication protocol
     * @param input is the input of the client
     */
    @Override
    public void checkInput(Client context, String input) {
        if (!input.equalsIgnoreCase(context.getNetworkManager().getHostName())) {
            System.out.println(">You must choose the username that you used to log in ");
        }
        else {
            context.setNickname(input);
            context.setState(new SetUpState());
            context.getCurrentState().execute(context);
        }
    }
}
