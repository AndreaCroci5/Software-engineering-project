package it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.round;

import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.ClientMessages.activeMessages.flow.ChangeTurnRequestMessage;
import it.polimi.ingsw.am40.client.network.Client;
import it.polimi.ingsw.am40.client.network.States.passiveStates.PassivePlacingState;
import it.polimi.ingsw.am40.client.smallModel.SmallCard;
import it.polimi.ingsw.am40.client.smallModel.SmallCardLoader;

/**
 * This message is sent from the server, and it contains all the updates of the game after a draw
 */
public class PositiveDrawMessage extends Message {

    /**
     * It's the name of the active client
     */
    private final String clientNickname;

    /**
     * It's the ID of the card drawn by the client
     */
    private final int cardDrawnID;

    /**
     * It's the ID of the card that replace the card drawn
     */
    private final int cardReplacedID;

    /**
     * It's the position of the card that replace the card drawn
     */
    private final int replacePosition;

    /**
     * It's the ID of the card that is now on top of the deck
     */
    private final int cardOnTopOfDeck;

    /**
     * Constructor for the PositiveDrawMessage
     * @param clientNickname is the name of the active client
     * @param cardDrawnID is the ID of the card drawn by the client
     * @param cardReplacedID is the ID of the card that replace the card drawn
     * @param replacePosition is the position of the card that replace the card drawn
     * @param cardOnTopOfDeck is the ID of the card that is now on top of the deck
     */
    public PositiveDrawMessage(String clientNickname, int cardDrawnID, int cardReplacedID, int replacePosition, int cardOnTopOfDeck) {
        super("POSITIVE_DRAW",clientNickname);
        this.clientNickname = clientNickname;
        this.cardDrawnID = cardDrawnID;
        this.cardReplacedID = cardReplacedID;
        this.replacePosition = replacePosition;
        this.cardOnTopOfDeck = cardOnTopOfDeck;
    }

    /**
     * It updates the common board and the card in the hand of the client
     * It sets the new state
     * @param context is the context of the client with his view and his network communication protocol
     */
    public void process(Client context) {

        // Update commonBoard
        SmallCard cardReplaced = SmallCardLoader.findCardById(cardReplacedID);
        assert cardReplaced != null;

        int goldReplacePosition = 0;

        if (replacePosition == 0 || replacePosition == 1) {
            if (cardReplaced.getRequires() == null) {
                context.getSmallModel().getCommonBoard().set(replacePosition, cardReplaced);
            }
            else {
                goldReplacePosition = replacePosition + 3;
                context.getSmallModel().getCommonBoard().set(goldReplacePosition, cardReplaced);
            }
        }


        SmallCard cardDeck = SmallCardLoader.findCardById(cardOnTopOfDeck);
        assert cardDeck != null;

        if (cardDeck.getRequires() == null ) {
            context.getSmallModel().getCommonBoard().set(2,cardDeck);
        }
        else {
            context.getSmallModel().getCommonBoard().set(5,cardDeck);
        }


        if (context.getNickname().equalsIgnoreCase(this.clientNickname)) {

            // Update small model
            SmallCard cardDrawn = SmallCardLoader.findCardById(cardDrawnID);
            assert cardDrawn != null;

            int myHandIndex = 0;
            for (int i=0; i < context.getSmallModel().getMyHand().size(); i++) {
                if (context.getSmallModel().getMyHand().get(i).getFace() != null) {
                    myHandIndex = i;
                }
            }

            context.getSmallModel().getMyHand().remove(context.getSmallModel().getMyHand().get(myHandIndex));
            context.getSmallModel().getMyHand().add(cardDrawn);

            context.getViewManager().displayPositiveDraw();

            context.setState(new PassivePlacingState());
            context.getNetworkManager().send(new ChangeTurnRequestMessage(this.clientNickname));
        }
        else {
            context.getViewManager().displayPassiveDrawResult(this.clientNickname);
        }
    }
}
