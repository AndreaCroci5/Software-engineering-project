package it.polimi.ingsw.am40.data.passive.firstRound;

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

    //Logic constructor for subclasses
    public StartingCardInfoData(String nickname, int startingCardID) {
        super("AIM_CARD_INFO", nickname);
        this.startingCardID = startingCardID;
    }
    //Json constructor
    public StartingCardInfoData(){

    }

    //PUBLIC METHODS


    public Action onServer(){
        return null;
    }

    public Message onClient() {
        return new StartingCardInfoMessage(this.getNickname(), this.startingCardID);
    }
}
