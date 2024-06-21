package it.polimi.ingsw.am40.data.passive.firstRound;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.firstRound.AimCardInfoMessage;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.Action;

import java.util.Arrays;
import java.util.List;

@JsonTypeName("AIM_CARD_INFO")
public class AimCardInfoData extends Data {
    //ATTRIBUTES
    private int aimID1;
    private int aimID2;

    //CONSTRUCTOR

    @JsonCreator
    public AimCardInfoData(@JsonProperty("nickname") String nickname,
                           @JsonProperty("aimID1") int aimID1,
                           @JsonProperty("aimID2") int aimID2) {
        super("AIM_CARD_INFO", nickname);
        this.aimID1 = aimID1;
        this.aimID2 = aimID2;
    }

    public int getAimID1() {
        return aimID1;
    }

    public void setAimID1(int aimID1) {
        this.aimID1 = aimID1;
    }

    public int getAimID2() {
        return aimID2;
    }

    public void setAimID2(int aimID2) {
        this.aimID2 = aimID2;
    }

    //PUBLIC METHODS


    public Action onServer(){
        return null;
    }

    public Message onClient() {
        return new AimCardInfoMessage(getNickname(), Arrays.asList(aimID1, aimID2));
    }
}

