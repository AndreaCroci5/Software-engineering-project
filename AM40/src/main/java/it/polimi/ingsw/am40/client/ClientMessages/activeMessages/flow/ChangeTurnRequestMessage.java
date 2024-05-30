package it.polimi.ingsw.am40.client.ClientMessages.activeMessages.flow;

import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.data.active.flow.ChangeTurnRequestData;

public class ChangeTurnRequestMessage extends Message {

    /**
     * Constructor for the ChangeTurnMessage
     * This message is sent by the client after he finished the draw phase
     */
    public ChangeTurnRequestMessage() {
        super("CHANGE_TURN");
    }

    /**
     * This method is used to convert the internal message of the client in
     * a data that is the object that is going through the network
     * @return the data that is going to the network
     */
    public ChangeTurnRequestData messageToData() {
        return new ChangeTurnRequestData();
    }

}
