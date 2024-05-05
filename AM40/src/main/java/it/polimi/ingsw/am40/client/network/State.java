package it.polimi.ingsw.am40.client.network;

import it.polimi.ingsw.am40.client.ConcreteContext;

import java.util.Map;

/**
 * The State interface is useful for the State Pattern
 */
public interface State {

    /**
     * Method to check the map of parameters given by the network or by the view
     * @param inputParameters a map which contains the name of the parameter and the value.
     *                        The structure of this map must be defined for each state
     * @return true if each parameter syntax is correct, false otherwise
     */
    public boolean checkInput(Map<String, String> inputParameters);

    /**
     * Method to execute a sequence of instructions written in the state.
     * The actions include view showings and network interactions
     * @param context the object on which to invoke the methods to execute the state
     */
    public void execute(ConcreteContext context);
}
