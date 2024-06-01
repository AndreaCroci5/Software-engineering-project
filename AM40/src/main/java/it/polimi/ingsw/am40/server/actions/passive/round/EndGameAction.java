package it.polimi.ingsw.am40.server.actions.passive.round;

import it.polimi.ingsw.am40.server.ActionAgent;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.model.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * This class serves as a mean to notify to the VirtualView which then will notify the client by using the Network interface
 * and carries the information about the winner of the Game. If the Game ends in a tie, there will be more than one winner
 */
public class EndGameAction extends Action {
    //ATTRIBUTES
    /** Reference to the Players that won the Game in case of a tie. If the winner is only one, the List contains only one Player*/
    private final List<Player> winners;

    //CONSTRUCTOR
    /**
     * Constructor for EndGameAction
     */
    public EndGameAction(int gameID, int playerID, List<Player> winners){
        super("ENDGAME", gameID, playerID);
        this.winners = winners;
    }

    /**
     * Override of doAction for EndGame phase
     * @param agent
     */
    @Override
    public void doAction(ActionAgent agent){

    }
}
