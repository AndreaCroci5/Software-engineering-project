package it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.firstRound;

import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.network.Client;
import it.polimi.ingsw.am40.client.network.States.activeStates.ActiveAimCardChoiceState;

import java.util.List;

/**
 * This message contains the two aim cards for the client choice
 */
public class AimCardInfoMessage extends Message {

    /**
     * The nickname of the active client
     */
    private final String clientNickname;

    /**
     * It's the list of the two cards in which the client has to choose one of them
     */
    private final List<Integer> aimCardsID;

    /**
     * Constructor for aimCardInfoMessage
     * @param clientNickname is the name of the active client
     * @param amiCardsID is the list of the two cards in which the client has to choose one of them
     */
    public AimCardInfoMessage(String clientNickname, List<Integer> amiCardsID) {
        super("AIM_CARD_INFO",clientNickname);
        this.clientNickname = clientNickname;
        this.aimCardsID = amiCardsID;
    }

    /**
     * It shows the two aim cards for the user choice
     * It sets the next state of the client state machine
     * @param context is the context of the client with his view and his network communication protocol
     */
    public void process(Client context) {

        if (context.getNickname().equalsIgnoreCase(this.clientNickname)) {
            context.getViewManager().displayAimCardsToChoose(this.aimCardsID);
            context.setState(new ActiveAimCardChoiceState(this.aimCardsID));
        }
        else {
            context.getViewManager().showPassiveAimState(this.clientNickname);
        }
    }
}
