package it.polimi.ingsw.am40.client.network.States.activeStates;

import it.polimi.ingsw.am40.client.ClientMessages.activeMessages.firstRound.StartingCardChoiceMessage;
import it.polimi.ingsw.am40.client.network.Client;
import it.polimi.ingsw.am40.client.network.State;

import java.util.ArrayList;
import java.util.List;

public class ActiveStartingCardChoiceState implements State {

    /**
     * In this state after showing the client his starting card
     * he will choose if place this card face up or face down
     * @param context is the context of the client with his view and his network communication protocol
     */
    @Override
    public void execute(Client context) {
        context.getViewManager().askStartingFace();
    }

    /**
     * In this state input must be only front or back in order to choose how to place the starting card
     * @param context is the context of the client with his view and his network communication protocol
     * @param input is the input of the client
     */
    @Override
    public void checkInput(Client context, String input) {
        List<String> possibleChoices = new ArrayList<String>();
        possibleChoices.add("front");
        possibleChoices.add("back");
        if (!possibleChoices.contains(input.toLowerCase())) {
            System.out.println(">Wrong input");
        }
        else {
            context.getNetworkManager().send(new StartingCardChoiceMessage(context.getNickname(),input));
        }
    }

}
