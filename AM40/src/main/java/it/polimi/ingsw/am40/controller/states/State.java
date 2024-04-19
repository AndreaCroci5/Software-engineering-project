package it.polimi.ingsw.am40.controller.states;

import it.polimi.ingsw.am40.controller.StateContext;

/**
 * Interface for state pattern.
 * It requests to implement a method which defines how to manage a state
 * in an FSM paradigm.
 */
public interface State {
    /**
     * Interface method for state execution
     * @param context The context which uses the states
     */
    public void execute(StateContext context);
}

