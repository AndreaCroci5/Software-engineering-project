package it.polimi.ingsw.am40.model;

import it.polimi.ingsw.am40.model.aimStrategy.AimCheckerDiagonalPattern;
import it.polimi.ingsw.am40.model.aimStrategy.AimCheckerLPattern;
import it.polimi.ingsw.am40.model.scoreStrategy.CoverageScoreType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains tests for the Game class regarding the emd game part
 */
public class EndGameTest {


    /**
     * This test tests the checkEndGame method
     * It asserts true if a players has more than 20 points
     * or one of the decks is empty
     * It asserts false otherwise
     */
    @Test
    public void testCheckEndGame() {
        Game game = new Game();
        AimCard card = new AimCard(1, 2, new ArrayList<>(),new AimCheckerLPattern(),"x");
        GoldResourceCard card2 = new GoldResourceCard(2,CardElements.ANIMAL,new ArrayList<CardElements>(),1,new ArrayList<CardElements>(),new CoverageScoreType());
        Player player1 = new Player("Simo",game,card);
        Player player2 = new Player("Andre",game,card);
        Player player3 = new Player("Nico",game,card);
        Player player4 = new Player("Gio",game,card);
        game.getPlayers().add(player1);
        game.getPlayers().add(player2);
        game.getPlayers().add(player3);
        game.getPlayers().add(player4);

        // decks empty and players all 0 points
        assertTrue(game.checkEndGame());

        game.getCommonBoard().getGoldenResourceDeck().appendToBottom(card2);

        // golden deck non empty
        assertFalse(game.checkEndGame());

        game.getCommonBoard().getGoldenResourceDeck().pickFromTop();

        player1.setScore(10);
        player2.setScore(20);
        player3.setScore(18);
        player4.setScore(12);

        // 1 player over 20
        assertTrue(game.checkEndGame());

        // 4 players over 20
        player1.setScore(28);
        player3.setScore(24);
        player4.setScore(182);
        assertTrue(game.checkEndGame());
    }

    /**
     * This test tests the number of remaining rounds
     * based on how many players are playing and which player is playing
     */
    @Test
    public void testCalculateRemainingRounds() {
        Game game = new Game();
        AimCard card = new AimCard(1, 2, new ArrayList<>(),new AimCheckerLPattern(),"x");
        Player player1 = new Player("Simo",game,card);
        Player player2 = new Player("Andre",game,card);
        Player player3 = new Player("Nico",game,card);
        Player player4 = new Player("Gio",game,card);

        // test with 0 players
        game.calculateRemainingRounds();
        assertEquals(0, game.getRemainingRounds());

        game.getPlayers().add(player1);
        game.getPlayers().add(player2);

        // test with 2 players and the first is playing
        game.getPlayers().getFirst().setCurrentlyPlaying(true);
        game.calculateRemainingRounds();
        assertEquals(3, game.getRemainingRounds());

        // test with 2 players and the second is playing
        game.getPlayers().getFirst().setCurrentlyPlaying(false);
        game.getPlayers().get(1).setCurrentlyPlaying(true);
        game.calculateRemainingRounds();
        assertEquals(2, game.getRemainingRounds());

        game.getPlayers().add(player3);
        game.getPlayers().add(player4);

        // test with 4 players and the first is playing
        game.getPlayers().get(1).setCurrentlyPlaying(false);
        game.getPlayers().getFirst().setCurrentlyPlaying(true);
        game.calculateRemainingRounds();
        assertEquals(7,game.getRemainingRounds());

        // test with 4 players and the second is playing
        game.getPlayers().getFirst().setCurrentlyPlaying(false);
        game.getPlayers().get(1).setCurrentlyPlaying(true);
        game.calculateRemainingRounds();
        assertEquals(6,game.getRemainingRounds());

        // test with 4 players and the third is playing
        game.getPlayers().get(1).setCurrentlyPlaying(false);
        game.getPlayers().get(2).setCurrentlyPlaying(true);
        game.calculateRemainingRounds();
        assertEquals(5,game.getRemainingRounds());

        // test with 4 players and the fourth is playing
        game.getPlayers().get(2).setCurrentlyPlaying(false);
        game.getPlayers().getLast().setCurrentlyPlaying(true);
        game.calculateRemainingRounds();
        assertEquals(4,game.getRemainingRounds());

        game.getPlayers().getLast().setCurrentlyPlaying(false);

    }

    @Test
    public void testSelectWinner() {
        Game game = new Game();
        AimCard card = new AimCard(1, 2, new ArrayList<>(),new AimCheckerLPattern(),"x");
        Player player1 = new Player("Simo",game,card);
        Player player2 = new Player("Andre",game,card);
        Player player3 = new Player("Nico",game,card);
        Player player4 = new Player("Gio",game,card);

        game.getPlayers().add(player1);
        game.getPlayers().add(player2);
        game.getPlayers().add(player3);
        game.getPlayers().add(player4);

        List<Player> winners = game.selectWinner();
        assertEquals(4, winners.size());
        assertTrue(winners.contains(player1));
        assertTrue(winners.contains(player2));
        assertTrue(winners.contains(player3));
        assertTrue(winners.contains(player4));

        game.getPlayers().getFirst().setScore(12);
        List<Player> winners2 = game.selectWinner();
        assertEquals(1, winners2.size());
        assertTrue(winners2.contains(player1));

        game.getPlayers().get(2).setScore(14);
        game.getPlayers().get(3).setScore(14);
        List<Player> winners3 = game.selectWinner();
        assertEquals(2, winners3.size());
        assertTrue(winners3.contains(player3));
        assertTrue(winners3.contains(player4));

        game.getPlayers().get(3).setNumOfAimDone(2);
        List<Player> winners4 = game.selectWinner();
        assertEquals(1, winners4.size());
        assertTrue(winners4.contains(player4));

        game.getPlayers().get(2).setNumOfAimDone(2);
        List<Player> winners5 = game.selectWinner();
        assertEquals(2, winners5.size());
        assertTrue(winners5.contains(player3));
        assertTrue(winners5.contains(player4));

        game.getPlayers().getFirst().setScore(14);
        game.getPlayers().getFirst().setNumOfAimDone(1);
        List<Player> winners6 = game.selectWinner();
        assertEquals(2, winners6.size());
        assertTrue(winners6.contains(player3));
        assertTrue(winners6.contains(player4));
    }

    @Test
    public void testCalculateFinalScore() {
        Game game = new Game();
        AimCard card = new AimCard(1, 2, new ArrayList<>(),new AimCheckerLPattern(),"x");
        AimCard card6 = new AimCard(1, 2, new ArrayList<>(),new AimCheckerDiagonalPattern(),"x");
        AimCard card7 = new AimCard(1, 2, new ArrayList<>(),new AimCheckerDiagonalPattern(),"x");
        card6.getCheckResources().add(CardElements.INKWELL);
        card7.getCheckResources().add(CardElements.INKWELL);
        game.getCommonBoard().plateAimCard[0] = card6;
        game.getCommonBoard().plateAimCard[1] = card7;
        List<CardElements> checkResources = new ArrayList<>();
        checkResources.add(CardElements.ANIMAL);
        checkResources.add(CardElements.ANIMAL);
        checkResources.add(CardElements.ANIMAL);
        AimCard card2 = new AimCard(2,3,checkResources,new AimCheckerDiagonalPattern(),"y");
        Player player1 = new Player("Simo",game,card);
        Player player2 = new Player("Andre",game,card2);
        Player player3 = new Player("Nico",game,card);
        Player player4 = new Player("Gio",game,card);
        game.getPlayers().add(player1);
        game.getPlayers().add(player2);
        game.getPlayers().add(player3);
        game.getPlayers().add(player4);
        game.getPlayers().getFirst().setScore(23);
        game.getPlayers().get(1).setScore(24);
        game.getPlayers().get(2).setScore(24);
        game.getPlayers().getLast().setScore(22);
        ResourceCard card3 = new ResourceCard(3,CardElements.ANIMAL,new ArrayList<>(),0);
        ResourceCard card4 = new ResourceCard(4,CardElements.ANIMAL,new ArrayList<>(),0);
        ResourceCard card5 = new ResourceCard(5,CardElements.ANIMAL,new ArrayList<>(),0);
        card3.setCoordinates(0,0);
        card4.setCoordinates(1,1);
        card5.setCoordinates(2,2);
        game.getPlayers().get(1).getPrivateBoard().getCardGrid().add(card3);
        game.getPlayers().get(1).getPrivateBoard().getCardGrid().add(card4);
        game.getPlayers().get(1).getPrivateBoard().getCardGrid().add(card5);

        game.calculateFinalScore();
        assertEquals(23, game.getPlayers().getFirst().getScore());
        assertEquals(27, game.getPlayers().get(1).getScore());
        assertEquals(24, game.getPlayers().get(2).getScore());
        assertEquals(22, game.getPlayers().getLast().getScore());
        assertEquals(game.getPlayers().get(1).getNumOfAimDone(),1);

        game.getPlayers().getFirst().setScore(30);
        game.calculateFinalScore();
        assertEquals(29, game.getPlayers().getFirst().getScore());
    }
}
