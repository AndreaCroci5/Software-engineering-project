package it.polimi.ingsw.am40.server.actions.active.round;

import it.polimi.ingsw.am40.server.exceptions.model.RepeatDrawException;
import it.polimi.ingsw.am40.server.ActionAgent;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.actions.passive.round.PositiveDrawAction;
import it.polimi.ingsw.am40.server.actions.passive.round.RepeatDrawAction;
import it.polimi.ingsw.am40.server.model.Game;

/**
 * This class represent the action performed by the Server after receiving via network a DRAW command from the Client.
 * It works as executor for the controller on the model, following the MVC pattern
 */
public class DrawAction extends Action {
    //ATTRIBUTES
    /** Choice of which type of Card to draw from the CommonBoard: resource(0) or golden(1)*/
    private final int choice;
    /** Possible selection on the CommonBoard: the two cards on plate(0), plate(1); or deck(2)*/
    private final int selection;


    //CONSTRUCTOR
    public DrawAction(String nickname, int gameID, int playerID, int choice, int selection ){
        super("DRAW", nickname, gameID, playerID);
        this.choice = choice;
        this.selection = selection;
    }

    /**
     * Override of doAction for the Draw Phase
     * @param agent is the Game in which we perform the Action
     */
    @Override
    public void doAction(ActionAgent agent){
        Game gameContext = (Game) agent;

        try {

            //Draw
            gameContext.draw(this.choice, this.selection);

            //Data research for the notification
            int dataPlayerIndex = gameContext.getIndexOfPlayingPlayer();
            int cardDrawnID = gameContext.getPlayers().get(dataPlayerIndex).getPrivateBoard().getHandDeck().getLast().getCardID();
            int cardReplacedID = gameContext.getCommonBoard().boardCardDifference(this.choice, this.selection);
            int deckCardReplacedID = gameContext.getCommonBoard().deckDifference(this.choice);
            //Positive Draw Notify
            gameContext.notifyListeners(new PositiveDrawAction(this.getNickname(), this.getGameID(),this.getPlayerID(), cardDrawnID, cardReplacedID, this.selection, deckCardReplacedID), gameContext.getListeners());
            
        } catch (RepeatDrawException e) {
            gameContext.notifyListeners(new RepeatDrawAction(this.getNickname(), this.getGameID(), this.getPlayerID()), gameContext.getListeners());
        }
    }
}
