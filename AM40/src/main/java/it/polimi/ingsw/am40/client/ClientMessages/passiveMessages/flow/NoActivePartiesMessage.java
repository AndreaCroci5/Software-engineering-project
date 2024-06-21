package it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.flow;

import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.network.Client;
import it.polimi.ingsw.am40.client.network.States.activeStates.SetUpState;

public class NoActivePartiesMessage extends Message {

    public NoActivePartiesMessage(String nickname) {
        super("NO_ACTIVE_PARTIES", nickname);
    }

    public void process(Client context) {
        if (context.getNickname().equalsIgnoreCase(this.getNickname())) {
            context.getViewManager().showNoActiveParties();
            context.setState(new SetUpState());
        }
    }
}
