package it.polimi.ingsw.am40.client.network.States.activeStates;

import it.polimi.ingsw.am40.client.network.Client;
import it.polimi.ingsw.am40.client.network.State;
import it.polimi.ingsw.am40.client.smallModel.SmallCard;

import java.util.ArrayList;
import java.util.List;

/**
 * In this state the client choose which card to cover in his private board
 */
public class ActivePlacingCardToCoverChoiceState implements State {

    /**
     * ID of the card the client want to place
     */
    private int cardId;

    /**
     * Constructor for the ActivePlacingCardToCoverChoiceState
     * @param cardId is the ID of the card the client want to place
     */
    public ActivePlacingCardToCoverChoiceState(int cardId) {
        this.cardId = cardId;
    }


    /**
     * Asks for the card to cover on the board
     * @param context is the context of the client with his view and his network communication protocol
     */
    @Override
    public void execute(Client context) {
        context.getViewManager().displayPlacingCardToCoverChoice(context.getSmallModel().getMyGrid());
    }

    /**
     * Input must be an ID of a card in his personal board
     * @param context is the context of the client with his view and his network communication protocol
     * @param input is the input of the client
     */
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
                System.out.println(">You insert a number that is not among the available IDs ");
            }
        }catch (NumberFormatException e){
            System.out.println(">You must insert a number");
        }
    }
}
