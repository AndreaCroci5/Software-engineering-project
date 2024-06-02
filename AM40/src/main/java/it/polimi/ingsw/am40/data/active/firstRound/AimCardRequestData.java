package it.polimi.ingsw.am40.data.active.firstRound;

import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.data.Data;

@JsonTypeName("AIM_CARD_REQUEST")
public class AimCardRequestData extends Data {

    public AimCardRequestData () {
        super("AIM_CARD_REQUEST");
    }
}
