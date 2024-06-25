package it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.firstRound;

import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.ClientMessages.activeMessages.flow.ChangeTurnRequestMessage;
import it.polimi.ingsw.am40.client.network.Client;
import it.polimi.ingsw.am40.client.network.States.passiveStates.PassiveTokenChoiceState;
import it.polimi.ingsw.am40.client.smallModel.SmallCard;
import it.polimi.ingsw.am40.client.smallModel.SmallCardLoader;
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

    /**
     * This are the coordinates of the starting card
     */
    private final Coordinates startingCardCoords;

    /**
     * This is the face on which the starting card has been placed
     */
    private final String cardFace;

    /**
     * This are the possible coordinates that the player can choose to place the next card
     */
    private final ArrayList<Coordinates> placingCoordinates;

    /**
     * This is the counter of the elements on the player private board
     */
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
     * It updates the personal grid and the other players grid, personal elements counter and personal placingCoordinates
     * It sets the next state of the client state machine
     * @param context is the context of the client with his view and his network communication protocol
     */
    public void process(Client context) {

        SmallCard startingCard = SmallCardLoader.findCardById(this.cardID);
        assert startingCard != null;
        startingCard.setCoordinates(this.startingCardCoords);
        startingCard.setFace(this.cardFace);

        if (this.clientNickname.equalsIgnoreCase(context.getNickname())) {

            // Update small model
            context.getSmallModel().setMyGrid(new ArrayList<>());
            context.getSmallModel().getMyGrid().add(startingCard);
            context.getSmallModel().setElementsCounter(new HashMap<>());
            context.getSmallModel().setElementsCounter(this.elementsCounter);
            context.getSmallModel().setPlacingCoordinates(new ArrayList<>());
            context.getSmallModel().setPlacingCoordinates(this.placingCoordinates);


            // Update view and state
            context.getViewManager().displayStartingCardResult(cardID,this.cardFace);
            context.setState(new PassiveTokenChoiceState());
            context.getNetworkManager().send(new ChangeTurnRequestMessage(context.getNickname()));
        }
        else {

            if (context.getSmallModel().getOtherPlayersGrid() == null) {
                context.getSmallModel().setOtherPlayersGrid(new HashMap<>());
                context.getSmallModel().getOtherPlayersGrid().put(this.clientNickname,null);
            }

            if (!context.getSmallModel().getOtherPlayersGrid().containsKey(this.clientNickname)) {
                context.getSmallModel().getOtherPlayersGrid().put(this.clientNickname,null);
            }

            ArrayList<SmallCard> otherPlayerCards = context.getSmallModel().getOtherPlayersGrid().get(this.clientNickname);
            if (otherPlayerCards != null) {
                otherPlayerCards.add(startingCard);
            } else {
                ArrayList<SmallCard> newPlayerCards = new ArrayList<>();
                newPlayerCards.add(startingCard);
                context.getSmallModel().getOtherPlayersGrid().put(this.clientNickname, newPlayerCards);
            }

            context.getViewManager().showPassiveStartingCard(this.clientNickname,this.cardID,this.cardFace);
        }
    }
}
