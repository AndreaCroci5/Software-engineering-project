package it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.firstRound;

import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.ClientMessages.activeMessages.flow.ChangeTurnRequestMessage;
import it.polimi.ingsw.am40.client.network.Client;
import it.polimi.ingsw.am40.client.network.States.activeStates.ReadyToRoundState;
import it.polimi.ingsw.am40.client.smallModel.SmallCardLoader;


public class AimCardResultMessage extends Message {
    /**
     * The nickname of the active client
     */
    private final String clientNickname;

    /**
     * The ID of the card chosen by the client
     */
    private final int aimCardIdChosen;


    /**
     * Constructor for AimCardResultMessage
     * Represents a message indicating that an aim card has been selected by a client
     * @param clientNickname it's the nickname of the active client
     * @param aimCardIdChosen it's the ID of the card chosen by the client
     */
    public AimCardResultMessage(String clientNickname, int aimCardIdChosen) {
        super("AIM_CARD_SELECTED");
        this.clientNickname = clientNickname;
        this.aimCardIdChosen = aimCardIdChosen;
    }

    /**
     * It updates the small model of the client with the card he chooses
     * It sets the next state of the client state machine
     * @param context is the context of the client with his view and his network communication protocol
     */
    public void process(Client context) {

        if (this.clientNickname.equalsIgnoreCase(context.getNickname())) {
            context.getSmallModel().setMyAimCard(SmallCardLoader.findCardById(this.aimCardIdChosen));
            context.setState(new ReadyToRoundState());
            context.getNetworkManager().send(new ChangeTurnRequestMessage());
        }
        else {
            context.getViewManager().showPassiveAimCardResult(this.clientNickname);
        }
    }


}
