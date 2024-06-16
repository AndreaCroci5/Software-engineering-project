package it.polimi.ingsw.am40.client.network.States.activeStates;

import it.polimi.ingsw.am40.client.ClientMessages.activeMessages.firstRound.AimCardChoiceMessage;
import it.polimi.ingsw.am40.client.network.Client;
import it.polimi.ingsw.am40.client.network.State;

import java.util.List;


public class ActiveAimCardChoiceState implements State {

    private final List<Integer> possibleInputs;

    public ActiveAimCardChoiceState(List<Integer> possibleInputs) {
        this.possibleInputs = possibleInputs;
    }

    /**
     * In this state the client select which aimCard he wants between the two options
     * A AimCardChoiceMessage with the user input will be sent back
     * @param context is the context of the client with his view and network choices
     */
    @Override
    public void execute(Client context) {
        // NOTHING TO DO
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
                System.out.println(">Wrong input");
            }
            else {
                context.getNetworkManager().send(new AimCardChoiceMessage(Integer.parseInt(input)));
            }
        }
        catch (NumberFormatException e) {
            System.out.println(">You must insert a number");
        }
    }
}
