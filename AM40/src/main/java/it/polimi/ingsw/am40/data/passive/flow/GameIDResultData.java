package it.polimi.ingsw.am40.data.passive.flow;

import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.data.Data;

@JsonTypeName("GAME_ID_RESULT")
public class GameIDResultData extends Data {
    //ATTRIBUTES
    private int partyID;
    private int currentNumOfPlayers;
    private int totalNumOfPlayers;
    public GameIDResultData(String nickname, int partyID, int currentNumOfPlayers, int totalNumOfPlayers) {
        super("GAME_ID_RESULT", nickname);
        this.partyID = partyID;
        this.currentNumOfPlayers = currentNumOfPlayers;
        this.totalNumOfPlayers = totalNumOfPlayers;
    }

    public GameIDResultData() {
    }
}
