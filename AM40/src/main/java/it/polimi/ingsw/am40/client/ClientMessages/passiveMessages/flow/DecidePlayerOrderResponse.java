package it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.flow;

import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.network.Client;
import it.polimi.ingsw.am40.client.network.States.activeStates.ActivePlacingState;
import it.polimi.ingsw.am40.client.network.States.passiveStates.PassivePlacingState;

import java.util.List;

public class DecidePlayerOrderResponse extends Message {

    /**
     * It's the name of the active client
     */
    private final String clientNickname;

    /**
     * It's a list that tells the order of the players
     */
    private final List<String> playersOrder;

    /**
     * This message is sent by the server and tells the order of the players
     * @param clientNickname is the name of the active client
     * @param playersOrder is the order of the players
     */
    public DecidePlayerOrderResponse(String clientNickname, List<String> playersOrder) {
        super("PLAYER_ORDER_RESPONSE",clientNickname);
        this.clientNickname = clientNickname;
        this.playersOrder = playersOrder;
    }

    /**
     * It sets the next state of the client state machine
     * It sets active the first player and passive al the others
     * @param context is the context of the client with his view and his network communication protocol
     */
    public void process(Client context) {

        context.getViewManager().displayPlayerOrder(this.playersOrder);

        if (context.getNickname().equalsIgnoreCase(this.playersOrder.getFirst())) {
            context.setState(new ActivePlacingState());
        }
        else {
            context.setState(new PassivePlacingState());
        }
    }

}
