package it.polimi.ingsw.am40.server.network.virtual_view;

/**
 * Exception that occurs when a client is already logged in a Party.
 */
public class ClientAlreadyLoggedException extends Exception {
    public ClientAlreadyLoggedException(){
        super("The client is already logged in a Party!");
    }

}
