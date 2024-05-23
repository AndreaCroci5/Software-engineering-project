package it.polimi.ingsw.am40.client.ClientMessages.activeMessages;

import it.polimi.ingsw.am40.client.ClientMessages.Message;

public class TokenMessage extends Message {

    private String token;

    public TokenMessage(String description, String token) {
        super("TOKEN_SELECTION");
        this.token = token;
    }
}
