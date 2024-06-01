package it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.firstRound;

import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.ClientMessages.activeMessages.flow.ChangeTurnRequestMessage;
import it.polimi.ingsw.am40.client.network.ClientContext;
import it.polimi.ingsw.am40.client.network.States.passiveStates.PassiveAimCardChoiceState;
import it.polimi.ingsw.am40.client.network.States.passiveStates.PassivePlacingState;

import java.util.List;

public class DealCardsResponseMessage extends Message {

    /**
     * It's the name of the active client
     */
    private final String clientNickname;

    /**
     * It's the list of the 3 cards of the client
     */
    private final List<Integer> handCards;

    /**
     * This message is used to give the client his 3 cards
     * @param handCards is the list of the 3 cards of the client
     * @param clientNickname is the name of the active client
     */
    public DealCardsResponseMessage(List<Integer> handCards, String clientNickname) {
        super("DEAL_CARDS_RESPONSE");
        this.clientNickname = clientNickname;
        this.handCards = handCards;
    }

    /**
     * It updates the small model of the client with his 3 cards
     * It sets the next state of the client state machine
     * @param context is the context of the client with his view and his network communication protocol
     */
    public void process(ClientContext context) {

        // UPDATE SMALL MODEL

        if (context.getNickname().equalsIgnoreCase(this.clientNickname)) {
            context.getClientView().displayPlayerHand();
            context.setState(new PassiveAimCardChoiceState());
            context.getClientNetwork().send(new ChangeTurnRequestMessage());
        }
    }
}
