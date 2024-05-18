package it.polimi.ingsw.am40.exceptions.server.model;

import it.polimi.ingsw.am40.exceptions.server.ModelException;

public class TurnException extends ModelException {
    public TurnException() {
        super("FAILED_TURN_CHANGE");
    }
}
