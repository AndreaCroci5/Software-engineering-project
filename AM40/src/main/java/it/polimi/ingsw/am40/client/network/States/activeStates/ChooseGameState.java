package it.polimi.ingsw.am40.client.network.States.activeStates;

import it.polimi.ingsw.am40.client.ClientMessages.activeMessages.flow.GameIdChoiceMessage;
import it.polimi.ingsw.am40.client.network.Client;
import it.polimi.ingsw.am40.client.network.State;

import java.util.ArrayList;
import java.util.Map;

/**
 * This state ask the client which game he wants to join after showing him
 * the list of all possible game he can join
 */
public class ChooseGameState implements State {

    /**
     * It's the map with all the current parties
     */
    private Map<Integer, ArrayList<Integer>> currentParties;

    /**
     * Constructor for the ChooseGameState
     * @param currentParties is the map of the current parties
     */
    public ChooseGameState(Map<Integer, ArrayList<Integer>> currentParties) {
        this.currentParties = currentParties;
    }

    /**
     * Execute for this state
     * @param context is the context of the client with his view and his network communication protocol
     */
    @Override
    public void execute(Client context) {

    }

    /**
     * In this state the input must be a number and must be in the possible games IDs
     * @param context is the context of the client with his view and his network communication protocol
     * @param input is the input of the client
     */
    @Override
    public void checkInput(Client context, String input) {

        // check if it's an integer and send back message
        try {
            Integer.parseInt(input);
            if (this.currentParties.containsKey(Integer.parseInt(input))) {
                context.getNetworkManager().send(new GameIdChoiceMessage(context.getNickname(), Integer.parseInt(input)));
            }
            else {
                System.out.println(">The ID of the game you choose is not among the available IDs ");
            }
        }
        catch (NumberFormatException e) {
            System.out.println(">You must insert a number");
        }
    }
}
