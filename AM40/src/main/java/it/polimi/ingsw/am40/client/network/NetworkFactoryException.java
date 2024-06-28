package it.polimi.ingsw.am40.client.network;

/**
 * Exception that occurs when it isn't possible to create the concrete network manager object.
 */
public class NetworkFactoryException extends Exception {

    /**
     * Constructor for the NetworkFactoryException
     */
    public NetworkFactoryException(){
        super("Can't create the network manager!");
    }
}
