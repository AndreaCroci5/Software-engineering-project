package it.polimi.ingsw.am40.client.ClientMessages.activeMessages.flow;

import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.data.active.firstRound.PlayersOrderRequestData;

/**
 * This message is sent to the server to asks for the player order for the rounds of the game
 */
public class DecidePlayerOrderRequestMessage extends Message {

    /**
     * It's the name of the active client
     */
    private final String clientNickname;

    /**
     * Constructor for the DecidePlayerOrderRequestMessage
     * @param nickname
     */
    public DecidePlayerOrderRequestMessage(String nickname) {
        super("PLAYER_ORDER",nickname);
        this.clientNickname = nickname;
    }

    /**
     * This method is used to convert the internal message of the client in
     * a data that is the object that is going through the network
     * @return the data that is going to the network
     */
    public Data messageToData() {
        return new PlayersOrderRequestData(this.clientNickname);
    }
}

