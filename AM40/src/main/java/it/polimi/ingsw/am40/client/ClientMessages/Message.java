package it.polimi.ingsw.am40.client.ClientMessages;

public abstract class Message {
    private final String description;

    public Message(String description) {
        this.description = description;
    }

}
