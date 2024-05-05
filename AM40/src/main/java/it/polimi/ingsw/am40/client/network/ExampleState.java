package it.polimi.ingsw.am40.client.network;

import it.polimi.ingsw.am40.client.ConcreteContext;

import java.util.Map;
import java.util.Objects;


/**
 * Example class to show how to implement State pattern
 */
public class ExampleState implements State{

    /**
     * Method to check the map of parameters given by the network or by the view
     *
     * @param inputParameters a map which contains the name of the parameter and the value.
     *                        The structure of this map must be defined for each state
     * @return true if each parameter syntax is correct, false otherwise
     */
    @Override
    public boolean checkInput(Map<String, String> inputParameters) {
        //Check (if present) if the key "Example" has the value "hello_world"
        return Objects.equals(inputParameters.getOrDefault("Example", (String) ""), "hello_World");
    }

    /**
     * Method to execute a sequence of instructions written in the state.
     * The actions include view showings and network interactions
     *
     * @param context the object on which to invoke the methods to execute the state
     */
    @Override
    public void execute(ConcreteContext context) {
        //After a check on the parameters you can execute the state. In this case it prints the context as a String
        System.out.println(context.toString());
    }
}
