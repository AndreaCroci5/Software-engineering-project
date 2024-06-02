package it.polimi.ingsw.am40.data.passive.firstRound;

import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.Action;

@JsonTypeName("AIM_CARD_SELECTED")
public class AimCardResultData extends Data {
    //ATTRIBUTES
    /** ID of the AimCard chosen by the Player*/
    private int aimCardChosenID;

    //CONSTRUCTOR

    //Logic constructor for subclasses
    public AimCardResultData(int aimCardChosenID) {
        super("AIM_CARD_SELECTED");
        this.aimCardChosenID = aimCardChosenID;
    }
    //Json constructor
    public AimCardResultData(){

    }

    //PUBLIC METHODS


    public Action onServer(){
        return null;
    }

    public Message onClient() {
        return null;
    }
}
