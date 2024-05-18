package it.polimi.ingsw.am40.exceptions.server.model;


import it.polimi.ingsw.am40.exceptions.server.ModelException;

public class RepeatPlacingException extends ModelException {
    public RepeatPlacingException() {
        super("FAILED_PLACING");
    }
}
