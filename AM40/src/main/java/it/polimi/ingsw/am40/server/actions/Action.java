package it.polimi.ingsw.am40.server.actions;

import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.ActionAgent;
//TODO choose where to put the nickname, constructor if every Action has it, set method if i make a lookup on the Server
// UPDATE wait the next commits because probably the nickname will be given directly by the Data so i have to put
// a nickname declaration here and put in the class constructor
// FIXME the dataCreator in every subclass and add the VVServer part and add the Nickname part
/**
 * Action abstract class defines messages which are used in a listeners-posters system between Model, View and Controller
 */
public abstract class Action {

    //ATTRIBUTES

    /**
     * Description of the action. It describes what an action does in order to be used in listener system (onEvent)
     */
    private final String description;

    //TODO javadoc
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
     * Constructor for action
     * @param description the description of the action
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

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

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

    public Data dataCreator() {
        return null;
    }
}
