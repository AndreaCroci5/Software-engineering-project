package it.polimi.ingsw.am40.client.network.States.activeStates;

import it.polimi.ingsw.am40.client.ClientMessages.activeMessages.firstRound.TokenChoiceMessage;
import it.polimi.ingsw.am40.client.network.Client;
import it.polimi.ingsw.am40.client.network.State;

import java.util.List;

public class ActiveTokenChoiceState implements State {

    private final List<String> possibleTokens;

    public ActiveTokenChoiceState(List<String> possibleTokens) {
        this.possibleTokens = possibleTokens;
    }

    /**
     * In this state the client choose which is the token he wants
     * @param context is the context of the client with his view and network choices
     */
    @Override
    public void execute(Client context) {
        // Nothing to do
    }

    /**
     * In this state input must be a color that is in the possible color tokens
     * @param context is the context of the client with his view and his network communication protocol
     * @param input is the input of the client
     */
    @Override
    public void checkInput(Client context, String input) {
        for (String token : possibleTokens) {
            if (input.equalsIgnoreCase(token)) {
                context.getNetworkManager().send(new TokenChoiceMessage(context.getNickname(), token));
                return;
            }
        }
        System.out.println(">The token you selected is not on the available tokens ");
    }
}
