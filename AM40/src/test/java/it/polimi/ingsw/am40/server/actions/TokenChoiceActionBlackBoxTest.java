package it.polimi.ingsw.am40.server.actions;

import it.polimi.ingsw.am40.server.actions.active.firstRound.TokenChoiceAction;
import it.polimi.ingsw.am40.server.model.Color;
import it.polimi.ingsw.am40.server.model.Game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Test class with black-box unit tests for TokenChoiceAction class
 */

public class TokenChoiceActionBlackBoxTest {


    /**
     * Method testConstructor tests the constructor of the TokenChoiceAction class.
     * It checks that the constructor initializes the fields correctly.
     */
    @Test
    public void testConstructor() {
        String expectedNickname = "test";
        int expectedGameID = 1;
        int expectedPlayerID = 1;
        Color expectedColor = Color.RED;

        TokenChoiceAction action = new TokenChoiceAction(expectedNickname, expectedGameID, expectedPlayerID, expectedColor);

        assertEquals(expectedNickname, action.getNickname());
        assertEquals(expectedGameID, action.getGameID());
        assertEquals(expectedPlayerID, action.getPlayerID());
    }

}
