package it.polimi.ingsw.am40.client.ClientMessages.activeMessages;

import it.polimi.ingsw.am40.client.ClientMessages.Message;

public class DrawMessage extends Message {
    private String choice;
    private int selection;

    public DrawMessage(String choice, int selection) {
        super("DRAW");
        this.choice = choice;
        this.selection = selection;
    }
}
