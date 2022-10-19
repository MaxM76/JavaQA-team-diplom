package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext.Store;

import java.security.KeyStore.Entry;
import java.util.Arrays;
import java.util.HashSet;

public class PlayerTest {

    @Test
    public void shouldSumGenreIfOneGame() {         //сумма часов в игре одного жанра для одной игры
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);
        player.play(game, 3);

        int expected = 3;
        int actual = player.sumGenre(game.getGenre());
        assertEquals(expected, actual);
    }

    // другие ваши тесты

    @Test
    public void shouldSumResultsOfFewGamesWithSameGenre() {
        GameStore store = new GameStore();
        Game game1 = store.publishGame("Max Payne", "Action");
        Game game2 = store.publishGame("PacMan", "Action");
        Game game3 = store.publishGame("SpiderMan", "Action");

        Player player = new Player("Petya");
        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);

        player.play(game1, 8);
        player.play(game2, 4);
        player.play(game3, 2);

        int expected = 14;
        int actual = player.sumGenre(game2.getGenre());
        assertEquals(expected, actual);
    }

    @Test
    public void shouldSumResultsOfFewGamesWithDifferentGenres() {
        GameStore store = new GameStore();
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = store.publishGame("PacMan", "Action");
        Game game3 = store.publishGame("SpiderMan", "Action");
        Game game4 = store.publishGame("Man", "simulation");

        Player player = new Player("Petya");
        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);
        player.installGame(game4);

        player.play(game1, 8);
        player.play(game2, 4);
        player.play(game3, 2);
        player.play(game4, 5);

        int expected = 6;
        int actual = player.sumGenre(game2.getGenre());
        assertEquals(expected, actual);
    }

    @Test
    public void shouldThrowExceptionIfPlayNotInstalledGame() {
        GameStore store = new GameStore();
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");

        Assertions.assertThrows(RuntimeException.class, () -> {
            player.play(game1, 3);
            ;
        });
    }

    @Test
    public void showGameInWhichPlayedMost() {
        GameStore store = new GameStore();
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = store.publishGame("PacMan", "Action");
        Game game3 = store.publishGame("SpiderMan", "Action");
        Game game4 = store.publishGame("Man", "Action");

        Player player = new Player("Petya");
        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);
        player.installGame(game4);

        player.play(game1, 3);
        player.play(game2, 4);

        Game actual = player.mostPlayerByGenre("Action");
        Game expected = game2;
        assertEquals(expected, actual);
    }

    @Test
    public void mostPlayedByGenreShouldReturnNullIfNoInstalledGames() {
        GameStore store = new GameStore();

        Player player = new Player("Petya");

        Game actual = player.mostPlayerByGenre("Аркады");
        Game expected = null;

        assertEquals(expected, actual);
    }

    @Test
    public void mostPlayedByGenreShouldReturnNullIfOnlyOneFreshlyInstalledGameExist() {
        GameStore store = new GameStore();
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");

        Game actual = player.mostPlayerByGenre("Аркады");
        Game expected = null;

        assertEquals(expected, actual);
    }

    @Test
    public void mostPlayedByGenreShouldReturnLonelyInstalledAndPlayedGame() {
        GameStore store = new GameStore();
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game1);
        player.play(game1, 3);

        Game actual = player.mostPlayerByGenre("Аркады");
        Game expected = game1;

        assertEquals(expected, actual);
    }

    @Test
    public void mostPlayedByGenreShouldReturnMostPlayedGameFromFewInstalledAndPlayedGames() {
        GameStore store = new GameStore();
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = store.publishGame("PacMan", "Action");
        Game game3 = store.publishGame("SpiderMan", "Action");
        Game game4 = store.publishGame("Man", "simulation");
        Game game5 = store.publishGame("Max Payne", "Action");

        Player player = new Player("Petya");
        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);
        player.installGame(game4);
        player.installGame(game5);

        player.play(game1, 2);
        player.play(game2, 4);
        player.play(game3, 1);
        player.play(game4, 4);

        Game actual = player.mostPlayerByGenre("Аркады");
        Game expected = game1;

        assertEquals(expected, actual);
    }

    @Test
    public void mostPlayedByGenreShouldReturnNullIfGenreIsNull() {
        GameStore store = new GameStore();
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "");

        Player player = new Player("Petya");
        player.installGame(game1);

        Game actual = player.mostPlayerByGenre("Аркады");
        Game expected = null;

        assertEquals(expected, actual);
    }

    @Test
    public void mostPlayedByGenreShouldReturnNullIfGenreIsEmpty() {
        GameStore store = new GameStore();
        Game game1 = store.publishGame("", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game1);

        Game actual = player.mostPlayerByGenre("Аркады");
        Game expected = null;

        assertEquals(expected, actual);
    }

    @Test
    public void mostPlayedByGenreShouldReturnNullIfGenreIsNotExist() {
        GameStore store = new GameStore();
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = store.publishGame("PacMan", "Action");
        Game game3 = store.publishGame("Man", "simulation");

        Player player = new Player("Petya");
        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);

        player.play(game1, 3);
        player.play(game2, 4);
        player.play(game3, 5);

        Game actual = player.mostPlayerByGenre("shuter");
        Game expected = null;

        assertEquals(expected, actual);
    }

    @Test
    public void installGameShouldNotResetResultsIfGameAlreadyIn() {
        GameStore store = new GameStore();
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game1);

        player.play(game1, 3);
        player.installGame(game1);

        int expected = 6;
        int actual = player.play(game1, 3);
        //int actual = player.sumGenre(game1.getGenre());
        assertEquals(expected, actual);
    }

    @Test
    public void createPlayerNotName() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            Player player1 = new Player("");
            ;
        });
    }

    @Test
    public void createPlayerWithNameNull() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            Player player1 = new Player(null);
            ;
        });
    }

    @Test
    public void showPlayerName() {
        Player player1 = new Player("Petya");
        assertEquals("Petya", player1.getName());
        ;
    }

    @Test
    public void totalHoursPlayedInGame() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);
        int hours = 5;

        int expected = 5;
        int actual = player.play(game, hours);

        assertEquals(expected, actual);
    }

    @Test
    public void totalHoursPlayedInGameIfTimeNegative() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);
        int time = -1;

        int expected = 0;
        int actual = player.play(game, time);

        assertEquals(expected, actual);
    }

    @Test
    public void sumGenreIfGenreIsBlank() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", " ");

        Player player = new Player("Petya");
        player.installGame(game);
        player.play(game, 5);

        assertEquals(0, player.sumGenre(game.getGenre()));
    }

    @Test
    public void mostPlayerByGenreIfGenreIsBlank() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", " ");

        Player player = new Player("Petya");
        player.installGame(game);
        player.play(game, 5);

        assertEquals(null, player.mostPlayerByGenre(game.getGenre()));
    }

    @Test
    public void installGameIfGameEqualsNull() {
        GameStore store = new GameStore();
        Game game = null;

        Player player = new Player("Petya");

        Assertions.assertThrows(RuntimeException.class, () -> {
            player.installGame(game);
            ;
        });
    }

}