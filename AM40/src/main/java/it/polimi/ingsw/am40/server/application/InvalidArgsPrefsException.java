package it.polimi.ingsw.am40.server.application;

/**
 * Exception that occurs when args prefs aren't valid.
 */
public class InvalidArgsPrefsException extends Exception {

    /**
     * Constructor for the InvalidArgsPrefsException
     */
    public InvalidArgsPrefsException(){
        super("Impossible to parse prefs from args!");
    }
}
