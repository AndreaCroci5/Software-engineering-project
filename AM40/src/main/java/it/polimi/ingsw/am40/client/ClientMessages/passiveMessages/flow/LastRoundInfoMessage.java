package it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.flow;

import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.network.Client;

public class LastRoundInfoMessage extends Message {

    /**
     * It's the name of the active client
     */
    private final String clientNickname;

    /**
     * This message is used for letting the player know that a player has a score from
     * of more than 20 points or the plates are empty
     * so the final rounds are started
     * @param clientNickname is the name of the active player
     */
    public LastRoundInfoMessage(String clientNickname) {
        super("LAST_ROUNDS",clientNickname);
        this.clientNickname = clientNickname;
    }

    /**
     * It shows the message of the final rounds
     * @param context is the context of the client with his view and his network communication protocol
     */
    public void process(Client context) {
        context.getViewManager().displayLastRoundMessage(clientNickname);
    }
}
