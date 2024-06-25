package it.polimi.ingsw.am40.data.passive.round;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.round.EndGameMessage;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.Action;

import java.util.List;

@JsonTypeName("ENDGAME")
public class EndGameData extends Data {
    //ATTRIBUTES
    /** Names of the Players that won the Game in case of a tie. If the winner is only one, the List contains only one Player*/
    private List<String> winners;

    //CONSTRUCTOR

    @JsonCreator
    public EndGameData(@JsonProperty("nickname") String nickname,
                       @JsonProperty("winners") List<String> winners) {
        super("ENDGAME", nickname);
        this.winners = winners;
    }

    public List<String> getWinners() {
        return winners;
    }

    public void setWinners(List<String> winners) {
        this.winners = winners;
    }

    //PUBLIC METHODS


    public Action onServer(){
        return null;
    }

    public Message onClient() {
        return new EndGameMessage(this.getNickname(), this.winners);
    }
}
