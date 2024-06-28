package it.polimi.ingsw.am40.server.actions;

import it.polimi.ingsw.am40.server.actions.active.firstRound.AimCardChoiceAction;
import it.polimi.ingsw.am40.server.model.Game;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Test class with Black-box unit tests for the AimCardChoiceAction class.
 */

public class AimCardChoiceActionBlackBoxTest {


    /**
     * Method testConstructor tests the constructor of the AimCardChoiceAction class.
     * The test asserts that a new AimCardChoiceAction object is not null and that its attributes are correctly initialized.
     */
    @Test
    public void testConstructor() {
        AimCardChoiceAction aimCardChoiceAction = new AimCardChoiceAction("test", 1, 1, 1);
        assertNotNull(aimCardChoiceAction);
        assertEquals("AIM_CARD_SELECTION", aimCardChoiceAction.getDescription());
        assertEquals("test", aimCardChoiceAction.getNickname());
        assertEquals(1, aimCardChoiceAction.getGameID());
        assertEquals(1, aimCardChoiceAction.getPlayerID());
    }

}
