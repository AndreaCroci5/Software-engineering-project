package it.polimi.ingsw.am40.server.exceptions.model;

import it.polimi.ingsw.am40.server.exceptions.ModelException;

public class TokenColorException extends ModelException {
    public TokenColorException() {
        super("FAILED_TOKEN_SELECTION");
    }
}
