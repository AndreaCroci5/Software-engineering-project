package it.polimi.ingsw.am40.client.ClientMessages;

import it.polimi.ingsw.am40.data.ClientDisconnectedData;
import it.polimi.ingsw.am40.data.Data;

public class ClientDisconnectedMessage extends Message{

    public ClientDisconnectedMessage(String nickname) {
        super("CLIENT_DISCONNECTED", nickname);
    }

    public Data messageToData() {
        return new ClientDisconnectedData(this.getNickname());
    }
}
