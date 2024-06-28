package it.polimi.ingsw.am40.server.exceptions.model;

import it.polimi.ingsw.am40.server.exceptions.ModelException;

/**
 * Exception that occurs if something goes wrong during the change turn
 */
public class TurnException extends ModelException {

    /**
     * Constructor for the TurnException
     */
    public TurnException() {
        super("FAILED_TURN_CHANGE");
    }
}
