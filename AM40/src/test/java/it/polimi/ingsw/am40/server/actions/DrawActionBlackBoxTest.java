package it.polimi.ingsw.am40.server.actions;

import it.polimi.ingsw.am40.server.actions.active.flow.ChangeTurnRequestAction;
import it.polimi.ingsw.am40.server.exceptions.model.TurnException;
import it.polimi.ingsw.am40.server.model.Game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;


/**
 *
 */

public class DrawActionBlackBoxTest {

    private Game game;
    private ChangeTurnRequestAction action;

    @BeforeEach
    public void setup() {
        game = new Game();
        action = new ChangeTurnRequestAction("test", 1, 1);
    }



    /**
     * Method testConstructor tests the constructor of the class.
     * It checks that the constructor sets the attributes correctly.
     */
    @Test
    public void testConstructor() {
        String expectedNickname = "test";
        int expectedGameID = 1;
        int expectedPlayerID = 1;

        ChangeTurnRequestAction action = new ChangeTurnRequestAction(expectedNickname, expectedGameID, expectedPlayerID);

        assertEquals(expectedNickname, action.getNickname());
        assertEquals(expectedGameID, action.getGameID());
        assertEquals(expectedPlayerID, action.getPlayerID());
    }


    /**
     * Method testDoActionLastRound tests the doAction method when the game is in the last round.
     * It checks that the game is set to end_game.
     */
    @Test
    public void testDoActionLastRound() {
        game.setRemainingRounds(0);

        action.doAction(game);

        assertTrue(game.isEnd_game());
    }



    /**
     * Method testDoActionNormalTurnChange tests the doAction method when the game is not in the last round.
     */
    @Test
    public void testDoActionNormalTurnChange() {
        game.setEnd_game(false);
        game.setRemainingRounds(1);

        action.doAction(game);

        assertEquals(0, game.getIndexOfPlayingPlayer());
    }



    /**
     * Method testDoActionTurnException tests the doAction method when the game is in the last round.
     * @throws TurnException
     */
    @Test
    public void testDoActionTurnException() throws TurnException {

        game.setEnd_game(false);
        game.setRemainingRounds(1);

        action.doAction(game);
    }
}
