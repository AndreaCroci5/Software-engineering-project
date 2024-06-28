package it.polimi.ingsw.am40.client.network;

/**
 * Exception throws in case of a server cut off
 */
public class NetworkServerCutOffException extends Exception {

    /**
     * Constructor for the NetworkServerCutOffException
     */
    public NetworkServerCutOffException() {
        super("Server cut off the connection!");
    }
}