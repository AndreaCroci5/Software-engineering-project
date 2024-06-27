package it.polimi.ingsw.am40.client.network;

/**
 * Exception which represents a generic problem with the connection to the server
 */
public class NetworkProblemException extends Exception {

    /**
     * Constructor with generic error message
     */
    public NetworkProblemException(){
        super("Problem with the server connection!");
    }

    /**
     * Constructor with custom error message
     * @param message the custom message
     */
    public NetworkProblemException(String message){
        super(message);
    }
}
