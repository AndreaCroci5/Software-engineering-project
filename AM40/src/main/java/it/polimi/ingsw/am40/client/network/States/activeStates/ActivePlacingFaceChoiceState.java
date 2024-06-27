package it.polimi.ingsw.am40.client.network.States.activeStates;

import it.polimi.ingsw.am40.client.ClientMessages.activeMessages.round.PlacingMessage;
import it.polimi.ingsw.am40.client.network.Client;
import it.polimi.ingsw.am40.client.network.State;
import it.polimi.ingsw.am40.server.model.Coordinates;

/**
 * In this state the client decide on which face placing his card
 */
public class ActivePlacingFaceChoiceState implements State {

    /**
     * ID of the card that he wants to place
     */
    private int cardToPlaceID;

    /**
     * Coordinates where he wants to place the card
     */
    private Coordinates coordinates;

    /**
     * Constructor for the ActivePlacingFaceChoiceState
     * @param cardToPlaceID is the ID of the card that he wants to place
     * @param coordinates are the coordinates where he wants to place the card
     */
    public ActivePlacingFaceChoiceState(int cardToPlaceID, Coordinates coordinates) {
        this.cardToPlaceID = cardToPlaceID;
        this.coordinates = coordinates;
    }


    /**
     * Asks on which face he wants to place the card
     * @param context is the context of the client with his view and his network communication protocol
     */
    @Override
    public void execute(Client context) {
        context.getViewManager().displayPlacingFaceChoice();
    }

    /**
     * Input must be front or back
     * @param context is the context of the client with his view and his network communication protocol
     * @param input is the input of the client
     */
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
