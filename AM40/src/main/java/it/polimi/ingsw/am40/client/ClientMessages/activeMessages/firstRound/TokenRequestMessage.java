package it.polimi.ingsw.am40.client.ClientMessages.activeMessages.firstRound;

import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.data.active.firstRound.TokenRequestData;

/**
 * This message is a request from the client to the server in which the client
 * asks the list of the token he can choose
 */
public class TokenRequestMessage extends Message {

    /**
     * It's the name of the active client
     */
    private final String clientNickname;

    /**
     * Constructor for TokenRequestMessage
     * @param nickname is the name of the active client
     */
    public TokenRequestMessage(String nickname) {
        super("TOKEN_REQUEST",nickname);
        this.clientNickname = nickname;
    }

    /**
     * This method is used to convert the internal message of the client in
     * a data that is the object that is going through the network
     * @return the data that is going to the network
     */
    public Data messageToData() {
        return new TokenRequestData(this.clientNickname);
    }

}

