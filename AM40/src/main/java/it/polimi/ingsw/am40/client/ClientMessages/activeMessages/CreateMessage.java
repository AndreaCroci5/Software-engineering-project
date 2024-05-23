package it.polimi.ingsw.am40.client.ClientMessages.activeMessages;

import it.polimi.ingsw.am40.client.ClientMessages.Message;

public class CreateMessage extends Message {

    private int numOfPlayer;

    public CreateMessage(int numOfPlayer) {
        super("CREATE_GAME");
        this.numOfPlayer = numOfPlayer;
    }
}
