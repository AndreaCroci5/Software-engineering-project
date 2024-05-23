package it.polimi.ingsw.am40.server.actions.passive.firstRound;

import it.polimi.ingsw.am40.server.ActionAgent;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.model.ResourceCard;

import java.util.ArrayList;

/**
 * This class serves as a mean of the notification to the VirtualView which then will notify the client by using the Network interface
 * after cards dealing during the first Round and this class carries the information of the Cards Drawn and the new Cards on the
 * top of the ResourceCard and GoldenResourceCard Decks
 */
public class DealCardsResultAction extends Action {
    //ATTRIBUTES
    /** Reference to the Array of Integers that represents the cardIDs of the Cards held in a Player's PrivateBoard's handDeck*/
    ArrayList<Integer> handDeckIDs;
    /** Value of the ID of the Card on the top of the ResourceCard Deck*/
    int deckResourceCardID;
    /** Value of the ID of the Card on the top of the GoldenResourceCard Deck*/
    int deckGoldenCardID;
    //CONSTRUCTOR
    /**
     * Constructor for DealCardsResult Action
     */
    public DealCardsResultAction(int gameID, int playerID, ArrayList<Integer> handDeckIDs, int deckResourceCardID, int deckGoldenCardID){
        super("POSITIVE_TOKEN_COLOR", gameID, playerID);
        this.handDeckIDs = handDeckIDs;
        this.deckResourceCardID = deckResourceCardID;
        this.deckGoldenCardID = deckGoldenCardID;
    }

    /**
     * Override of doAction for the DeaCardsResultAction
     * @param agent is the Game in which we perform the Action
     */
    @Override
    public void doAction(ActionAgent agent){

    }
}
