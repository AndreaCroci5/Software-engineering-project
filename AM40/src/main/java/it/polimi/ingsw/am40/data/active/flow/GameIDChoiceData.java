package it.polimi.ingsw.am40.data.active.flow;

import it.polimi.ingsw.am40.data.Data;

public class GameIDChoiceData extends Data {

    private int gameIDChoice;

    public GameIDChoiceData(int gameIDChoice) {
        super("GAME_ID_CHOICE");
        this.gameIDChoice = gameIDChoice;
    }
}
