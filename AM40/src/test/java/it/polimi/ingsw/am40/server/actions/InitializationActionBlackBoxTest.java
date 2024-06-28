package it.polimi.ingsw.am40.server.actions;

import it.polimi.ingsw.am40.server.actions.active.firstRound.InitializationAction;
import it.polimi.ingsw.am40.server.model.Game;
import java.util.ArrayList;
import it.polimi.ingsw.am40.server.network.virtual_view.VVServer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Test class with Black-box unit tests for the InitializationAction class
 */

public class InitializationActionBlackBoxTest {


    /**
     * Method testConstructor tests the constructor of the InitializationAction class.
     * It checks if the constructor initializes the object correctly.
     */
    @Test
    public void testConstructor() {
        ArrayList<String> players = new ArrayList<>();
        players.add("test1");
        players.add("test2");
        VVServer mockServer = new VVServer();
        InitializationAction initializationAction = new InitializationAction("test", 1, 1, players, mockServer);
        assertNotNull(initializationAction);

    }

}
