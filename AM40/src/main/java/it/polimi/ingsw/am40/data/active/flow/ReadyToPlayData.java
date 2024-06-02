package it.polimi.ingsw.am40.data.active.flow;

import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.data.Data;

@JsonTypeName("READY_TO_PLAY")
public class ReadyToPlayData extends Data {

    public ReadyToPlayData() {
        super("READY_TO_PLAY");
    }
}
