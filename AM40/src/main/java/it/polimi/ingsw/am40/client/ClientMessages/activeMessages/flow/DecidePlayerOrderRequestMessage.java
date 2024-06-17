package it.polimi.ingsw.am40.client.ClientMessages.activeMessages.flow;

import it.polimi.ingsw.am40.client.ClientMessages.Message;

public class DecidePlayerOrderRequestMessage extends Message {

    public DecidePlayerOrderRequestMessage(String nickname) {
        super("PLAYER_ORDER",nickname);
    }
}
