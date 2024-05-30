package it.polimi.ingsw.am40.client.ClientMessages.activeMessages.flow;

import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.data.active.flow.CreateRequestData;

public class CreateRequestMessage extends Message {

    /**
     * It's the number of player that the client wants in the new game he created
     */
    private final int numOfPlayer;

    /**
     * Constructor for the CreateMessage
     * This message is the request of the client of creating a new game
     * @param numOfPlayer it's the number of player that the client wants in the new game he created
     */
    public CreateRequestMessage(int numOfPlayer) {
        super("CREATE_GAME");
        this.numOfPlayer = numOfPlayer;
    }

    /**
     * This method is used to convert the internal message of the client in
     * a data that is the object that is going through the network
     * @return the data that is going to the network
     */
    public CreateRequestData messageToData() {
        return new CreateRequestData(this.numOfPlayer);
    }

}
