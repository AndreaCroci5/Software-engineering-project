package it.polimi.ingsw.am40.server.model;

import it.polimi.ingsw.am40.server.model.aimStrategy.AimChecker;
import it.polimi.ingsw.am40.server.model.aimStrategy.AimCheckerDiagonalPattern;
import it.polimi.ingsw.am40.server.model.aimStrategy.AimCheckerLPattern;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;


/**
 * Test class with Black-box unit tests for the Player class
 */

public class PlayerBlackBoxTest {


    /**
     * Method tests the constructor of the Player class with a privateAim
     */

    @Test
    public void testConstructorWithAimCard(){
        Game game = new Game();
        AimChecker aimChecker = new AimCheckerLPattern();

        AimCard aimCard = new AimCard(1, 10, new ArrayList<>(), aimChecker, null);
        Player player = new Player("Giovanni", game, aimCard);

        assertEquals("Giovanni", player.getNickname());
        assertEquals(game, player.getGame());
        assertEquals(aimCard, player.getPrivateAim());

    }



    /**
     * Method test the constructor of the Player class without a privateAim
     * The privateAim should be null
     */

    @Test
    public void testConstructorWithoutAimCard(){
        Game game = new Game();
        Player player = new Player("Giovanni", game);

        assertEquals("Giovanni", player.getNickname());
        assertEquals(game, player.getGame());
        assertNull(player.getPrivateAim());
    }


    /**
     * Method test the setter method for the privateAim
     */

    @Test
    public void testSetPrivateAim(){
        Game game = new Game();
        AimChecker aimChecker = new AimCheckerDiagonalPattern();

        AimCard aimCard = new AimCard(1, 10, new ArrayList<>(), aimChecker, null);
        Player player = new Player("Giovanni", game);
        player.setPrivateAim(aimCard);

        assertEquals(aimCard, player.getPrivateAim());

    }


    /**
     *
     */

    @Test
    public void testIncreaseScore(){
        Game game = new Game();
        Player player = new Player("Giovanni", game);
        player.increaseScore(10);

        assertEquals(10, player.getScore());
        player.increaseScore(20);

        assertEquals(30, player.getScore());
    }



    /**
     * Method tests the setter method for the startingPlayer
     */

    @Test
    public void testSetStartingPlayer(){
        Game game = new Game();
        Player player = new Player("Giovanni", game);
        player.setStartingPlayer(true);

        assertTrue(player.isStartingPlayer());
        player.setStartingPlayer(false);

        assertFalse(player.isStartingPlayer());
    }


    /**
     * Method tests the setter method for the currentlyPlaying
     */

    @Test
    public void testSetCurrentlyPlaying(){
        Game game = new Game();
        Player player = new Player("Giovanni", game);
        player.setCurrentlyPlaying(true);

        assertTrue(player.isCurrentlyPlaying());
        player.setCurrentlyPlaying(false);

        assertFalse(player.isCurrentlyPlaying());
    }


    /**
     * Method tests the setter method for the online status of the player
     */

    @Test
    public void testSetIsOnline(){
        Game game = new Game();
        Player player = new Player("Giovanni", game);
        player.setOnline();

        assertTrue(player.isOnline());
        player.setOnline();

        assertTrue(player.isOnline());
    }



    /**
     * Method tests the setter method for the offline status of the player
     */

    @Test
    public void testSetIsOffline(){
        Game game = new Game();
        Player player = new Player("Giovanni", game);
        player.setOffline();

        assertFalse(player.isOnline());
        player.setOffline();

        assertFalse(player.isOnline());
    }



    /**
     * Method tests the setter method for the nickname of the player
     */

    @Test
    public void testSetNickname(){
        Game game = new Game();
        Player player = new Player("Giovanni", game);
        player.setNickname("Mario");

        assertEquals("Mario", player.getNickname());
    }



    /**
     * Method tests the setter method for the aimDone attribute of the player
     */

    @Test
    public void testSetNumOfAimDone(){
        Game game = new Game();
        Player player = new Player("Giovanni", game);
        player.setNumOfAimDone(10);

        assertEquals(10, player.getNumOfAimDone());
    }



    /**
     * Method tests the increaseNumOfAimDone method of the player
     */

    @Test
    public void testIncreaseNumOfAimDone(){
        Game game = new Game();
        Player player = new Player("Giovanni", game);
        player.increaseNumOfAimDone();

        assertEquals(1, player.getNumOfAimDone());
        player.increaseNumOfAimDone();

        assertEquals(2, player.getNumOfAimDone());
    }



    /**
     * Method tests setter method for the currentlyPlaying attribute of the player
     */

    @Test
    public void setStartingPlayerTrue(){
        Game game = new Game();
        Player player = new Player("Giovanni", game);
        player.setStartingPlayer(true);

        assertTrue(player.isStartingPlayer());
    }



    /**
     * JAVADOC TO DO
     */

    @Test
    public void setStartingPlayerFalse(){
        Game game = new Game();
        Player player = new Player("Giovanni", game);
        player.setStartingPlayer(false);

        assertFalse(player.isStartingPlayer());
    }



    /**
     * JAVADOC TO DO
     */

    @Test
    public void testSetScore(){
        Game game = new Game();
        Player player = new Player("Giovanni", game);
        player.setScore(10);

        assertEquals(10, player.getScore());
    }


    /**
     * JAVADOC TO DO
     */

    @Test
    public void getPrivateBoard(){
        Game game = new Game();
        Player player = new Player("Giovanni", game);

        assertNotNull(player.getPrivateBoard());
    }


    /**
     * JAVADOC TO DO
     */

    @Test
    public void testGetToken(){
        Game game = new Game();
        Player player = new Player("Giovanni", game);

        assertNotNull(player.getToken());
    }



    /**
     * JAVADOC TO DO
     */

    @Test
    public void testStartingPlayerTrue(){
        Game game = new Game();
        Player player = new Player("Giovanni", game);
        player.setStartingPlayer(true);

        assertTrue(player.isStartingPlayer());
    }

}
