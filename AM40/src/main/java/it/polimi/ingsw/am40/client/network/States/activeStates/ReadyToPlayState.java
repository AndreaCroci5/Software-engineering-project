package it.polimi.ingsw.am40.client.network.States.activeStates;

import it.polimi.ingsw.am40.client.ClientMessages.activeMessages.flow.ReadyToPlayMessage;
import it.polimi.ingsw.am40.client.network.ClientContext;
import it.polimi.ingsw.am40.client.network.State;

public class ReadyToPlayState implements State {

    /**
     * In this state the client send a message to the server that is ready to play
     * The server will respond with a StartingGame message if the number of players it's okay
     * @param context is the context of the client with his view and his network communication protocol
     */
    @Override
    public void execute(ClientContext context) {

        // Display waiting for player
        context.getClientView().displayWaitingForPlayers();

        // send the message to the server in order to start the game
        context.getClientNetwork().send(new ReadyToPlayMessage());
    }
}
