package it.polimi.ingsw.am40.exceptions.server.model;

import it.polimi.ingsw.am40.exceptions.server.ModelException;

public class TokenColorException extends ModelException {
    public TokenColorException() {
        super("FAILED_TOKEN_SELECTION");
    }
}
