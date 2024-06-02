package it.polimi.ingsw.am40.server.actions.active.firstRound;

import it.polimi.ingsw.am40.server.ActionAgent;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.actions.passive.firstRound.TokenInfoAction;
import it.polimi.ingsw.am40.server.model.Game;

import java.util.List;

public class TokenRequestAction extends Action {
    //ATTRIBUTES

    //CONSTRUCTOR
    /**
     * Constructor for TokenRequestAction
     */
    public TokenRequestAction(int gameID, int playerID){
        super("TOKEN_REQUEST", gameID, playerID);
    }

    /**
     * Override of doAction for the Token colors request
     * @param agent is the Game in which we perform the Action
     */
    @Override
    public void doAction(ActionAgent agent){
        Game gameContext = (Game) agent;
        List<String> colors;

        //Remaining colors check
        colors = gameContext.remainingTokenColors();
        //Changes Notification
        gameContext.notifyListeners(new TokenInfoAction(this.getGameID(), this.getPlayerID(), colors), gameContext.getListeners());
    }
}
