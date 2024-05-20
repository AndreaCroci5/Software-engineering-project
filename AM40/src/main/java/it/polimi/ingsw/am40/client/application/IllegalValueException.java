package it.polimi.ingsw.am40.client.application;


/**
 * Exception that occurs when network choice isn't valid.
 */
public class IllegalValueException extends Exception {
    public IllegalValueException(){
        super("Invalid parameter");
    }
}
