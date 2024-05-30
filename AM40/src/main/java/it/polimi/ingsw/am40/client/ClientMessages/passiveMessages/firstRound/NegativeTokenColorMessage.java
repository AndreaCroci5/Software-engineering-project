package it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.firstRound;

import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.network.ClientContext;
import it.polimi.ingsw.am40.client.network.States.activeStates.ActiveTokenChoiceState;

public class NegativeTokenColorMessage  extends Message {

    /**
     * The nickname of the active client
     */
    private final String clientNickname;

    public NegativeTokenColorMessage(String clientNickname) {
        super("TOKEN_ERROR");
        this.clientNickname = clientNickname;
    }

    /**
     * It sets the new state of the client that is still the one of the token because
     * there was an error in his choice
     * @param context is the context of the client with his view and his network communication protocol
     */
    public void process(ClientContext context) {
        if (this.clientNickname.equalsIgnoreCase(context.getNickname())) {
            context.getClientView().displayError();
            context.setState(new ActiveTokenChoiceState());
        }
    }
}
