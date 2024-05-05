package it.polimi.ingsw.am40.server;

import it.polimi.ingsw.am40.server.actions.Action;

import java.util.Collection;
import java.util.List;

/**
 * Interface that ensures the implementation of methods to handle a collection of listeners
 * in a "listeners" architecture
 */
public interface ActionPoster extends ActionAgent{

    /**
     * It adds a new listener to the container of listeners in a "poster" class
     * @param listener Reference to the new object which implements "action listener"
     * @param listenersContainer Collection of the listeners
     */
    public void addListener(ActionListener listener, Collection<ActionListener> listenersContainer);

    /**
     * It removes a new listener to the container of listeners in a "poster" class
     * @param listener Reference to the object you want to remove
     * @param listenersContainer Collection of the listeners
     */
    public void removeListener(ActionListener listener,  Collection<ActionListener> listenersContainer);

    /**
     * It notifies all the listeners of an event
     * It will probably call the onEvent method of ActionListener interface
     * @param event Message to post
     * @param listenersContainer Collection of the listeners
     */
    public void notifyListeners(Action event, Collection<ActionListener> listenersContainer);

    /**
     * Getter for the list of listeners
     * @return the list of references of the listeners
     */
    public List<ActionListener> getListeners();
}
