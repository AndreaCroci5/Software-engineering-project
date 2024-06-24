package it.polimi.ingsw.am40.client.network.States.activeStates;

import it.polimi.ingsw.am40.client.network.Client;
import it.polimi.ingsw.am40.client.network.State;
import it.polimi.ingsw.am40.client.smallModel.SmallCard;

import java.util.ArrayList;
import java.util.List;

public class ActivePlacingCardToCoverChoiceState implements State {

    private int cardId;

    public ActivePlacingCardToCoverChoiceState(int cardId) {
        this.cardId = cardId;
    }


    @Override
    public void execute(Client context) {
        context.getViewManager().displayPlacingCardToCoverChoice(context.getSmallModel().getMyGrid());
    }

    @Override
    public void checkInput(Client context, String input) {
        List<Integer> possibleInputs = new ArrayList<>();
        for (SmallCard card : context.getSmallModel().getMyGrid()) {
            possibleInputs.add(card.getCardID());
        }

        try {
            Integer.parseInt(input);
            if (possibleInputs.contains(Integer.parseInt(input))) {
                context.setState(new ActivePlacingCornerCoverState(this.cardId,Integer.parseInt(input)));
                context.getCurrentState().execute(context);
            }
            else {
                System.out.println(">You insert a number that is not in the available IDs ");
            }
        }catch (NumberFormatException e){
            System.out.println(">You must insert a number");
        }
    }
}
