package it.polimi.ingsw.am40.data.passive.firstRound;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.flow.DecidePlayerOrderResponse;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.Action;

import java.util.ArrayList;

@JsonTypeName("PLAYERS_ORDER_INFO")
public class PlayersOrderInfoData extends Data {
    //ATTRIBUTES
    private ArrayList<String> players;

    //CONSTRUCTOR

    @JsonCreator
    public PlayersOrderInfoData(@JsonProperty("nickname") String nickname,
                                @JsonProperty("players") ArrayList<String> players) {
        super("PLAYERS_ORDER_INFO", nickname);
        this.players = players;
    }

    public ArrayList<String> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<String> players) {
        this.players = players;
    }

    //PUBLIC METHODS

    public Action onServer(){
        return null;
    }

    public Message onClient() {
        return new DecidePlayerOrderResponse(this.getNickname(), this.players);
    }
}
