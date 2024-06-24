package it.polimi.ingsw.am40.client.network.States.activeStates;

import it.polimi.ingsw.am40.client.ClientMessages.activeMessages.round.PlacingMessage;
import it.polimi.ingsw.am40.client.network.Client;
import it.polimi.ingsw.am40.client.network.State;
import it.polimi.ingsw.am40.server.model.Coordinates;

public class ActivePlacingFaceChoiceState implements State {

    private int cardToPlaceID;

    private Coordinates coordinates;

    public ActivePlacingFaceChoiceState(int cardToPlaceID, Coordinates coordinates) {
        this.cardToPlaceID = cardToPlaceID;
        this.coordinates = coordinates;
    }


    @Override
    public void execute(Client context) {
        context.getViewManager().displayPlacingFaceChoice();
    }

    @Override
    public void checkInput(Client context, String input) {
        if (!input.equalsIgnoreCase("front") && !input.equalsIgnoreCase("back")) {
            System.out.println(">You must insert 'front' or 'back' ");
        }
        else {
            context.getNetworkManager().send(new PlacingMessage(context.getNickname(),this.cardToPlaceID,this.coordinates,input));
        }
    }
}
