package it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.firstRound;

import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.network.Client;
import it.polimi.ingsw.am40.client.network.States.activeStates.ActiveTokenChoiceState;

import java.util.List;

public class TokenInfoMessage extends Message {

    /**
     * It's the name of the active client
     */
    private final String clientNickname;

    /**
     * It's the list of the color of the possible tokens that the client can choose
     */
    private final List<String> tokens;

    /**
     * This message contains the list of the color of the possible tokens that the client can choose
     * @param clientNickname is the name of the active client
     * @param tokens is the list of the color of the possible tokens that the client can choose
     */
    public TokenInfoMessage(String clientNickname, List<String> tokens) {
        super("TOKEN_INFO",clientNickname);
        this.clientNickname = clientNickname;
        this.tokens = tokens;
    }

    /**
     * It shows the client the possible tokens he can choose
     * It sets the next state of the client state machine
     * @param context is the context of the client with his view and his network communication protocol
     */
    public void process(Client context) {
        if (context.getNickname().equalsIgnoreCase(this.clientNickname)) {
            context.getViewManager().displayPossibleTokens(this.tokens);
            context.setState(new ActiveTokenChoiceState(this.tokens));
        }
    }
}
