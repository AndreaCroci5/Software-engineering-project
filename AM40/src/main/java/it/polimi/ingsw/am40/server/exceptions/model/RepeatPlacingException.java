package it.polimi.ingsw.am40.server.exceptions.model;


import it.polimi.ingsw.am40.server.exceptions.ModelException;

public class RepeatPlacingException extends ModelException {
    public RepeatPlacingException() {
        super("FAILED_PLACING");
    }
}
