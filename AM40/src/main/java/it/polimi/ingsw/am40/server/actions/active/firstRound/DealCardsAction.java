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
public class DealCardsAction extends Action {
    //ATTRIBUTES

    //CONSTRUCTOR
    /**
     * Constructor for DealCardsAction
     */
    public DealCardsAction(int gameID, int playerID){
        super("CARDS_DEAL_MESSAGE", gameID, playerID);
    }

    /**
     * Override of doAction for the Initialization of the Game Phase
     * @param agent is the Game in which we perform the Action
     */
    @Override
    public void doAction(ActionAgent agent){
        Game gameContext = (Game) agent;

        //Card Deal
      //fixme  gameContext.dealCards();
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
        gameContext.notifyListeners(new DealCardsResultAction(this.getGameID(), this.getPlayerID(), handDeckIDs, deckResourceCardID, deckGoldenCardID), gameContext.getListeners());

    }

}
