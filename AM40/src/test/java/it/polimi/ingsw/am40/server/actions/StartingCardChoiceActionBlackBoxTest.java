package it.polimi.ingsw.am40.server.actions;

import it.polimi.ingsw.am40.server.actions.active.firstRound.StartingCardChoiceAction;
import it.polimi.ingsw.am40.server.model.CardFace;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Test class with Black-box unit tests for the StartingCardChoiceAction class.
 */

public class StartingCardChoiceActionBlackBoxTest {


    /**
     * Method testConstructor checks that the constructor of the class works properly.
     * It checks that the nickname, gameID, playerID and cardFace are correctly set.
     */
    @Test
    public void testConstructor() {
        String expectedNickname = "test";
        int expectedGameID = 1;
        int expectedPlayerID = 1;
        CardFace expectedCardFace = CardFace.FRONT;

        StartingCardChoiceAction action = new StartingCardChoiceAction(expectedNickname, expectedGameID, expectedPlayerID, expectedCardFace);

        assertEquals(expectedNickname, action.getNickname());
        assertEquals(expectedGameID, action.getGameID());
        assertEquals(expectedPlayerID, action.getPlayerID());
    }
}
