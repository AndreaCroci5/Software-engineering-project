package it.polimi.ingsw.am40.client.network;

import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.view.ViewManager;

/**
 * The State interface is useful for the State Pattern
 */
public interface State {

    /**
     * Method to execute a sequence of instructions written in the state.
     * The actions include view showings and network interactions
     * @param view the object on which to invoke the methods to execute the state
     */
    public Message execute(ViewManager view);

    /**
     * Method to send a message through the network
     * @param message the message to send
     * @return a message //fixme
     */
    public Message sendMessage(Message message);
}
