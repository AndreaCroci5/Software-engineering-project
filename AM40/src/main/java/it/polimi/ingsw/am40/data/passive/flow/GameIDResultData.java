package it.polimi.ingsw.am40.data.passive.flow;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.flow.GameIdResponseMessage;
import it.polimi.ingsw.am40.data.Data;

@JsonTypeName("GAME_ID_RESULT")
public class GameIDResultData extends Data {
    //ATTRIBUTES
    private int partyID;
    private int currentNumOfPlayers;
    private int totalNumOfPlayers;

    public int getPartyID() {
        return partyID;
    }

    public void setPartyID(int partyID) {
        this.partyID = partyID;
    }

    public int getCurrentNumOfPlayers() {
        return currentNumOfPlayers;
    }

    public void setCurrentNumOfPlayers(int currentNumOfPlayers) {
        this.currentNumOfPlayers = currentNumOfPlayers;
    }

    public int getTotalNumOfPlayers() {
        return totalNumOfPlayers;
    }

    public void setTotalNumOfPlayers(int totalNumOfPlayers) {
        this.totalNumOfPlayers = totalNumOfPlayers;
    }

    @JsonCreator
    public GameIDResultData(@JsonProperty("nickname") String nickname,
                            @JsonProperty("game_id") int gameId,
                            @JsonProperty("partyID") int partyID,
                            @JsonProperty("currentNumOfPlayers") int currentNumOfPlayers,
                            @JsonProperty("totalNumOfPlayers") int totalNumOfPlayers) {
        super("GAME_ID_RESULT", nickname);
        this.setGameID(gameId);
        this.partyID = partyID;
        this.currentNumOfPlayers = currentNumOfPlayers;
        this.totalNumOfPlayers = totalNumOfPlayers;
    }

    public Message onClient() {
        return new GameIdResponseMessage(getNickname(), partyID, currentNumOfPlayers, totalNumOfPlayers);
    }
}
