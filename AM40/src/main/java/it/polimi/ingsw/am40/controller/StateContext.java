package it.polimi.ingsw.am40.controller;

import it.polimi.ingsw.am40.controller.states.State;

/**
 * Interface for state pattern.
 * It requests to implement a method which change the actual state of a FSM (context)
 */
public interface StateContext {
    /**
     * Interface method to set the context to another state
     * @param stateToSet New state to set
     * @throws InexistentStateException The method must throw an exception in case the called state doesn't exist
     */
    void setState(State stateToSet) throws InexistentStateException;
}
