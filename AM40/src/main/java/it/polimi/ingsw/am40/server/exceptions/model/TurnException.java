package it.polimi.ingsw.am40.server.exceptions.model;

import it.polimi.ingsw.am40.server.exceptions.ModelException;

public class TurnException extends ModelException {
    public TurnException() {
        super("FAILED_TURN_CHANGE");
    }
}
