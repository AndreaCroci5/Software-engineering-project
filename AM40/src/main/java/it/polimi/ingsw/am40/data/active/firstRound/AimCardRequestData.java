package it.polimi.ingsw.am40.data.active.firstRound;

import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.actions.active.firstRound.AimCardRequestAction;

@JsonTypeName("AIM_CARD_REQUEST")
public class AimCardRequestData extends Data {

    //CONSTRUCTOR
    //Logic constructor for subclasses
    public AimCardRequestData (String nickname) {
        super("AIM_CARD_REQUEST", nickname);
    }

    //Json constructor
    public AimCardRequestData(){

    }
    //PUBLIC METHODS

    public Action onServer() {
        return new AimCardRequestAction(this.getGameID(), this.getPlayerID());
    }
}
