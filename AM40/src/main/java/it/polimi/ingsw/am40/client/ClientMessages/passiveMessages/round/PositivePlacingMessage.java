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

    private final String clientNickname;

    private final int score;

    private final Map<CardElements,Integer> elementsCounter;

    private final ArrayList<Coordinates> placingCoordinates;

    private final int cardID;

    private final Coordinates coordsCardPlaced;

    private final String face;

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

    public void process(Client context) {
        if (context.getNickname().equals(clientNickname)) {

            // Updating small model
            SmallCard cardPlaced = SmallCardLoader.findCardById(this.cardID);
            assert cardPlaced != null;
            cardPlaced.setFace(this.face);
            cardPlaced.setCoordinates(this.coordsCardPlaced);
            context.getSmallModel().getMyGrid().add(cardPlaced);
            context.getSmallModel().setElementsCounter(this.elementsCounter);
            context.getSmallModel().getScoreBoard().put(this.clientNickname, this.score);


            // Updating view and the state
            context.getViewManager().displayPositivePlacing();
            context.setState(new ActiveDrawChoiceState());

        }
        else {
            context.getViewManager().displayPassivePlacingResult(this.clientNickname);
        }
    }
}
