package it.polimi.ingsw.am40.server.application;

/**
 * Exception that occurs when args prefs aren't valid.
 */
public class InvalidArgsPrefsException extends Exception {
    public InvalidArgsPrefsException(){
        super("Impossible to parse prefs from args!");
    }
}
