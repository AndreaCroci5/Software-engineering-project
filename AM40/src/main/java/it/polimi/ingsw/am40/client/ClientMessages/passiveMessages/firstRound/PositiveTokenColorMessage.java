package it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.firstRound;

import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.ClientMessages.activeMessages.flow.ChangeTurnRequestMessage;
import it.polimi.ingsw.am40.client.network.Client;
import it.polimi.ingsw.am40.client.network.States.passiveStates.PassiveDealCardsState;

/**
 * This message is sent by the server to the client, and it is an okay on the choice
 * of the client token
 */
public class PositiveTokenColorMessage extends Message {

    /**
     * The nickname of the active client
     */
    private final String clientNickname;

    /**
     * It's the color of the token choose by the client
     */
    private final String token;

    /**
     * Constructor for the PositiveTokenColorMessage
     * @param clientNickname is the name of the active client
     * @param token is the color of the token choose by the client
     */
    public PositiveTokenColorMessage(String clientNickname, String token) {
        super("POSITIVE_TOKEN_COLOR",clientNickname);
        this.clientNickname = clientNickname;
        this.token = token;
    }

    /**
     * It updates the small model of the client with the token he chooses
     * It sets the next state of the client state machine
     * @param context is the context of the client with his view and his network communication protocol
     */
    public void process(Client context) {

        context.getViewManager().showPositiveTokenColor(this.clientNickname, this.token);

        if (this.clientNickname.equalsIgnoreCase(context.getNickname())) {
            context.getSmallModel().setToken(this.token);
            context.setState(new PassiveDealCardsState());
            context.getNetworkManager().send(new ChangeTurnRequestMessage(context.getNickname()));
        }
    }
}
