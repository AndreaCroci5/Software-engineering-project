package it.polimi.ingsw.am40.client.network;

/**
 * AbstractContext interface ensures the implementation of a method to set the state of an FSM of a State Pattern
 */
public interface AbstractContext {

    /**
     * Method to set the current state of the FSM
     * @param newState the new state to set
     */
    public void setState(State newState);
}
