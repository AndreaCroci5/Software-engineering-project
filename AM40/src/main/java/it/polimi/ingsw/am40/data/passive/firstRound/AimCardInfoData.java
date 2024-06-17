package it.polimi.ingsw.am40.data.passive.firstRound;

import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.Action;

@JsonTypeName("AIM_CARD_INFO")
public class AimCardInfoData extends Data {
    //ATTRIBUTES
    private int aimID1;
    private int aimID2;

    //CONSTRUCTOR

    //Logic constructor for subclasses
    public AimCardInfoData(String nickname, int aimID1, int aimID2) {
        super("AIM_CARD_INFO", nickname);
        this.aimID1 = aimID1;
        this.aimID2 = aimID2;
    }
    //Json constructor
    public AimCardInfoData(){

    }

    //PUBLIC METHODS


    public Action onServer(){
        return null;
    }

    public Message onClient() {
        return null;
    }
}
