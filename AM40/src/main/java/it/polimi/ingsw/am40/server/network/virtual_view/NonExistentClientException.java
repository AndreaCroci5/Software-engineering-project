package it.polimi.ingsw.am40.server.network.virtual_view;


/**
 * Exception that occurs when the reference to a client doesn't exist.
 */
public class NonExistentClientException extends Exception{
    public NonExistentClientException(){
        super("The client doesn't exist or its reference is null!");
    }
}
