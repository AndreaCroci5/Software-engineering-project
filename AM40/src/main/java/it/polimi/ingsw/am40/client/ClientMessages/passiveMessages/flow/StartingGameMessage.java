package it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.flow;

import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.network.Client;

public class StartingGameMessage extends Message {

    // TO FIX

    public StartingGameMessage(String clientNickname) {
        super("STARTING_GAME",clientNickname);
    }


    public void process(Client context) {
        // TO FIX
    }
}
