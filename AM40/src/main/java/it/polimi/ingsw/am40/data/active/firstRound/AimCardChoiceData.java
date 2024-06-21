package it.polimi.ingsw.am40.data.active.firstRound;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.active.firstRound.AimCardChoiceAction;

/**
 * This class contains the information that will be carried by being sent on the network as a TCP message.
 * This class is also the bridge to the AimCardChoiceAction on Server and AimCardMessage on the Client
 */
@JsonTypeName("AIM_CARD_SELECTION")
public class AimCardChoiceData extends Data {
    //ATTRIBUTES
    /** This attribute represents one the AimCard chosen by the Client: (0) the first, (1) the second*/
    private int aimCardChosenID;

    //CONSTRUCTOR

    @JsonCreator
    public AimCardChoiceData(@JsonProperty("nickname") String nickname,
                             @JsonProperty("aimCardChosenID") int aimCardChosenID) {
        super("AIM_CARD_SELECTION", nickname);
        this.aimCardChosenID = aimCardChosenID;
    }

    public int getAimCardChosenID() {
        return aimCardChosenID;
    }

    public void setAimCardChosenID(int aimCardChosenID) {
        this.aimCardChosenID = aimCardChosenID;
    }

    //PUBLIC METHODS

    /**
     * Override of the method onServer that returns the related AimCardChoiceAction on the Server
     * @return an AimCardChoiceAction
     */
    public Action onServer(){
        return new AimCardChoiceAction(this.getNickname(),this.getGameID(), this.getPlayerID(), this.aimCardChosenID);
    }

    public Message onClient() {
        return null;
    }
}
