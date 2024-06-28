package it.polimi.ingsw.am40.server.actions;

import it.polimi.ingsw.am40.server.actions.active.firstRound.DealCardsRequestAction;
import it.polimi.ingsw.am40.server.model.Game;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Test class with Black-box unit tests for the DealCardsRequestAction class
 */

public class DealCardsRequestActionBlackBoxTest {


    /**
     * Method testConstructor tests the constructor of the DealCardsRequestAction class.
     * The test checks if the constructor correctly initializes the attributes of the class.
     */
    @Test
    public void testConstructor() {
        DealCardsRequestAction dealCardsRequestAction = new DealCardsRequestAction("test", 1, 1);
        assertNotNull(dealCardsRequestAction);
        assertEquals("CARDS_DEAL", dealCardsRequestAction.getDescription());
        assertEquals("test", dealCardsRequestAction.getNickname());
        assertEquals(1, dealCardsRequestAction.getGameID());
        assertEquals(1, dealCardsRequestAction.getPlayerID());
    }

}
