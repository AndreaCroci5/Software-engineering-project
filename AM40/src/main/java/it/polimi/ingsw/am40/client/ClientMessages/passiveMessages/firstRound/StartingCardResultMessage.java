package it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.firstRound;

import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.ClientMessages.activeMessages.flow.ChangeTurnRequestMessage;
import it.polimi.ingsw.am40.client.network.Client;
import it.polimi.ingsw.am40.client.network.States.passiveStates.PassiveTokenChoiceState;
import it.polimi.ingsw.am40.server.model.CardElements;
import it.polimi.ingsw.am40.server.model.Coordinates;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StartingCardResultMessage extends Message {

    /**
     * It's the nickname of the active client
     */
    private final String clientNickname;

    /**
     * It's the ID of the starting card given to the client
     */
    private final int cardID;

    private final Coordinates startingCardCoords;

    private final String cardFace;

    private final ArrayList<Coordinates> placingCoordinates;

    private final Map<CardElements,Integer> elementsCounter;

    /**
     * This message contains the ID of the starting card given to the client
     * @param clientNickname is the name of the active client
     * @param cardID is the ID of the starting card given to the client
     */
    public StartingCardResultMessage(String clientNickname, int cardID, Coordinates startingCardCoords, String cardFace, ArrayList<Coordinates> placingCoordinates, Map<CardElements,Integer> elementsCounter) {
        super("POSITIVE_STARTING_CARD",clientNickname);
        this.clientNickname = clientNickname;
        this.cardID = cardID;
        this.startingCardCoords = startingCardCoords;
        this.cardFace = cardFace;
        this.placingCoordinates = placingCoordinates;
        this.elementsCounter = elementsCounter;
    }

    /**
     * It updates the small model of the client with the starting card given from the server
     * It sets the next state of the client state machine
     * @param context is the context of the client with his view and his network communication protocol
     */
    public void process(Client context) {

        // TO DO: UPDATE SMALL MODEL
        // Set grid

        if (this.clientNickname.equalsIgnoreCase(context.getNickname())) {
            context.getViewManager().displayStartingCardResult(cardID,this.cardFace);
            context.setState(new PassiveTokenChoiceState());
            context.getNetworkManager().send(new ChangeTurnRequestMessage(context.getNickname()));
        }
        else {
            context.getViewManager().showPassiveStartingCard(this.clientNickname,this.cardID,this.cardFace);
        }
    }
}
