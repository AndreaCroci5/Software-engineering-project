package it.polimi.ingsw.am40.client.network;

public class NetworkServerCutOffException extends Exception {

    public NetworkServerCutOffException() {
        super("Server cut off the connection!");
    }
}