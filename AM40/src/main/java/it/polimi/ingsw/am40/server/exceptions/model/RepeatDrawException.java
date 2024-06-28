package it.polimi.ingsw.am40.server.exceptions.model;

import it.polimi.ingsw.am40.server.exceptions.ModelException;

/**
 * Exception for repeating a draw after something went wrong
 */
public class RepeatDrawException extends ModelException {

    /**
     * Constructor for the RepeatDrawException
     */
    public RepeatDrawException() {
        super("FAILED_DRAW");
    }
}
