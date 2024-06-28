package it.polimi.ingsw.am40.server.actions;

import it.polimi.ingsw.am40.server.actions.active.firstRound.StartingCardChoiceAction;
import it.polimi.ingsw.am40.server.model.CardFace;
import it.polimi.ingsw.am40.server.model.Game;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Test class with black-box unit tests for StartingCardRequestAction class
 */

public class StartingCardRequestActionBlackBoxTest {


    /**
     * Method testConstructor tests the constructor of the class.
     * It checks that the constructor initializes the attributes correctly.
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
