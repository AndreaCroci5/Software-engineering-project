package it.polimi.ingsw.am40.client.network.States.activeStates;

import it.polimi.ingsw.am40.client.network.Client;
import it.polimi.ingsw.am40.client.network.State;

import java.util.ArrayList;
import java.util.List;

public class ActivePlacingCardChoiceState implements State {

    /**
     * In this state the client choose which card he wants to play and where
     * A PlacingMessage with the input of the user will be sent back
     * @param context is the context of the client with his view and network choices
     */
    @Override
    public void execute(Client context) {
        context.getViewManager().displayPlacingCardChoice(context.getSmallModel().getMyHand(),context.getSmallModel().getMyGrid());
    }

    @Override
    public void checkInput(Client context,String input) {
        List<Integer> possibleInputs = new ArrayList<>();
        possibleInputs.add(context.getSmallModel().getMyHand().get(0).getCardID());
        possibleInputs.add(context.getSmallModel().getMyHand().get(1).getCardID());
        possibleInputs.add(context.getSmallModel().getMyHand().get(2).getCardID());

        try {
            Integer.parseInt(input);
            if (!possibleInputs.contains(Integer.parseInt(input))) {
                System.out.println(">Wrong input");
            }
            else {
                int index = 0;
                for (int i = 0; i < context.getSmallModel().getMyHand().size(); i++) {
                    if (context.getSmallModel().getMyHand().get(i).getCardID() == Integer.parseInt(input)) {
                        index = i;
                        break;
                    }
                }
                context.setState(new ActivePlacingCardToCoverChoiceState(index));
                context.getCurrentState().execute(context);
            }
        }catch (NumberFormatException e){
            System.out.println(">You must insert a number");
        }

    }
}
