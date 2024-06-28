package it.polimi.ingsw.am40.client.view;

/**
 * Exception that occurs when it isn't possible to create the concrete view manager object.
 */
public class ViewFactoryException extends Exception {

    /**
     * Constructor for the ViewFactoryException
     */
    public ViewFactoryException(){
        super("Can't create the view manager!");
    }
}
