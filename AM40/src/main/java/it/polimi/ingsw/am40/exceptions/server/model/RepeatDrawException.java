package it.polimi.ingsw.am40.exceptions.server.model;

import it.polimi.ingsw.am40.exceptions.server.ModelException;

public class RepeatDrawException extends ModelException {
    public RepeatDrawException() {
        super("FAILED_DRAW");
    }
}
