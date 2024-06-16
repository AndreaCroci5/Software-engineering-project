package it.polimi.ingsw.am40.client.network.States.activeStates;

import it.polimi.ingsw.am40.client.network.Client;
import it.polimi.ingsw.am40.client.network.State;

public class UsernameChoiceState implements State {
    @Override
    public void execute(Client context) {
        context.getViewManager().displayInitialisation();
    }

    @Override
    public void checkInput(Client context, String input) {
        context.setNickname(input);
        context.setState(new SetUpState());
        context.getCurrentState().execute(context);
    }
}
