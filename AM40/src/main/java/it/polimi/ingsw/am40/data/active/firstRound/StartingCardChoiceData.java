package it.polimi.ingsw.am40.data.active.firstRound;

import it.polimi.ingsw.am40.data.Data;

public class StartingCardChoiceData extends Data {

    private final String face;

    public StartingCardChoiceData(String face) {
        super("STARTING_CARD_CHOICE");
        this.face = face;
    }
}
