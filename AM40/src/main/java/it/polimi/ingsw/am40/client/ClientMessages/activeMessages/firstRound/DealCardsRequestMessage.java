package it.polimi.ingsw.am40.client.ClientMessages.activeMessages.firstRound;

import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.data.active.firstRound.DealCardsData;

public class DealCardsRequestMessage extends Message {

    /**
     * This message is used to ask the server the 3 cards of the player hand
     */
    public DealCardsRequestMessage(String nickname) {
        super("CARDS_DEAL",nickname);
    }

    /**
     * This method is used to convert the internal message of the client in
     * a data that is the object that is going through the network
     * @return the data that is going to the network
     */
    public Data messageToData() {
        return new DealCardsData();
    }
}
