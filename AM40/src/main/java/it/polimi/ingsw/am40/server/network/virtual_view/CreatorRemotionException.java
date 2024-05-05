package it.polimi.ingsw.am40.server.network.virtual_view;

/**
 * Exception that occurs when something is trying to remove the creator from a party
 */
public class CreatorRemotionException extends Exception {
    public CreatorRemotionException(){
        super("The client is the creator!");
    }
}
