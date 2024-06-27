package it.polimi.ingsw.am40.client.network.States.activeStates;

import it.polimi.ingsw.am40.client.ClientMessages.activeMessages.firstRound.AimCardChoiceMessage;
import it.polimi.ingsw.am40.client.network.Client;
import it.polimi.ingsw.am40.client.network.State;

import java.util.List;

/**
 * In this state the client select which aimCard he wants between the two options
 * A AimCardChoiceMessage with the user input will be sent back
 */
public class ActiveAimCardChoiceState implements State {

    /**
     * Possible inputs for the user
     */
    private final List<Integer> possibleInputs;

    /**
     * Constructor for ActiveAimCardChoiceState
     * @param possibleInputs are the possible inputs for the user
     */
    public ActiveAimCardChoiceState(List<Integer> possibleInputs) {
        this.possibleInputs = possibleInputs;
    }

    /**
     * Execute for this state
     * @param context is the context of the client with his view and network choices
     */
    @Override
    public void execute(Client context) {

    }

    /**
     * In this state input must be a number and input must be an ID of the possible aim cards IDs
     * @param context is the context of the client with his view and his network communication protocol
     * @param input is the input of the client
     */
    @Override
    public void checkInput(Client context,String input) {
        try {
            Integer.parseInt(input);
            if (!possibleInputs.contains(Integer.parseInt(input))) {
                System.out.println(">You insert a number that is not among the available IDs ");
            }
            else {
                context.getNetworkManager().send(new AimCardChoiceMessage(context.getNickname(),Integer.parseInt(input)));
            }
        }
        catch (NumberFormatException e) {
            System.out.println(">You must insert a number");
        }
    }
}
