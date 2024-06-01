package it.polimi.ingsw.am40.data.active.flow;

import it.polimi.ingsw.am40.data.Data;

public class CreateRequestData extends Data {

    private final int numOfPlayers;

    public CreateRequestData(int numOfPlayer) {
        super("CREATE_GAME");
        this.numOfPlayers = numOfPlayer;
    }
}
