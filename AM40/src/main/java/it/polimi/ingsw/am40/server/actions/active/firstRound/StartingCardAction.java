package it.polimi.ingsw.am40.server.actions.active.firstRound;

import it.polimi.ingsw.am40.server.exceptions.model.TurnException;
import it.polimi.ingsw.am40.server.ActionAgent;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.actions.passive.firstRound.StartingCardResultAction;
import it.polimi.ingsw.am40.server.actions.passive.flow.ChangeTurnInfoAction;
import it.polimi.ingsw.am40.server.actions.passive.flow.EndStartingCardPhaseAction;
import it.polimi.ingsw.am40.server.model.CardFace;
import it.polimi.ingsw.am40.server.model.Game;

public class StartingCardAction extends Action{
    //ATTRIBUTES
    /** CardFace chosen by the Client for the Starting Card*/
    CardFace cardFace;

    //CONSTRUCTOR
    /**
     * Constructor for StartingCardAction
     */
    public StartingCardAction(int gameID, int playerID, CardFace cardFace){
        super("", gameID, playerID);
        this.cardFace = cardFace;
    }

    /**
     * Override of doAction for the StartingCard face selection
     * @param agent is the Game in which we perform the Action
     */
    @Override
    public void doAction(ActionAgent agent){
        Game gameContext = (Game) agent;
        //Face Change
        gameContext.chooseStartingCardFace(this.cardFace);
        //Changes Notification
        gameContext.notifyListeners(new StartingCardResultAction(this.getGameID(), this.getPlayerID()), gameContext.getListeners());

        //Turn change
        if (gameContext.getPlayers().size()-1 == gameContext.getIndexOfPlayingPlayer()) {
            //Starting Card Selection Phase end
            try{
                gameContext.changePlayersTurn(gameContext.getIndexOfPlayingPlayer());
            } catch (TurnException e) {
                //It doesn't happen
            }
            //Notification
            int nextActivePlayerIndex = gameContext.getIndexOfPlayingPlayer();
            gameContext.notifyListeners(new EndStartingCardPhaseAction(this.getGameID(), this.getPlayerID(), nextActivePlayerIndex), gameContext.getListeners());
        } else {
            //Normal Turn Change
            try{
                gameContext.changePlayersTurn(gameContext.getIndexOfPlayingPlayer());
            } catch (TurnException e) {
                //It doesn't happen
            }
            //Notification
            int nextActivePlayerIndex = gameContext.getIndexOfPlayingPlayer();
            gameContext.notifyListeners(new ChangeTurnInfoAction(this.getGameID(), this.getPlayerID(), nextActivePlayerIndex), gameContext.getListeners());
        }
    }
}
