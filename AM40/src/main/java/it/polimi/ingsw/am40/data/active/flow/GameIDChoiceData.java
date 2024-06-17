package it.polimi.ingsw.am40.data.active.flow;

import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.data.Data;

@JsonTypeName("GAME_ID_CHOICE")
public class GameIDChoiceData extends Data {

    private int gameIDChoice;

    public GameIDChoiceData(String nickname, int gameIDChoice) {
        super("GAME_ID_CHOICE", nickname);
        this.gameIDChoice = gameIDChoice;
    }

    public GameIDChoiceData(){

    }
}
