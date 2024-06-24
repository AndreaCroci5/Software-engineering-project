package it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.round;

import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.ClientMessages.activeMessages.flow.ChangeTurnRequestMessage;
import it.polimi.ingsw.am40.client.network.Client;
import it.polimi.ingsw.am40.client.network.States.passiveStates.PassivePlacingState;
import it.polimi.ingsw.am40.client.smallModel.SmallCard;
import it.polimi.ingsw.am40.client.smallModel.SmallCardLoader;

public class PositiveDrawMessage extends Message {

    private final String clientNickname;

    private final int cardDrawnID;

    private final int cardReplacedID;

    private final int replacePosition;

    private final int cardOnTopOfDeck;

    public PositiveDrawMessage(String clientNickname, int cardDrawnID, int cardReplacedID, int replacePosition, int cardOnTopOfDeck) {
        super("POSITIVE_DRAW",clientNickname);
        this.clientNickname = clientNickname;
        this.cardDrawnID = cardDrawnID;
        this.cardReplacedID = cardReplacedID;
        this.replacePosition = replacePosition;
        this.cardOnTopOfDeck = cardOnTopOfDeck;
    }

    public void process(Client context) {

        // Update commonBoard
        SmallCard cardReplaced = SmallCardLoader.findCardById(cardReplacedID);
        assert cardReplaced != null;
        context.getSmallModel().getCommonBoard().set(replacePosition, cardReplaced);

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
    }
}
