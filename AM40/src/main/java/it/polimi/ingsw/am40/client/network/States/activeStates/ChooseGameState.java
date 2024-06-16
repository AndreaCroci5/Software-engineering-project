package it.polimi.ingsw.am40.client.network.States.activeStates;

import it.polimi.ingsw.am40.client.ClientMessages.activeMessages.flow.GameIdChoiceMessage;
import it.polimi.ingsw.am40.client.network.Client;
import it.polimi.ingsw.am40.client.network.State;

import java.util.List;


public class ChooseGameState implements State {

    private final List<Integer> gameIds;

    public ChooseGameState(List<Integer> gameIds) {
        this.gameIds = gameIds;
    }

    /**
     * This state ask the client which game he wants to join after showing him
     * the list of all possible game he can join
     * @param context is the context of the client with his view and his network communication protocol
     */
    @Override
    public void execute(Client context) {
        // Nothing just waiting for the input
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
            if (this.gameIds.contains(Integer.parseInt(input))) {
                context.getNetworkManager().send(new GameIdChoiceMessage(Integer.parseInt(input)));
            }
            else {
                System.out.println(">Wrong input");
            }
        }
        catch (NumberFormatException e) {
            System.out.println(">You must insert a number");
        }
    }
}
