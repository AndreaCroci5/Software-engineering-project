package it.polimi.ingsw.am40.server.actions;

import it.polimi.ingsw.am40.server.actions.active.firstRound.PlayersOrderRequestAction;
import it.polimi.ingsw.am40.server.model.Game;
import it.polimi.ingsw.am40.server.model.Player;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Test class with Black-box unit tests for the PlayersOrderRequestAction class
 */

public class PlayersOrderRequestActionBlackBoxTest {


    /**
     * Method testConstructor tests the constructor of the PlayersOrderRequestAction class.
     * It checks if the constructor initializes the object correctly.
     */
    @Test
    public void testConstructor() {
        String expectedNickname = "test";
        int expectedGameID = 1;
        int expectedPlayerID = 1;

        PlayersOrderRequestAction action = new PlayersOrderRequestAction(expectedNickname, expectedGameID, expectedPlayerID);

        assertEquals(expectedNickname, action.getNickname());
        assertEquals(expectedGameID, action.getGameID());
        assertEquals(expectedPlayerID, action.getPlayerID());
    }

}
