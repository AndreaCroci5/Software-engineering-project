package it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.flow;

import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.network.Client;
import it.polimi.ingsw.am40.client.network.States.activeStates.SetUpState;


public class FailedGameIDMessage extends Message {

    public FailedGameIDMessage(String nickname) {
        super("FAILED_GAME_ID", nickname);
    }

    public void process(Client context) {
        if (context.getNickname().equalsIgnoreCase(this.getNickname())) {
            context.getViewManager().showFailedGameID();
            context.setState(new SetUpState());
        }
    }
}
