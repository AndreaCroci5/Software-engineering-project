package it.polimi.ingsw.am40.server.network.virtual_view;


/**
 * Exception that occurs when the reference to a party doesn't exist.
 */
public class NonExistentPartyException extends Exception{
    public NonExistentPartyException(){
        super("The party doesn't exist or its reference is null!");
    }
}
