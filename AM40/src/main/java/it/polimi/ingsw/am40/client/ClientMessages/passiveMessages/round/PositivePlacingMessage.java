package it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.round;

import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.network.Client;
import it.polimi.ingsw.am40.client.network.States.activeStates.ActiveDrawChoiceState;
import it.polimi.ingsw.am40.client.smallModel.SmallCard;
import it.polimi.ingsw.am40.client.smallModel.SmallCardLoader;
import it.polimi.ingsw.am40.server.model.CardElements;
import it.polimi.ingsw.am40.server.model.Coordinates;
import java.util.ArrayList;
import java.util.Map;

public class PositivePlacingMessage extends Message {

    /**
     * It's the name of the active client
     */
    private final String clientNickname;

    /**
     * It's the score due to the card he placed
     */
    private final int score;

    /**
     * It's the counter of the elements on the player private board
     */
    private final Map<CardElements,Integer> elementsCounter;

    /**
     * These are the possible coordinates the player can place his next card
     */
    private final ArrayList<Coordinates> placingCoordinates;

    /**
     * It's the ID of the card placed
     */
    private final int cardID;

    /**
     * These are the coordinates on which he placed the card
     */
    private final Coordinates coordsCardPlaced;

    /**
     * It's the face on which he placed the card
     */
    private final String face;

    /**
     * This message is sent by the server and it contains all the updates due to the placing
     * @param clientNickname is the name of the active client
     * @param score is the score due to the card he placed
     * @param elementsCounter is the counter of the elements on the player private board
     * @param placingCoordinates are the possible coordinates the player can place his next card
     * @param cardID is the ID of the card placed
     * @param coordsCardPlaced are the coordinates on which he placed the card
     * @param face is the face on which he placed the card
     */
    public PositivePlacingMessage(String clientNickname, int score, Map<CardElements, Integer> elementsCounter, ArrayList<Coordinates> placingCoordinates, int cardID, Coordinates coordsCardPlaced, String face) {
        super("POSITIVE_PLACING",clientNickname);
        this.clientNickname = clientNickname;
        this.score = score;
        this.elementsCounter = elementsCounter;
        this.placingCoordinates = placingCoordinates;
        this.cardID = cardID;
        this.coordsCardPlaced = coordsCardPlaced;
        this.face = face;
    }

    /**
     * It updates the private board,elements counter and placingCoordinates of the active client
     * It updates scoreboard for all clients
     * It updates the otherPlayersGrid of the other clients
     * @param context is the context of the client with his view and his network communication protocol
     */
    public void process(Client context) {

        // Updating small model
        SmallCard cardPlaced = SmallCardLoader.findCardById(this.cardID);
        assert cardPlaced != null;
        cardPlaced.setFace(this.face);
        cardPlaced.setCoordinates(this.coordsCardPlaced);

        context.getSmallModel().getScoreBoard().put(this.clientNickname, this.score);

        if (context.getNickname().equals(clientNickname)) {

            context.getSmallModel().getMyGrid().add(cardPlaced);
            context.getSmallModel().setElementsCounter(this.elementsCounter);
            context.getSmallModel().setPlacingCoordinates(this.placingCoordinates);

            // Updating view and the state
            context.getViewManager().displayPositivePlacing();
            context.setState(new ActiveDrawChoiceState());

        }
        else {
            context.getSmallModel().getOtherPlayersGrid().get(this.clientNickname).add(cardPlaced);

            context.getViewManager().displayPassivePlacingResult(this.clientNickname);
        }
    }
}
