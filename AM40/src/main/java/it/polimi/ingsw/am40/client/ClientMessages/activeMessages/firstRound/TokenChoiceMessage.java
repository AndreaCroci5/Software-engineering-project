package it.polimi.ingsw.am40.client.ClientMessages.activeMessages.firstRound;

import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.data.active.firstRound.TokenChoiceData;

/**
 * This message it's the token choice from the client
 */
public class TokenChoiceMessage extends Message {

    /**
     * It's the name of the active client
     */
    private final String clientNickname;

    /**
     * It's the color of the token choose by the client
     */
    private final String token;


    /**
     * Constructor for the TokenChoiceMessage
     * @param nickname is the name of the active client
     * @param token it's the color of the token choose by the client
     */
    public TokenChoiceMessage(String nickname,String token) {
        super("TOKEN_SELECTION",nickname);
        this.clientNickname = nickname;
        this.token = token;
    }

    /**
     * This method is used to convert the internal message of the client in
     * a data that is the object that is going through the network
     * @return the data that is going to the network
     */
    public Data messageToData() {
        return new TokenChoiceData(this.clientNickname,this.token);
    }

}

