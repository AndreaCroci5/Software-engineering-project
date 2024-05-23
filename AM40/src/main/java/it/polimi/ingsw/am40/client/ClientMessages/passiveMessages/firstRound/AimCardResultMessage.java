package it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.firstRound;

import it.polimi.ingsw.am40.client.ClientMessages.Message;

public class AimCardResultMessage extends Message {
    private String clientNickname;

    private int aimCardIdChosen;

    public AimCardResultMessage(String clientNickname, int aimCardIdChosen) {
        super("AIM_CARD_SELECTED");
        this.clientNickname = clientNickname;
        this.aimCardIdChosen = aimCardIdChosen;
    }
}
