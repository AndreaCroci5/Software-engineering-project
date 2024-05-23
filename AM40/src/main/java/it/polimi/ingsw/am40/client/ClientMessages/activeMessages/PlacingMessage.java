package it.polimi.ingsw.am40.client.ClientMessages.activeMessages;

import it.polimi.ingsw.am40.client.ClientMessages.Message;

public class PlacingMessage extends Message {

    private String handCard;
    private int xPos;
    private int yPos;
    private String face;

    public PlacingMessage(String handCard, int xPos, int yPos, String face) {
        super("PLACING");
        this.handCard = handCard;
        this.xPos = xPos;
        this.yPos = yPos;
        this.face = face;
    }
}
