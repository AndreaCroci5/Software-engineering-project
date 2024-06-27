package it.polimi.ingsw.am40.client.network.States.activeStates;

import it.polimi.ingsw.am40.client.network.Client;
import it.polimi.ingsw.am40.client.network.State;
import it.polimi.ingsw.am40.client.smallModel.SmallCard;
import it.polimi.ingsw.am40.server.model.Coordinates;

/**
 * In this state client decide which corner cover on the card in his board
 */
public class ActivePlacingCornerCoverState implements State {

    /**
     * ID of the card that he wants to place
     */
    private int cardToPlaceID;

    /**
     * ID of the card that he wants to cover
     */
    private int cardToCoverID;

    /**
     * Constructor for the ActivePlacingCornerCoverState
     * @param cardToPlaceID is the ID of the card that he wants to place
     * @param cardToCoverID is the ID of the card that he wants to cover
     */
    public ActivePlacingCornerCoverState(int cardToPlaceID, int cardToCoverID) {
        this.cardToPlaceID = cardToPlaceID;
        this.cardToCoverID = cardToCoverID;
    }


    /**
     * Ask which corner he wants to cover
     * @param context is the context of the client with his view and his network communication protocol
     */
    @Override
    public void execute(Client context) {
        context.getViewManager().displayPlacingCornerCover();
    }

    /**
     * Input must be one of the four corners
     * @param context is the context of the client with his view and his network communication protocol
     * @param input is the input of the client
     */
    @Override
    public void checkInput(Client context, String input) {
        try {
            Integer.parseInt(input);
            if (Integer.parseInt(input) < 1 || Integer.parseInt(input) > 4) {
                System.out.println(">You must insert a number that is 1,2,3,4, no other numbers are accepted ");
            }
            else {
                int x = 0;
                int y = 0;
                for (SmallCard cardToCover : context.getSmallModel().getMyGrid()) {
                    if (cardToCover.getCardID() == this.cardToCoverID) {
                        x = cardToCover.getCoordinates().getX();
                        y = cardToCover.getCoordinates().getY();
                    }
                }
                if (Integer.parseInt(input) == 1 ) {
                    x = x - 1;
                }

                else if (Integer.parseInt(input) == 2 ) {
                    y = y + 1;
                }

                else if (Integer.parseInt(input) == 3 ) {
                    y = y - 1;
                }

                else if (Integer.parseInt(input) == 4) {
                    x = x + 1;
                }

                Coordinates coordinates = new Coordinates(x,y);

                context.setState(new ActivePlacingFaceChoiceState(this.cardToPlaceID, coordinates));
                context.getCurrentState().execute(context);
            }


        }catch (NumberFormatException e){
            System.out.println(">You must insert a number");
        }
    }
}
