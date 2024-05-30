package it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.round;

import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.network.ClientContext;
import it.polimi.ingsw.am40.server.model.CardElements;
import it.polimi.ingsw.am40.server.model.Coordinates;
import java.util.ArrayList;
import java.util.Map;

public class PositivePlacingMessage extends Message {

    private final String clientNickname;

    private final int score;

    private final Map<CardElements,Integer> elementsCounter;

    private final ArrayList<Coordinates> placingCoordinates;

    private final int cardID;

    public PositivePlacingMessage(String clientNickname, int score, Map<CardElements, Integer> elementsCounter, ArrayList<Coordinates> placingCoordinates, int cardID) {
        super("POSITIVE_PLACING");
        this.clientNickname = clientNickname;
        this.score = score;
        this.elementsCounter = elementsCounter;
        this.placingCoordinates = placingCoordinates;
        this.cardID = cardID;
    }

    public void process(ClientContext clientContext) {
        // UPDATE SMALL MODEL
        // SHOW INFORMATION
        // SET NEW STATE
    }
}
