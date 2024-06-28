package it.polimi.ingsw.am40.server.actions;

import it.polimi.ingsw.am40.server.actions.active.firstRound.AimCardRequestAction;
import it.polimi.ingsw.am40.server.model.Game;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Test class with Black-box unit tests for the AimCardRequestAction class
 */

public class AimCardRequestActionBlackBoxTest {


    /**
     *
     */
    @Test
    public void testConstructor() {
        AimCardRequestAction aimCardRequestAction = new AimCardRequestAction("test", 1, 1);
        assertNotNull(aimCardRequestAction);
        assertEquals("AIM_CARD_REQUEST", aimCardRequestAction.getDescription());
        assertEquals("test", aimCardRequestAction.getNickname());
        assertEquals(1, aimCardRequestAction.getGameID());
        assertEquals(1, aimCardRequestAction.getPlayerID());
    }

}
