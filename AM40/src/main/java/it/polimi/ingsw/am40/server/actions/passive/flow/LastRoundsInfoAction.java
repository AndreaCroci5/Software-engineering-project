package it.polimi.ingsw.am40.server.actions.passive.flow;

import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.data.passive.flow.LastRoundsInfoData;
import it.polimi.ingsw.am40.server.actions.Action;

/**
 * This class serves as a mean to notify to the VirtualView which then will notify the client by using the Network interface.
 * It's triggered when one of the Players has reached one of the condition of the EndGame phase.
 * After this event the Players will play their last rounds and, then, the model will notify the winners of the Game
 */
public class LastRoundsInfoAction extends Action {
    //CONSTRUCTOR
    /**
     * Constructor for Last Rounds Information response when Endgame phase is triggered
     */
    public LastRoundsInfoAction(String nickname, int gameID, int playerID){
        super("LAST_ROUNDS", nickname, gameID, playerID);
    }


    public Data dataCreator() {
        return  new LastRoundsInfoData(this.getNickname());
    }
}
