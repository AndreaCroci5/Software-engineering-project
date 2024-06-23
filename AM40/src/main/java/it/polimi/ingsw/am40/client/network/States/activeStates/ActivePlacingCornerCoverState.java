package it.polimi.ingsw.am40.client.network.States.activeStates;

import it.polimi.ingsw.am40.client.network.Client;
import it.polimi.ingsw.am40.client.network.State;
import it.polimi.ingsw.am40.client.smallModel.SmallCard;
import it.polimi.ingsw.am40.client.smallModel.SmallCardLoader;
import it.polimi.ingsw.am40.server.model.Coordinates;

public class ActivePlacingCornerCoverState implements State {

    private int cardToPlaceID;

    private int cardToCoverID;

    public ActivePlacingCornerCoverState(int cardToPlaceID, int cardToCoverID) {
        this.cardToPlaceID = cardToPlaceID;
        this.cardToCoverID = cardToCoverID;
    }


    @Override
    public void execute(Client context) {
        context.getViewManager().displayPlacingCornerCover();
    }

    @Override
    public void checkInput(Client context, String input) {
        try {
            Integer.parseInt(input);
            if (Integer.parseInt(input) < 1 || Integer.parseInt(input) > 4) {
                System.out.println(">Wrong input");
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
