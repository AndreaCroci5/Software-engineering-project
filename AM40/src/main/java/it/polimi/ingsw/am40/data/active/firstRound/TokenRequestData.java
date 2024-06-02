package it.polimi.ingsw.am40.data.active.firstRound;

import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.data.Data;

@JsonTypeName("TOKEN_REQUEST")
public class TokenRequestData extends Data {

    public TokenRequestData() {
        super("TOKEN_REQUEST");
    }
}
