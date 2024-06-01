package it.polimi.ingsw.am40.client.network.States.activeStates;

import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.network.ClientContext;
import it.polimi.ingsw.am40.client.network.State;

public class SetUpState implements State {

    /**
     * In the set-up state the client decide if joining an existing game or create a new one
     * The message send back will be a CreateMessage or a JoinMessage
     * @param context is the context of the client with his view and network choices
     */
    @Override
    public void execute(ClientContext context) {

        // ask the user to create or join a game
        Message response = context.getClientView().displayInitialisation();

        // send back the client response
        context.getClientNetwork().send(response);
    }
}
