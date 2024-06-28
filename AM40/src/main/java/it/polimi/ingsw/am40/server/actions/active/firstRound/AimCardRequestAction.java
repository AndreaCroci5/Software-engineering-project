package it.polimi.ingsw.am40.server.actions.active.firstRound;

import it.polimi.ingsw.am40.server.ActionAgent;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.actions.passive.firstRound.AimCardInfoAction;
import it.polimi.ingsw.am40.server.actions.passive.firstRound.AimCardResultAction;
import it.polimi.ingsw.am40.server.model.AimCard;
import it.polimi.ingsw.am40.server.model.Game;

/**
 * This class serves as a mean to notify the Server that the Active Client needs information about the two AimCards from
 * which he has to choose
 */
public class AimCardRequestAction extends Action {

    //CONSTRUCTOR
    public AimCardRequestAction(String nickname, int gameID, int playerID){
        super("AIM_CARD_REQUEST", nickname, gameID, playerID);
    }

    /**
     * Override of doAction for AimCard request
     * @param agent is the Game in which we perform the Action
     */
    @Override
    public void doAction(ActionAgent agent){
        Game gameContext = (Game) agent;

        //AimCard peeking DataSource
        int aimID1 = gameContext.getCommonBoard().getAimDeck().peekFirstCard();
        AimCard tmp = gameContext.getCommonBoard().getAimDeck().pickFromTop();
        int aimID2 = gameContext.getCommonBoard().getAimDeck().peekFirstCard();
        gameContext.getCommonBoard().getAimDeck().addToTop(tmp);

        //Notification
        gameContext.notifyListeners(new AimCardInfoAction(this.getNickname(), this.getGameID(), this.getPlayerID(), aimID1, aimID2), gameContext.getListeners());
    }

}
