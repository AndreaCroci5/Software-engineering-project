package it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.firstRound;

import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.ClientMessages.activeMessages.flow.ChangeTurnRequestMessage;
import it.polimi.ingsw.am40.client.network.ClientContext;
import it.polimi.ingsw.am40.client.network.States.passiveStates.PassiveTokenChoiceState;

public class StartingCardResultMessage extends Message {

    /**
     * It's the nickname of the active client
     */
    private final String clientNickname;

    /**
     * It's the ID of the starting card given to the client
     */
    private final int cardID;

    /**
     * This message contains the ID of the starting card given to the client
     * @param playerNickname is the name of the active client
     * @param cardID is the ID of the starting card given to the client
     */
    public StartingCardResultMessage(String playerNickname, int cardID) {
        super("POSITIVE_STARTING_CARD");
        this.clientNickname = playerNickname;
        this.cardID = cardID;
    }

    /**
     * It updates the small model of the client with the starting card given from the server
     * It sets the next state of the client state machine
     * @param context is the context of the client with his view and his network communication protocol
     */
    public void process(ClientContext context) {

        // TO DO: UPDATE SMALL MODEL
        
        if (this.clientNickname.equalsIgnoreCase(context.getNickname())) {
            context.getClientView().displayStartingCard(cardID);
            context.setState(new PassiveTokenChoiceState());
            context.getClientNetwork().send(new ChangeTurnRequestMessage());
        }
        else {
            context.getClientView().showPassiveStartingCard();
        }
    }
}
