package it.polimi.ingsw.am40.server.actions.passive.firstRound;

import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.data.passive.firstRound.AimCardResultData;
import it.polimi.ingsw.am40.data.passive.firstRound.DealCardsResultData;
import it.polimi.ingsw.am40.server.ActionAgent;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.model.ResourceCard;
import it.polimi.ingsw.am40.server.network.virtual_view.VVServer;

import java.util.ArrayList;

/**
 * This class serves as a mean of the notification to the VirtualView which then will notify the client by using the Network interface
 * after cards dealing during the first Round and this class carries the information of the Cards Drawn and the new Cards on the
 * top of the ResourceCard and GoldenResourceCard Decks
 */
public class DealCardsResultAction extends Action {
    //ATTRIBUTES
    /** Reference to the Array of Integers that represents the cardIDs of the Cards held in a Player's PrivateBoard's handDeck*/
    private final ArrayList<Integer> handDeckIDs;
    /** Value of the ID of the Card on the top of the ResourceCard Deck*/
    private final int deckResourceCardID;
    /** Value of the ID of the Card on the top of the GoldenResourceCard Deck*/
    private final int deckGoldenCardID;


    //CONSTRUCTOR
    public DealCardsResultAction(String nickname, int gameID, int playerID, ArrayList<Integer> handDeckIDs, int deckResourceCardID, int deckGoldenCardID){
        super("CARDS_DEAL_RESULT", nickname, gameID, playerID);
        this.handDeckIDs = handDeckIDs;
        this.deckResourceCardID = deckResourceCardID;
        this.deckGoldenCardID = deckGoldenCardID;
    }

    /**
     * Override of doAction for the DeaCardsResultAction
     * @param agent is the VVServer from which the Action is transformed into a Data and sent
     */
    @Override
    public void doAction(ActionAgent agent){
        VVServer v = (VVServer) agent;
        v.sendOnNetworkBroadcastInAParty(this.getGameID(), dataCreator());
    }

    /**
     * Override of dataCreator for the creation of the respective result Data
     * @return a DealCardsResultData
     */
    public Data dataCreator() {
        return new DealCardsResultData(this.getNickname(), this.handDeckIDs, this.deckResourceCardID, this.deckGoldenCardID);
    }
}
