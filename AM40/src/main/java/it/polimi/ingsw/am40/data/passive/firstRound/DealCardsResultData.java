package it.polimi.ingsw.am40.data.passive.firstRound;

import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.Action;

import java.util.ArrayList;

@JsonTypeName("CARDS_DEAL_RESULT")
public class DealCardsResultData extends Data {
    //ATTRIBUTES
    /** Reference to the Array of Integers that represents the cardIDs of the Cards held in a Player's PrivateBoard's handDeck*/
    private ArrayList<Integer> handDeckIDs;

    /** Value of the ID of the Card on the top of the ResourceCard Deck*/
    private int deckResourceCardID;

    /** Value of the ID of the Card on the top of the GoldenResourceCard Deck*/
    private int deckGoldenCardID;

    //CONSTRUCTOR

    //Logic constructor for subclasses
    public DealCardsResultData(String nickname, ArrayList<Integer> handDeckIDs, int deckResourceCardID, int deckGoldenCardID){
        super("CARDS_DEAL_RESULT", nickname);
        this.handDeckIDs = handDeckIDs;
        this.deckResourceCardID = deckResourceCardID;
        this.deckGoldenCardID = deckGoldenCardID;
    }
    //Json constructor
    public DealCardsResultData(){

    }


    //PUBLIC METHODS

    public Action onServer(){
        return null;
    }

    public Message onClient() {
        return null;
    }
}
