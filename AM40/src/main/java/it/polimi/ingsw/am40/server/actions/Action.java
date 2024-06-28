package it.polimi.ingsw.am40.server.actions;

import it.polimi.ingsw.am40.data.Data;
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
     * Nickname of the Player that made the Action
     */
    private final String nickname;

    /**
     * The Actions are strictly connected with a specific game, which has a gameID (before partyID) thanks to the
     * NetworkParty class
     */
    private  int gameID;

    /**
     * The Actions are strictly connected with a specific player, which has a playerID (before clientID) thanks to the
     * NetworkClient class
     */
    private  int playerID;



    //CONSTRUCTOR

    /**
     * Constructor for Action
     * @param description the description of the action
     * @param nickname is the name of the Client that triggered this Action
     * @param gameID is the ID of the party from where the Client that triggered this Action belongs
     * @param playerID is the ID the Client that triggered this Action
     */
    public Action(String description, String nickname, int gameID, int playerID){
        this.gameID = gameID;
        this.nickname = nickname;
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

    public String getNickname() {
        return nickname;
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

    /**
     * Getter for gameID
     * @param gameID is the ID of the party/game that the Player belongs to
     */
    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    /**
     * Setter for playerID
     * @param playerID is the ID of the player as int
     */
    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

//ACTION METHODS

    /**
     * Method to run the instructions transported by the action object
     * @param agent an action must have an agent on which call the instructions
     */
    public void doAction(ActionAgent agent){

    }

    /**
     * Method that creates the respective Data, here is displayed just as an interface method
     * @return the Data related to the Action
     */
    public Data dataCreator() {
        return null;
    }
}
