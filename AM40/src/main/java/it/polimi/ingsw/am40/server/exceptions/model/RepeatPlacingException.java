package it.polimi.ingsw.am40.server.exceptions.model;


import it.polimi.ingsw.am40.server.exceptions.ModelException;

/**
 * Exception that makes the client repeat placing after something went wrong during placing
 */
public class RepeatPlacingException extends ModelException {

    /**
     * Constructor for the RepeatPlacingException
     */
    public RepeatPlacingException() {
        super("FAILED_PLACING");
    }
}
