package it.polimi.ingsw.am40.client.ClientMessages.activeMessages.flow;

import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.data.active.flow.ChangeTurnRequestData;

/**
 * This message is sent by the client after he finished is turn
 */
public class ChangeTurnRequestMessage extends Message {

    /**
     * It's the name of the active client
     */
    private final String clientNickname;

    /**
     * Constructor for the ChangeTurnMessage
     * @param nickname is the name of the active client
     */
    public ChangeTurnRequestMessage(String nickname) {
        super("CHANGE_TURN",nickname);
        this.clientNickname = nickname;
    }

    /**
     * This method is used to convert the internal message of the client in
     * a data that is the object that is going through the network
     * @return the data that is going to the network
     */
    public Data messageToData() {
        return new ChangeTurnRequestData(this.clientNickname);
    }

}

