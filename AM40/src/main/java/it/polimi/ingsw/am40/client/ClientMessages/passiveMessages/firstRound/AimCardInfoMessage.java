package it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.firstRound;

import it.polimi.ingsw.am40.client.ClientMessages.Message;

import java.util.List;

public class AimCardInfoMessage extends Message {

    /**
     * It's the list of the two cards in which the client has to choose one of them
     */
    private final List<Integer> amiCardsID;

    /**
     * This message contains the two aim cards for the client choice
     * @param amiCardsID is the list of the two cards in which the client has to choose one of them
     */
    public AimCardInfoMessage(List<Integer> amiCardsID) {
        super("AIM_CARD_INFO");
        this.amiCardsID = amiCardsID;
    }

    /**
     * It shows the two aim cards for the user choice
     * It sets the next state of the client state machine
     * @param context is the context of the client with his view and his network communication protocol
     */
   public void process(ClientContext context) {
        // show the two cards that the player has to choose
        context.getClientView().displayAimCardsToChoose(this.amiCardsID);

        // set the new state
        context.setState(new ActiveAimCardChoiceState());
    }
}
