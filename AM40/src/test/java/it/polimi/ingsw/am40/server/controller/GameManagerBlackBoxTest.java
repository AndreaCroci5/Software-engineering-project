package it.polimi.ingsw.am40.server.controller;

import it.polimi.ingsw.am40.server.actions.Action;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



/**
 * Test class with Black-box unit tests for the GameManager class
 */

public class GameManagerBlackBoxTest {


    /**
     * Method testConstructor tests the constructor of the GameManager class,
     * verifying that the activeGames map is empty after the creation of a new GameManager object.
     */
    @Test
    public void testConstructor() {
        GameManager gameManager = new GameManager();
        assertNotNull(gameManager);
        assertTrue(gameManager.activeGames.isEmpty());
    }


    /**
     * Method testOnEvent tests the onEvent method of the GameManager class,
     * verifying that the activeGames map contains the game after the creation of a new game.
     * The test uses a mock Action object to simulate the creation of a new game.
     */
    @Test
    public void testOnEvent() {
        GameManager gameManager = new GameManager();
        Action mockAction = new Action("dummy1", "dummy2", 1, 2) {
            @Override
            public String getDescription() {
                return "GAME_INIT";
            }

            @Override
            public int getGameID() {
                return 1;
            }
        };

        gameManager.onEvent(mockAction);
        assertTrue(gameManager.activeGames.containsKey(mockAction.getGameID()));
    }



    /**
     * Method testInitApplication tests the initApplication method of the GameManager class,
     * verifying that the method does not throw any exception when called.
     */
    //@Test
    //public void testInitApplication() {
      //  GameManager gameManager = new GameManager();
        //assertDoesNotThrow(() -> gameManager.initApplication(1234, 5678, "localhost"));
    //}
}
