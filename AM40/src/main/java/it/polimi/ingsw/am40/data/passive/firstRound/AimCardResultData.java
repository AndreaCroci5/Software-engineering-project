package it.polimi.ingsw.am40.data.passive.firstRound;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.ClientMessages.passiveMessages.firstRound.AimCardResultMessage;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.Action;

@JsonTypeName("AIM_CARD_SELECTED")
public class AimCardResultData extends Data {
    //ATTRIBUTES
    /** ID of the AimCard chosen by the Player*/
    private int aimCardChosenID;

    //CONSTRUCTOR

    @JsonCreator
    public AimCardResultData(@JsonProperty("nickname") String nickname,
                             @JsonProperty("aimCardChosenID") int aimCardChosenID) {
        super("AIM_CARD_SELECTED", nickname);
        this.aimCardChosenID = aimCardChosenID;
    }

    public int getAimCardChosenID() {
        return aimCardChosenID;
    }

    public void setAimCardChosenID(int aimCardChosenID) {
        this.aimCardChosenID = aimCardChosenID;
    }

    //PUBLIC METHODS


    public Action onServer(){
        return null;
    }

    public Message onClient() {
        return new AimCardResultMessage(this.getNickname(), this.aimCardChosenID);
    }
}
