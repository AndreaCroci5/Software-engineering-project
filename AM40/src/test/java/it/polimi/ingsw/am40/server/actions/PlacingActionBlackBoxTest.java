package it.polimi.ingsw.am40.server.actions;

import it.polimi.ingsw.am40.server.actions.active.round.PlacingAction;
import it.polimi.ingsw.am40.server.model.CardFace;
import it.polimi.ingsw.am40.server.model.Coordinates;
import it.polimi.ingsw.am40.server.model.Game;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Test class with Black-box unit tests for the PlacingAction class.
 */

public class PlacingActionBlackBoxTest {

    private Game game;
    private PlacingAction action;
    private int choice;
    private Coordinates coordsToPlace;
    private CardFace face;

    @BeforeEach
    public void setup() {
        game = new Game();
        choice = 0;
        coordsToPlace = new Coordinates(0, 0);
        face = CardFace.FRONT;
        action = new PlacingAction("test", 1, 1, choice, coordsToPlace, face);
    }


    /**
     * Method testConstructor tests the constructor of the PlacingAction class
     * by checking if the attributes are correctly set.

     */
    @Test
    public void testConstructor() {
        assertEquals("test", action.getNickname());
        assertEquals(1, action.getGameID());
        assertEquals(1, action.getPlayerID());
    }


}
