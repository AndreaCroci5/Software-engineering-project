package it.polimi.ingsw.am40.client.network;

/**
 * Client class is the main class of the client. It manages both the view and the network.
 * It is like a mediator which decide, basing on the current state, what to do.
 */
public class Client implements AbstractContext{

    //ATTRIBUTES

    /**
     * Current state of the FSM on the client
     */
    public State currentState;




    //ABSTRACT CONTEXT METHODS

    /**
     * Method to set the current state of the FSM
     *
     * @param newState the new state to set
     */
    @Override
    public void setState(State newState) {

    }
}
