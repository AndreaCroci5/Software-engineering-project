package it.polimi.ingsw.am40.server.actions.active.firstRound;

import it.polimi.ingsw.am40.server.ActionAgent;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.actions.passive.firstRound.StartingCardInfoAction;
import it.polimi.ingsw.am40.server.model.Game;
import it.polimi.ingsw.am40.server.model.PrivateBoard;

//Fixme decide where to place the Card better in hand and then place
public class StartingCardRequestAction extends Action {
    //ATTRIBUTES

    //CONSTRUCTOR
    /**
     * Constructor for StartingCardRequestAction
     */
    public StartingCardRequestAction(int gameID, int playerID){
        super("STARTING_CARD_REQUEST", gameID, playerID);
    }

    /**
     * Override of doAction for the StartingCard assignment
     * @param agent is the Game in which we perform the Action
     */
    @Override
    public void doAction(ActionAgent agent){
        Game gameContext = (Game) agent;
        PrivateBoard activePP = gameContext.getPlayers().get(gameContext.getIndexOfPlayingPlayer()).getPrivateBoard();
        //FIXME add method for StartingCard deck in CommonBoard
        //StartingCard add to hand
        activePP.addCardToHand(gameContext.getCommonBoard().getStartingDeck().pickFromTop());
        int startingCardID = activePP.getHandDeck().getFirst().getCardID();
        //Changes Notification
        gameContext.notifyListeners(new StartingCardInfoAction(this.getGameID(), this.getPlayerID(), startingCardID), gameContext.getListeners());
    }
}
