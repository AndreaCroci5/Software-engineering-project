package it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.flow;

import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.network.Client;
import it.polimi.ingsw.am40.client.network.States.activeStates.SetUpState;

public class FailedCreationMessage extends Message {
    public FailedCreationMessage(String nickname) {
        super("FAILED_CREATION", nickname);
    }

    public void process(Client context) {
        if (context.getNickname().equalsIgnoreCase(this.getNickname())) {
            context.setState(new SetUpState());
        }
    }

}
