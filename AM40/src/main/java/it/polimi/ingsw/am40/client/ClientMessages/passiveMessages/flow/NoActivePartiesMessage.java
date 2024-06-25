package it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.flow;

import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.network.Client;
import it.polimi.ingsw.am40.client.network.States.activeStates.SetUpState;

public class NoActivePartiesMessage extends Message {

    /**
     * This message is sent by the server when a client tries to join a game but there are no game available to be joined
     * @param nickname is the name of the active client
     */
    public NoActivePartiesMessage(String nickname) {
        super("NO_ACTIVE_PARTIES", nickname);
    }

    /**
     * It shows the client that there are no game available to be joined so he has to create a new game
     * @param context is the context of the client with his view and his network communication protocol
     */
    public void process(Client context) {
        if (context.getNickname().equalsIgnoreCase(this.getNickname())) {
            context.getViewManager().showNoActiveParties();
            context.setState(new SetUpState());
        }
    }
}
