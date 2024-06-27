package it.polimi.ingsw.am40.client.network.States.activeStates;

import it.polimi.ingsw.am40.client.ClientMessages.activeMessages.firstRound.TokenChoiceMessage;
import it.polimi.ingsw.am40.client.network.Client;
import it.polimi.ingsw.am40.client.network.State;

import java.util.List;

/**
 * In this state the client choose which is the token he wants
 */
public class ActiveTokenChoiceState implements State {

    /**
     * These are the possible tokens the client can choose
     */
    private final List<String> possibleTokens;

    /**
     * Constructor for the ActiveTokenChoiceState
     * @param possibleTokens are the possible tokens the client can choose
     */
    public ActiveTokenChoiceState(List<String> possibleTokens) {
        this.possibleTokens = possibleTokens;
    }

    /**
     * Execute for this state
     * @param context is the context of the client with his view and network choices
     */
    @Override
    public void execute(Client context) {

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
        System.out.println(">The token you selected is not among the available tokens ");
    }
}
