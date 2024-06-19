package it.polimi.ingsw.am40.data.passive.flow;

import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.data.Data;

@JsonTypeName("NO_ACTIVE_PARTIES")
public class NoActivePartiesData extends Data {
    public NoActivePartiesData(String nickname) {
        super("NO_ACTIVE_PARTIES", nickname);
    }

    public NoActivePartiesData() {
    }
}
