package it.polimi.ingsw.am40.server.actions.active.firstRound;

import it.polimi.ingsw.am40.server.exceptions.model.TokenColorException;
import it.polimi.ingsw.am40.server.exceptions.model.TurnException;
import it.polimi.ingsw.am40.server.ActionAgent;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.actions.passive.firstRound.NegativeTokenColorAction;
import it.polimi.ingsw.am40.server.actions.passive.firstRound.PositiveTokenColorAction;
import it.polimi.ingsw.am40.server.actions.passive.flow.ChangeTurnInfoAction;
import it.polimi.ingsw.am40.server.actions.passive.flow.EndTokenPhaseAction;
import it.polimi.ingsw.am40.server.model.Color;
import it.polimi.ingsw.am40.server.model.Game;

public class TokenAction extends Action {
    //ATTRIBUTES
    /**Color of the Token Chosen*/
    Color tokenColor;

    //CONSTRUCTOR
    /**
     * Constructor for TokenAction
     */
    public TokenAction(int gameID, int playerID, Color tokenColor){
        super("", gameID, playerID);
        this.tokenColor = tokenColor;
    }

    /**
     * Override of doAction for Token Selection action
     * @param agent is the Game in which we perform the Action
     */
    @Override
    public void doAction(ActionAgent agent){
        Game gameContext = (Game) agent;

        //TokenSelection
        try{
            //Token color selection
            gameContext.chooseTokenColor(tokenColor);
            //Changes Notification
            gameContext.notifyListeners(new PositiveTokenColorAction(this.getGameID(), this.getPlayerID()),gameContext.getListeners());

            //Turn change
            if (gameContext.getPlayers().size()-1 == gameContext.getIndexOfPlayingPlayer()) {
                //Starting Card Selection Phase end
                try{
                    gameContext.changePlayersTurn(gameContext.getIndexOfPlayingPlayer());
                    //Notification
                    int nextActivePlayerIndex = gameContext.getIndexOfPlayingPlayer();
                    gameContext.notifyListeners(new EndTokenPhaseAction(this.getGameID(), this.getPlayerID(), nextActivePlayerIndex), gameContext.getListeners());
                } catch (TurnException e) {
                    //It doesn't happen
                }
            } else {
                //Normal Turn Change
                try{
                    gameContext.changePlayersTurn(gameContext.getIndexOfPlayingPlayer());
                    //Notification
                    int nextActivePlayerIndex = gameContext.getIndexOfPlayingPlayer();
                    gameContext.notifyListeners(new ChangeTurnInfoAction(this.getGameID(), this.getPlayerID(), nextActivePlayerIndex), gameContext.getListeners());
                } catch (TurnException e) {
                    //It doesn't happen
                }
            }
        } catch (TokenColorException e) {
            //Notifies that the color chosen is not available
            gameContext.notifyListeners(new NegativeTokenColorAction(this.getGameID(), this.getPlayerID()),gameContext.getListeners());
        }




    }
}
