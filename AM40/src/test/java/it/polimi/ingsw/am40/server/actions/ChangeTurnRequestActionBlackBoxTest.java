package it.polimi.ingsw.am40.server.actions;

import it.polimi.ingsw.am40.server.actions.active.flow.ChangeTurnRequestAction;
import it.polimi.ingsw.am40.server.model.Game;
import it.polimi.ingsw.am40.server.model.Player;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Test class with Black-box unit tests for the ChangeTurnRequestAction class.
 */

public class ChangeTurnRequestActionBlackBoxTest {


    /**
     * Method testConstructor tests the constructor of the ChangeTurnRequestAction class.
     * It verifies that the constructor sets the nickname, gameID, and playerID correctly.
     */
    @Test
    public void testConstructor() {
        String expectedNickname = "test";
        int expectedGameID = 1;
        int expectedPlayerID = 1;

        ChangeTurnRequestAction action = new ChangeTurnRequestAction(expectedNickname, expectedGameID, expectedPlayerID);

        // Verify
        assertEquals(expectedNickname, action.getNickname());
        assertEquals(expectedGameID, action.getGameID());
        assertEquals(expectedPlayerID, action.getPlayerID());
    }



    /**
     * Method testDoActionEndGame tests the doAction method of the ChangeTurnRequestAction class
     * when the game is ended.
     */
    @Test
    public void testDoActionEndGame() {
        Game game = new Game();
        ChangeTurnRequestAction action = new ChangeTurnRequestAction("test", 1, 1);

        game.isEnd_game();

        action.doAction(game);
        assertTrue(game.isEnd_game());
    }

}
