package it.polimi.ingsw.am40.server.actions.active.firstRound;

import it.polimi.ingsw.am40.server.ActionAgent;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.actions.passive.firstRound.DealCardsResultAction;
import it.polimi.ingsw.am40.server.model.Game;
import it.polimi.ingsw.am40.server.model.ResourceCard;

import java.util.ArrayList;

/**
 * This class represent the Action made by the Server after the Token selection phase that gives to a Player his 2
 * ResourceCards and 1 GoldenResourceCard as the rules say
 */
public class DealCardsRequestAction extends Action {
    //ATTRIBUTES

    //CONSTRUCTOR
    /**
     * Constructor for DealCardsRequestAction
     */
    public DealCardsRequestAction(String nickname,int gameID, int playerID){
        super("CARDS_DEAL", nickname, gameID, playerID);
    }

    /**
     * Override of doAction for the Cards dealing phase during the first Round
     * @param agent is the Game in which we perform the Action
     */
    @Override
    public void doAction(ActionAgent agent){
        Game gameContext = (Game) agent;

        //Card Deal
        gameContext.dealCards();
        //Data fetch
        //HandDeck
        ArrayList<Integer> handDeckIDs = new ArrayList<>();
        int i = gameContext.getIndexOfPlayingPlayer();
        ArrayList<ResourceCard> handDeck = gameContext.getPlayers().get(i).getPrivateBoard().getHandDeck();
        for (ResourceCard c : handDeck) {
            handDeckIDs.add(c.getCardID());
        }
        //Deck Card
        int deckResourceCardID = gameContext.getCommonBoard().getResourceDeck().peekFirstCard();
        int deckGoldenCardID = gameContext.getCommonBoard().getGoldenResourceDeck().peekFirstCard();

        //Notify listeners
        gameContext.notifyListeners(new DealCardsResultAction(this.getNickname(), this.getGameID(), this.getPlayerID(), handDeckIDs, deckResourceCardID, deckGoldenCardID), gameContext.getListeners());

    }

}
