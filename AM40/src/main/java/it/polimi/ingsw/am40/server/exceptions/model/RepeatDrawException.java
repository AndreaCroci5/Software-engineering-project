package it.polimi.ingsw.am40.server.exceptions.model;

import it.polimi.ingsw.am40.server.exceptions.ModelException;

public class RepeatDrawException extends ModelException {
    public RepeatDrawException() {
        super("FAILED_DRAW");
    }
}
