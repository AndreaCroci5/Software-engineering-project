package it.polimi.ingsw.am40.data.active.flow;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.actions.active.setup.GameIDChoiceAction;

@JsonTypeName("GAME_ID_CHOICE")
public class GameIDChoiceData extends Data {

    private int gameIDChoice;

    public int getGameIDChoice() {
        return gameIDChoice;
    }

    public void setGameIDChoice(int gameIDChoice) {
        this.gameIDChoice = gameIDChoice;
    }

    @JsonCreator
    public GameIDChoiceData(@JsonProperty("nickname") String nickname,
                            @JsonProperty("gameIDChoice") int gameIDChoice) {
        super("GAME_ID_CHOICE", nickname);
        this.gameIDChoice = gameIDChoice;
    }

    @Override
    public Action onServer(){
        return new GameIDChoiceAction(this.getNickname(), this.getGameID(), this.getPlayerID(), this.gameIDChoice);
    }
}
