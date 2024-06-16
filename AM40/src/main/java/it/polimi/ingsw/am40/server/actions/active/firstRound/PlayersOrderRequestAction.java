package it.polimi.ingsw.am40.server.actions.active.firstRound;

import it.polimi.ingsw.am40.server.ActionAgent;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.actions.passive.firstRound.PlayersOrderInfoAction;
import it.polimi.ingsw.am40.server.model.Game;

import java.util.ArrayList;

public class PlayersOrderRequestAction extends Action {

    //CONSTRUCTOR
    /**
     * Constructor for PlayersOrderRequestAction
     */
    public PlayersOrderRequestAction(int gameID, int playerID){
        super("PLAYERS_ORDER_REQUEST", gameID, playerID);
    }

    /**
     * Override of doAction for Players' Order request
     * @param agent is the Game in which we perform the Action
     */
    @Override
    public void doAction(ActionAgent agent){
        Game gameContext = (Game) agent;
        //Players' Nicknames game order
        ArrayList<String> nicknames = new ArrayList<>();
        //Decide Player Order
        gameContext.decidePlayerOrder();
        //Ordered nicknames getter
        for (int i = 0; i<gameContext.getPlayers().size(); i++) {
            nicknames.add(gameContext.getPlayers().get(i).getNickname());
        }

        //Notification
        gameContext.notifyListeners(new PlayersOrderInfoAction(this.getGameID(), this.getPlayerID(), nicknames), gameContext.getListeners());
    }

}
