package it.polimi.ingsw.am40.server.exceptions;

/**
 * Class from which all the exception on the model extends
 */
public class ModelException extends Exception {

    /**
     * Constructor for the ModelException
     * @param message is the message of the exception
     */
    public ModelException(String message) {
        super(message);
    }
}
