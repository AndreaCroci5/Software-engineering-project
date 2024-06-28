package it.polimi.ingsw.am40.server.application;

/**
 * Exception that occurs when JSON prefs aren't valid.
 */
public class InvalidJSONPrefsException extends Exception {

    /**
     * Constructor for the InvalidJsonPrefsException
     */
    public InvalidJSONPrefsException(){
        super("Impossible to parse prefs from JSON!");
    }
}
