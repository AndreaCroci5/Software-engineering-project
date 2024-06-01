package it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.flow;

import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.ClientMessages.activeMessages.firstRound.AimCardRequestMessage;
import it.polimi.ingsw.am40.client.ClientMessages.activeMessages.firstRound.StartingCardRequestMessage;
import it.polimi.ingsw.am40.client.ClientMessages.activeMessages.firstRound.TokenRequestMessage;
import it.polimi.ingsw.am40.client.network.ClientContext;
import it.polimi.ingsw.am40.client.network.States.activeStates.*;
import it.polimi.ingsw.am40.client.network.States.passiveStates.*;

public class ChangeTurnResponseMessage extends Message {

    /**
     * It's the name of the new active client
     */
    private final String clientNickname;

    /**
     * This message is sent by the server, and it says that the active client is changed
     * @param clientNickname is the name of the new active client
     */
    public ChangeTurnResponseMessage(String clientNickname) {
        super("CHANGE_TURN");
        this.clientNickname = clientNickname;
    }

    /**
     * It sets the next state of the client state machine based on the phase of the game
     * @param context is the context of the client with his view and his network communication protocol
     */
    public void process(ClientContext context) {
        if (this.clientNickname.equalsIgnoreCase(context.getNickname())) {
            if (context.getCurrentState().getClass().equals(PassiveTokenChoiceState.class)) {
                context.getClientNetwork().send(new TokenRequestMessage());
            }
            if (context.getCurrentState().getClass().equals(PassiveStartingCardChoiceState.class)) {
                context.getClientNetwork().send(new StartingCardRequestMessage());
            }
            if (context.getCurrentState().getClass().equals(PassiveAimCardChoiceState.class)) {
                context.getClientNetwork().send(new AimCardRequestMessage());
            }
            if (context.getCurrentState().getClass().equals(PassivePlacingState.class)) {
                context.setState(new ActivePlacingState());
            }
            if (context.getCurrentState().getClass().equals(PassiveDealCardsState.class)) {
                context.setState(new ActiveDealCardsState());
            }
        }
    }
}