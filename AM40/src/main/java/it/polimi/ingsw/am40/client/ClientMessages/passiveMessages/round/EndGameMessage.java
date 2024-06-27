package it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.round;

import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.network.Client;

import java.util.List;

/**
 * This message is sent at the end game
 */
public class EndGameMessage extends Message {

    /**
     * It's the list with the names of the winners
     */
    List<String> winners;

    /**
     * Constructor for the EndGameMessage
     * @param clientNickname is the name of the active client
     * @param winners is the name of the winners
     */
    public EndGameMessage(String clientNickname, List<String> winners) {
        super("END_GAME_MESSAGE",clientNickname);
        this.winners = winners;
    }

    /**
     * It displays the end game banner and the name of the winners
     * @param context is the context of the client with his view and his network communication protocol
     */
    public void process(Client context) {
        context.getViewManager().displayEndGame(winners);
    }
}
