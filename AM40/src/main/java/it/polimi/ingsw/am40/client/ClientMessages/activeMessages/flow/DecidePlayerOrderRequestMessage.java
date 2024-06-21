package it.polimi.ingsw.am40.client.ClientMessages.activeMessages.flow;

import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.data.active.firstRound.PlayersOrderRequestData;

public class DecidePlayerOrderRequestMessage extends Message {

    private final String clientNickname;

    public DecidePlayerOrderRequestMessage(String nickname) {
        super("PLAYER_ORDER",nickname);
        this.clientNickname = nickname;
    }

    public Data messageToData() {
        return new PlayersOrderRequestData(this.clientNickname);
    }
}

