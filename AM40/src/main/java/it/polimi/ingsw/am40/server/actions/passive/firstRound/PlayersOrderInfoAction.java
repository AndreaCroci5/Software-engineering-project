package it.polimi.ingsw.am40.server.actions.passive.firstRound;

import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.data.passive.firstRound.PlayersOrderInfoData;
import it.polimi.ingsw.am40.server.ActionAgent;
import it.polimi.ingsw.am40.server.actions.Action;

import java.util.ArrayList;

public class PlayersOrderInfoAction extends Action {
    //ATTRIBUTES
    /**
     * This ArrayList contains the nicknames of the Players in a certain Game ordered by their respective Game order,
     * which establishes when each Player has the right to play his turn
     */
    private ArrayList<String> nicknames;

    //CONSTRUCTOR
    /**
     * Constructor for AimCard choice selection response
     */
    public PlayersOrderInfoAction(String nickname, int gameID, int playerID, ArrayList<String> nicknames){
        super("PLAYERS_ORDER_INFO", nickname, gameID, playerID);
        this.nicknames = nicknames;
    }

    //PUBLIC METHOD

    /**
     * Override of doAction for Player nicknames order info transport
     * @param agent is the Game in which we perform the Action
     */
    @Override
    public void doAction(ActionAgent agent){

    }

    public Data dataCreator() {
        return new PlayersOrderInfoData(this.getNickname(), this.nicknames);
    }

}
