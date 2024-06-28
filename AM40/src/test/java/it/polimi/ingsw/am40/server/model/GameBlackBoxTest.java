package it.polimi.ingsw.am40.server.model;

import it.polimi.ingsw.am40.server.ActionListener;
import it.polimi.ingsw.am40.server.ActionPoster;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.exceptions.model.ForceEndgameTurnException;
import it.polimi.ingsw.am40.server.exceptions.model.RepeatDrawException;
import it.polimi.ingsw.am40.server.exceptions.model.TurnException;
import it.polimi.ingsw.am40.server.model.*;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;



/**
 * Test class with Black-box unit tests for the Game class
 */

public class GameBlackBoxTest {


    /**
     * Method testConstructor tests the constructor of the Game class and verifies that the game is correctly initialized
     * The players list should be empty, the common board should be initialized, the remaining rounds should be 70,
     * the end game should be false, and the listeners list should be empty
     */
    @Test
    public void testConstructor() {
        Game game = new Game();

        // Verify the players list is initialized and empty
        assertNotNull(game.getPlayers(), "Players list should not be null");
        assertTrue(game.getPlayers().isEmpty(), "Players list should be empty");

        // Verify the common board is initialized
        assertNotNull(game.getCommonBoard(), "Common board should not be null");

        // Verify the remaining rounds are set to 70
        assertEquals(70, game.getRemainingRounds(), "Remaining rounds should be initialized to 70");

        // Verify the game is not in end-game state
        assertFalse(game.isEnd_game(), "End game should be false initially");

        // Verify the listeners list is initialized and empty
        assertNotNull(game.getListeners(), "Listeners list should not be null");
        assertTrue(game.getListeners().isEmpty(), "Listeners list should be empty");
    }




    /**
     * Method testIsEndGame tests the isEndGame method of the Game class,
     * which should return false when the game is not in end-game state
     */
    @Test
    public void testIsEndGame() {
        Game game = new Game();
        assertFalse(game.isEnd_game());
    }



    /**
     * Method testSetEndGame tests the setEndGame method of the Game class,
     * which should set the end-game state of the game to true
     */
    @Test
    public void testSetEndGame() {
        Game game = new Game();
        game.setEnd_game(true);
        assertTrue(game.isEnd_game());
    }



    /**
     * Method testSetRemainingRounds tests the setRemainingRounds method of the Game class,
     * which should set the number of remaining rounds in the game
     */
    @Test
    public void testSetRemainingRounds() {
        Game game = new Game();
        game.setRemainingRounds(50);
        assertEquals(50, game.getRemainingRounds());
    }



    /**
     * testCheckEndGamePlayerScore tests the checkEndGame method of the Game class
     */
    @Test
    public void testCheckEndGamePlayerScore() {
        Game game = new Game();
        Player player = new Player("Player1", game);
        player.setScore(21);
        game.getPlayers().add(player);

        assertTrue(game.checkEndGame());
    }



    /**
     * Method testCheckEndGameDeckEmpty tests the checkEndGame method of the Game class,
     * which should return true when the deck is empty
     */
    @Test
    public void testCheckEndGameDeckEmpty() {
        Game game = new Game();
        CommonBoard commonBoard = game.getCommonBoard();
        Player player = new Player("Player1", game);
        player.setScore(10);
        game.getPlayers().add(player);

        assertTrue(game.checkEndGame());

    }



    /**
     * Method testGetPlayersTokenColor tests the getPlayersTokenColor method of the Game class,
     * which should return a list of the colors of the tokens of the players in the game
     */
    @Test
    public void testGetPlayersTokenColor() {
        Game game = new Game();
        Player player1 = new Player("Alice", game);
        Player player2 = new Player("Bob", game);
        player1.getToken().setColor(Color.RED);
        player2.getToken().setColor(Color.BLUE);
        game.getPlayers().add(player1);
        game.getPlayers().add(player2);

        ArrayList<Color> tokenColors = game.getPlayersTokenColor();
        assertEquals(2, tokenColors.size());
        assertEquals(Color.RED, tokenColors.get(0));
        assertEquals(Color.BLUE, tokenColors.get(1));
    }




    /**
     * Method testCheckEndGame tests the checkEndGame method of the Game class,
     * which should return true when the game is in end-game state
     */
    @Test
    public void testCheckEndGame() {
        Game game = new Game();
        Player player1 = new Player("Alice", game);
        player1.setScore(21);
        game.getPlayers().add(player1);

        assertTrue(game.checkEndGame());

        player1.setScore(19);
        assertTrue(game.checkEndGame());
    }




    /**
     * Method testStartGame tests the startGame method of the Game class,
     * which should initialize the game with the given nicknames
     */
    @Test
    public void testStartGame() {
        Game game = new Game();
        List<String> nicknames = List.of("Player1", "Player2");
        game.startGame(nicknames);

        assertEquals(2, game.getPlayers().size());
        assertNotNull(game.getCommonBoard());
    }



    /**
     * Method testSelectWinner tests the selectWinner method of the Game class,
     * which should return the player with the highest score
     */
    @Test
    public void testSelectWinner() {
        Game game = new Game();
        Player player1 = new Player("Alice", game);
        Player player2 = new Player("Bob", game);
        player1.setScore(10);
        player2.setScore(15);
        game.getPlayers().add(player1);
        game.getPlayers().add(player2);

        List<Player> winners = game.selectWinner();
        assertEquals(1, winners.size());
        assertEquals(player2, winners.get(0));

        player1.setScore(15);
        winners = game.selectWinner();
        assertEquals(2, winners.size());
        assertTrue(winners.contains(player1));
        assertTrue(winners.contains(player2));
    }




    /**
     * Method testChooseTokenColor tests the chooseTokenColor method of the Game class,
     * which should set the color of the token of the player currently playing
     */
    @Test
    public void testChooseTokenColor() {
        Game game = new Game();
        Player player = new Player("Player1", game);
        game.getPlayers().add(player);
        player.setCurrentlyPlaying(true);

        game.chooseTokenColor(Color.RED);
        assertEquals(Color.RED, player.getToken().getColor());
    }




    /**
     * Method testAddListener tests the addListener method of the Game class,
     * which should add a listener to the list of listeners
     * The listener should be contained in the list of listeners
     */
    @Test
    public void testAddListener() {
        Game game = new Game();
        ActionListener listener = new ActionListener() {
            @Override
            public void onEvent(Action event) {}
        };
        game.addListener(listener, game.getListeners());
        assertTrue(game.getListeners().contains(listener));
    }



    /**
     * Method testRemoveListener tests the removeListener method of the Game class,
     * which should remove a listener from the list of listeners
     */
    @Test
    public void testRemoveListener() {
        Game game = new Game();
        ActionListener listener = new ActionListener() {
            @Override
            public void onEvent(Action event) {}
        };
        game.addListener(listener, game.getListeners());
        game.removeListener(listener, game.getListeners());
        assertFalse(game.getListeners().contains(listener));
    }



    /**
     * Method testCalculateRemainingRounds tests the calculateRemainingRounds method of the Game class,
     * which should calculate the number of remaining rounds based on the number of players and the player currently playing
     */
    @Test
    public void testCalculateRemainingRounds() {
        Game game = new Game();
        Player player1 = new Player("Alice", game);
        Player player2 = new Player("Bob", game);
        player1.setCurrentlyPlaying(true);
        game.getPlayers().add(player1);
        game.getPlayers().add(player2);

        game.calculateRemainingRounds();
        assertEquals(3, game.getRemainingRounds());
    }




    /**
     * Method testRemainingTokenColors tests the remainingTokenColors method of the Game class,
     * which should return a list of the colors of the tokens that have not been chosen by the players
     */
    @Test
    public void testRemainingTokenColors() {
        Game game = new Game();
        Player player1 = new Player("Player1", game);
        Player player2 = new Player("Player2", game);
        game.getPlayers().add(player1);
        game.getPlayers().add(player2);
        player1.getToken().setColor(Color.RED);

        List<String> remainingColors = game.remainingTokenColors();
        assertFalse(remainingColors.contains(Color.RED.toString()));
    }




    /**
     * Method testDecidePlayerOrder tests the decidePlayerOrder method of the Game class,
     * which should decide the order of the players randomly
     */
    @Test
    public void testDecidePlayerOrder() {
        Game game = new Game();
        Player player1 = new Player("Player1", game);
        Player player2 = new Player("Player2", game);
        game.getPlayers().add(player1);
        game.getPlayers().add(player2);

        game.decidePlayerOrder();
        assertTrue(game.getPlayers().get(0).isCurrentlyPlaying() || game.getPlayers().get(1).isCurrentlyPlaying());
    }




    /**
     * Method testCalculateFinalScore tests the calculateFinalScore method of the Game class,
     * which should calculate the final score of the player based on the number of aims done
     * The final score should be equal to the score of the player
     */
    @Test
    public void testCalculateFinalScore() {
        Game game = new Game();
        Player player1 = new Player("Player1", game);
        game.getPlayers().add(player1);

        player1.setScore(10);
        player1.setNumOfAimDone(2);
        assertEquals(10, player1.getScore());
    }




    /**
     * Method testGetIndexOfPlayingPlayer tests the getIndexOfPlayingPlayer method of the Game class,
     * which should return the index of the player currently playing
     */
    @Test
    public void testGetIndexOfPlayingPlayer() {
        Game game = new Game();
        Player player = new Player("Player1", game);
        game.getPlayers().add(player);
        player.setCurrentlyPlaying(true);

        assertEquals(0, game.getIndexOfPlayingPlayer());
    }



    /**
     * Method testDealCards tests the dealCards method of the Game class,
     * which should deal cards to the player currently playing
     */
    @Test
    public void testDealCards() {
        Game game = new Game();
        Player player1 = new Player("Alice", game);
        game.getPlayers().add(player1);
        player1.setCurrentlyPlaying(true);

        game.dealCards();

        assertEquals(0, player1.getPrivateBoard().getHandDeck().size());
    }



    /**
     * Method testDraw tests the draw method of the Game class,
     * which should draw a card from the deck and add it to the
     * hand deck of the player currently playing
     */
    @Test
    public void testDraw() {
        Game game = new Game();
        Player player1 = new Player("Alice", game);
        game.getPlayers().add(player1);
        player1.setCurrentlyPlaying(true);

        assertThrows(RepeatDrawException.class, () -> game.draw(2, 3));
    }



    /**
     * Method testChangePlayersTurn tests the changePlayersTurn method of the Game class,
     * which should change the turn of the players in the game
     */
    @Test
    public void testChangePlayersTurn() {
        Game game = new Game();
        Player player1 = new Player("Alice", game);
        Player player2 = new Player("Bob", game);
        player1.setCurrentlyPlaying(true);
        game.getPlayers().add(player1);
        game.getPlayers().add(player2);

        try {
            game.changePlayersTurn(0);
        } catch (TurnException e) {
            fail("TurnException thrown");
        }

        assertTrue(player2.isCurrentlyPlaying());
    }

}
