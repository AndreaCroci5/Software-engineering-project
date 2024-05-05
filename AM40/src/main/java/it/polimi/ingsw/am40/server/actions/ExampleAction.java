package it.polimi.ingsw.am40.server.actions;

import it.polimi.ingsw.am40.server.ActionAgent;

/**
 * Template for "? extends Action"
 */
public class ExampleAction extends Action {

    /**
     * Constructor for example action
     */
    public ExampleAction(int gameID, int playerID){
        super("Example Action", gameID, playerID);
    }

    /**
     * Override of doAction like an example
     * @param agent an action must have an agent on which call the instructions
     */
    @Override
    public void doAction(ActionAgent agent){
        System.out.println(agent.toString());
    }
}
