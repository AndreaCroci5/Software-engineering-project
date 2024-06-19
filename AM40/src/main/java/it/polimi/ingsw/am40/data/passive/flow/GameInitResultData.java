package it.polimi.ingsw.am40.data.passive.flow;

import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.data.Data;

import java.util.ArrayList;
import java.util.Map;

@JsonTypeName("GAME_INIT_RESULT")
public class GameInitResultData extends Data {
    //ATTRIBUTES
    private ArrayList<String> nicknames;

    /**
     * Reference to a Map containing as Keys the name of the Type of Cards that the Values Represent: "RESOURCE", "GOLDEN", "AIM".
     * The values represent an ArrayList of Integers, whose element represent the card ID and the index the position on the CommonBoard:
     * indexes: (0) plate first card, (1) plate second card, (2) deck card
     */
    private Map<String, ArrayList<Integer>> commonboard;

    public GameInitResultData(String nickname, ArrayList<String> nicknames, Map<String, ArrayList<Integer>> commonboard) {
        super("GAME_INIT_RESULT", nickname);
        this.nicknames = nicknames;
        this.commonboard = commonboard;
    }

    public GameInitResultData() {

    }
}
