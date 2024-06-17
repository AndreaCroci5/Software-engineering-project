package it.polimi.ingsw.am40.client.ClientMessages.activeMessages.flow;

import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.data.active.flow.GameIDChoiceData;

public class GameIdChoiceMessage extends Message {

    private final String clientNickname;

    /**
     * It's the ID of the game the client wants to join
     */
    private final int gameIdChoice;

    /**
     * This message contains the ID of the game that the client wants to join
     * @param gameIdChoice is the ID of the game the client wants to join
     */
    public GameIdChoiceMessage(String nickname, int gameIdChoice) {
        super("GAME_ID_CHOICE",nickname);
        this.clientNickname = nickname;
        this.gameIdChoice = gameIdChoice;
    }

    /**
     * This method is used to convert the internal message of the client in
     * a data that is the object that is going through the network
     * @return the data that is going to the network
     */
    public Data messageToData() {
        return new GameIDChoiceData(this.clientNickname,this.gameIdChoice);
    }
}

