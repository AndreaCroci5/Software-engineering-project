package it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.firstRound;

import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.ClientMessages.activeMessages.flow.ChangeTurnRequestMessage;
import it.polimi.ingsw.am40.client.network.Client;
import it.polimi.ingsw.am40.client.network.States.passiveStates.PassiveAimCardChoiceState;
import it.polimi.ingsw.am40.client.smallModel.SmallCard;
import it.polimi.ingsw.am40.client.smallModel.SmallCardLoader;

import java.util.ArrayList;
import java.util.List;

public class DealCardsResponseMessage extends Message {

    /**
     * It's the name of the active client
     */
    private final String clientNickname;

    /**
     * It's the list of the 3 cards of the client
     */
    private final List<Integer> handCards;

    private final int deckResourceCardID;

    private final int deckGoldenCardID;

    /**
     * This message is used to give the client his 3 cards
     * @param handCards is the list of the 3 cards of the client
     * @param clientNickname is the name of the active client
     */
    public DealCardsResponseMessage(String clientNickname, List<Integer> handCards, int deckResourceCardID, int deckGoldenCardID) {
        super("DEAL_CARDS_RESPONSE",clientNickname);
        this.clientNickname = clientNickname;
        this.handCards = handCards;
        this.deckResourceCardID = deckResourceCardID;
        this.deckGoldenCardID = deckGoldenCardID;
    }

    /**
     * It updates the small model of the client with his 3 cards
     * It sets the next state of the client state machine
     * @param context is the context of the client with his view and his network communication protocol
     */
    public void process(Client context) {

        // Update commonBoard
        SmallCard resCard = SmallCardLoader.findCardById(this.deckResourceCardID);
        assert resCard != null;
        SmallCard goldCard = SmallCardLoader.findCardById(this.deckGoldenCardID);
        assert goldCard != null;

        context.getSmallModel().getCommonBoard().set(2,resCard);
        context.getSmallModel().getCommonBoard().set(5,goldCard);

        context.getViewManager().displayCommonBoard(context.getSmallModel().getCommonBoard());

        if (context.getNickname().equalsIgnoreCase(this.clientNickname)) {


            List<SmallCard> myHandCards = new ArrayList<SmallCard>();
            for (Integer cardID : handCards) {
                SmallCard card = SmallCardLoader.findCardById(cardID);
                myHandCards.add(card);
            }

            context.getSmallModel().setMyHand(myHandCards);
            context.getViewManager().displayDealCardState(context.getSmallModel().getMyHand());
            context.setState(new PassiveAimCardChoiceState());
            context.getNetworkManager().send(new ChangeTurnRequestMessage(context.getNickname()));
        }
    }
}
