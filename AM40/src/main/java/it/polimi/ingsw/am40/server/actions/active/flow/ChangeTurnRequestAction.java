package it.polimi.ingsw.am40.server.actions.active.flow;


import it.polimi.ingsw.am40.server.exceptions.model.TurnException;
import it.polimi.ingsw.am40.server.ActionAgent;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.actions.passive.flow.ChangeTurnInfoAction;
import it.polimi.ingsw.am40.server.actions.passive.flow.LastRoundsInfoAction;
import it.polimi.ingsw.am40.server.actions.passive.round.EndGameAction;
import it.polimi.ingsw.am40.server.model.Game;
import it.polimi.ingsw.am40.server.model.Player;

import java.util.List;

/**
 * This class represent the Action made by the Server in response a Data sent on the network by the Client that
 * has just finished his round and confirms the fact that his round is ended to the model
 */
public class ChangeTurnRequestAction extends Action {

    //CONSTRUCTOR
    /**
     * Constructor for ChangeTurnRequestAction
     */
    public ChangeTurnRequestAction(int gameID, int playerID){
        super("CHANGE_TURN", gameID, playerID);
    }

    /**
     * Override of doAction for the Turn change in every case
     * @param agent is the Game in which we perform the Action
     */
    @Override
    public void doAction(ActionAgent agent){
        Game gameContext = (Game) agent;

        //TURN CHANGE

        //EndgameCheck / Final Rounds
        if (gameContext.checkEndGame()) {
            //Last Rounds Triggered
            gameContext.calculateRemainingRounds();
            gameContext.notifyListeners(new LastRoundsInfoAction(this.getGameID(),this.getPlayerID()), gameContext.getListeners());
            //Turn Change
            try{
                gameContext.changePlayersTurn(gameContext.getIndexOfPlayingPlayer());
                //Notification
                int nextActivePlayerIndex = gameContext.getIndexOfPlayingPlayer();
                gameContext.notifyListeners(new ChangeTurnInfoAction(this.getGameID(), this.getPlayerID(), nextActivePlayerIndex), gameContext.getListeners());
            } catch (TurnException e) {
                //Forced Endgame
                gameContext.calculateFinalScore();
                List<Player> winners;
                winners = gameContext.selectWinner();
                gameContext.notifyListeners(new EndGameAction(this.getGameID(),this.getPlayerID(), winners), gameContext.getListeners());
            }
        }
        else if (gameContext.getRemainingRounds() == 0) {
            //Natural Last Draw and then Endgame winner selection if the flow isn't compromised by disconnection
            gameContext.calculateFinalScore();
            List<Player> winners;
            winners = gameContext.selectWinner();
            gameContext.notifyListeners(new EndGameAction(this.getGameID(),this.getPlayerID(), winners), gameContext.getListeners());
        } else {
            //Normal Turn Change
            try{
                gameContext.changePlayersTurn(gameContext.getIndexOfPlayingPlayer());
                //Notification
                int nextActivePlayerIndex = gameContext.getIndexOfPlayingPlayer();
                gameContext.notifyListeners(new ChangeTurnInfoAction(this.getGameID(), this.getPlayerID(), nextActivePlayerIndex), gameContext.getListeners());
            } catch (TurnException e) {
                //Forced Endgame
                gameContext.calculateFinalScore();
                List<Player> winners;
                winners = gameContext.selectWinner();
                gameContext.notifyListeners(new EndGameAction(this.getGameID(),this.getPlayerID(), winners), gameContext.getListeners());
            }
        }
    }
}
