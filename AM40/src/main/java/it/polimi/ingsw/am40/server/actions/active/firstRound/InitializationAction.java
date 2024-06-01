package it.polimi.ingsw.am40.server.actions.active.firstRound;

import it.polimi.ingsw.am40.server.ActionAgent;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.model.*;

//TODO Implementation, it has to be sequentially synchronized with the Game creation
public class InitializationAction extends Action {
    //ATTRIBUTES

    //CONSTRUCTOR
    /**
     * Constructor for InitializationAction
     */
    public InitializationAction(int gameID, int playerID){
        super("", gameID, playerID);
    }

    /**
     * Override of doAction for the Initialization of the Game Phase
     * @param agent is the Game in which we perform the Action
     */
    @Override
    public void doAction(ActionAgent agent){
        Game gameContext = (Game) agent;
        //Boards Setup
    }
}
