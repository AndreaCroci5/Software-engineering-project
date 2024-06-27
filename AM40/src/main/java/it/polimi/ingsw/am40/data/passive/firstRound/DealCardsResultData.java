package it.polimi.ingsw.am40.data.passive.firstRound;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.firstRound.DealCardsResponseMessage;
import it.polimi.ingsw.am40.client.network.RMI.RemoteInterfaceClient;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.network.RMI.RemoteInterfaceServer;

import java.rmi.RemoteException;
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


    @JsonCreator
    public DealCardsResultData(@JsonProperty("nickname") String nickname,
                               @JsonProperty("handDeckIDs") ArrayList<Integer> handDeckIDs,
                               @JsonProperty("deckResourceCardID") int deckResourceCardID,
                               @JsonProperty("deckGoldenCardID") int deckGoldenCardID){
        super("CARD_DEAL_RESULT", nickname);
        this.handDeckIDs = handDeckIDs;
        this.deckResourceCardID = deckResourceCardID;
        this.deckGoldenCardID = deckGoldenCardID;
    }

    public ArrayList<Integer> getHandDeckIDs() {
        return handDeckIDs;
    }

    public void setHandDeckIDs(ArrayList<Integer> handDeckIDs) {
        this.handDeckIDs = handDeckIDs;
    }

    public int getDeckResourceCardID() {
        return deckResourceCardID;
    }

    public void setDeckResourceCardID(int deckResourceCardID) {
        this.deckResourceCardID = deckResourceCardID;
    }

    public int getDeckGoldenCardID() {
        return deckGoldenCardID;
    }

    public void setDeckGoldenCardID(int deckGoldenCardID) {
        this.deckGoldenCardID = deckGoldenCardID;
    }

    //PUBLIC METHODS

    public Action onServer(){
        return null;
    }

    public Message onClient() {
        return new DealCardsResponseMessage(this.getNickname(),this.handDeckIDs,this.deckResourceCardID,this.deckGoldenCardID);
    }

    /**
     * Method which calls the right RMI interface method for each data (with override)
     * @param skeleton the client remote interface. Null if data active
     * @param stub the server remote interface. Null if data passive
     */
    @Override
    public void doRMI(RemoteInterfaceClient skeleton, RemoteInterfaceServer stub){
        try {
            skeleton.dealCardResultPassiveFirstRound(this);
        } catch (RemoteException e) {
            System.out.println("RMI call went wrong! message: " + e);
        }
    }
}
