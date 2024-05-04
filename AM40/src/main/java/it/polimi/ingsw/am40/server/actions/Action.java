package it.polimi.ingsw.am40.server.actions;

import it.polimi.ingsw.am40.server.ActionAgent;

/**
 * Action abstract class defines messages which are used in a listeners-posters system between Model, View and Controller
 */
public abstract class Action {

    //ATTRIBUTES

    /**
     * Description of the action. It describes what an action does in order to be used in listener system (onEvent)
     */
    private final String description;

    /**
     * The Actions are strictly connected with a specific game, which has a gameID (before partyID) thanks to the
     * NetworkParty class
     */
    private final int gameID;

    /**
     * The Actions are strictly connected with a specific player, which has a playerID (before clientID) thanks to the
     * NetworkClient class
     */
    private final int playerID;



    //CONSTRUCTOR

    /**
     * Constructor for action
     * @param description the description of the action
     */
    public Action(String description, int gameID, int playerID){
        this.gameID = gameID;
        this.playerID = playerID;
        this.description = description;
    }



    //GETTER METHODS

    /**
     * Getter for message description
     * @return the description string
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter for gameID
     * @return the gameID connected with this action
     */
    public int getGameID() {
        return gameID;
    }

    /**
     * Getter for playerID
     * @return the playerID connected with this action
     */
    public int getPlayerID() {
        return playerID;
    }



    //ACTION METHODS

    /**
     * Method to run the instructions transported by the action object
     * @param agent an action must have an agent on which call the instructions
     */
    public void doAction(ActionAgent agent){

    }
}
