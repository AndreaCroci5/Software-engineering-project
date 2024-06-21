package it.polimi.ingsw.am40.data.passive.firstRound;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.firstRound.StartingCardInfoMessage;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.Action;

@JsonTypeName("STARTING_INFO")
public class StartingCardInfoData extends Data {
    //ATTRIBUTES
    private int startingCardID;

    //CONSTRUCTOR

    @JsonCreator
    public StartingCardInfoData(@JsonProperty("nickname") String nickname,
                                @JsonProperty("startingCardID") int startingCardID) {
        super("STARTING_INFO", nickname);
        this.startingCardID = startingCardID;
    }

    public int getStartingCardID() {
        return startingCardID;
    }

    public void setStartingCardID(int startingCardID) {
        this.startingCardID = startingCardID;
    }

    //PUBLIC METHODS


    public Action onServer(){
        return null;
    }

    public Message onClient() {
        return new StartingCardInfoMessage(this.getNickname(), this.startingCardID);
    }
}
