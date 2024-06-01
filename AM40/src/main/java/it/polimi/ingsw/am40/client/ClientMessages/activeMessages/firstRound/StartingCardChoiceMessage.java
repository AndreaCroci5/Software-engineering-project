package it.polimi.ingsw.am40.client.ClientMessages.activeMessages.firstRound;

import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.data.active.firstRound.StartingCardChoiceData;

public class StartingCardChoiceMessage extends Message {

    /**
     * It's the face that the client choose in order to place his starting card
     */
    private final String face;

    /**
     * This message contains the face that the client choose in order to place his starting card
     * @param face is the face that the client choose in order to play his starting card
     */
    public StartingCardChoiceMessage(String face) {
        super("STARTING_CARD_CHOICE");
        this.face = face;
    }

    /**
     * This method is used to convert the internal message of the client in
     * a data that is the object that is going through the network
     * @return the data that is going to the network
     */
    public Data messageToData() {
        return new StartingCardChoiceData(this.face);
    }
}
