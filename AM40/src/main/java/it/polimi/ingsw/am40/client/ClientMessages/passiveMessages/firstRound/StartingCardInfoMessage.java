package it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.firstRound;

import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.network.Client;
import it.polimi.ingsw.am40.client.network.States.activeStates.ActiveStartingCardChoiceState;

public class StartingCardInfoMessage extends Message {

    /**
     * It's the name of the active client
     */
    private final String clientNickname;

    /**
     * It's the ID of the starting card of the client
     */
    private final int StartingCardID;

    /**
     * This message is used to give the client his starting card
     * @param startingCardID is the ID of the starting card of the client
     * @param clientNickname is the name of the active client
     */
    public StartingCardInfoMessage(String clientNickname, int startingCardID) {
        super("STARTING_CARD_INFO",clientNickname);
        this.clientNickname = clientNickname;
        this.StartingCardID = startingCardID;
    }

    /**
     * It shows the client his starting card and it asks if he wants to place front or back
     * It sets the next state of the client state machine
     * @param context is the context of the client with his view and his network communication protocol
     */
    public void process(Client context) {
        if (context.getNickname().equalsIgnoreCase(this.clientNickname)) {
            context.getViewManager().displayStartingCardInfo(this.StartingCardID);
            context.setState(new ActiveStartingCardChoiceState());
        }
        else {
            context.getViewManager().showPassiveStartingCardState(this.clientNickname);
        }
    }
}
