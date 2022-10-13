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
    public void shouldSumGenreIfTwoGame() {         //сумма часов в игре одного жанра для 2 игр
        GameStore store = new GameStore();
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = store.publishGame("PacMan", "Action");
        Game game3 = store.publishGame("SpiderMan", "Action");

        Player player = new Player("Petya");
        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);

        player.play(game1, 8);
        player.play(game2, 4);
        player.play(game3, 2);

        int expected = 6;
        int actual = player.sumGenre(game2.getGenre());
        assertEquals(expected, actual);
    }

    @Test
    public void shouldSumGenreIfTwoGameForTwoPlayers() {         //сумма часов в игре одного жанра для 2 игр для двух игроков
        GameStore store = new GameStore();
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = store.publishGame("PacMan", "Action");
        Game game3 = store.publishGame("SpiderMan", "Action");

        Player player1 = new Player("Petya");
        player1.installGame(game1);
        player1.installGame(game2);
        player1.installGame(game3);

        player1.play(game1, 1);
        player1.play(game2, 1);
        player1.play(game3, 1);

        Player player2 = new Player("Robert");
        player2.installGame(game1);
        player2.installGame(game2);

        player2.play(game1, 2);
        player2.play(game2, 2);
        player2.play(game2, 4);

        int expected = 2;
        int actual = player1.sumGenre(game2.getGenre());
        assertEquals(expected, actual);
    }

    @Test
    public void countHoursInTheGameNonIntalled() {     // кол-во часов в не установлленой игре(реализация RuntimeExiptions)
        GameStore store = new GameStore();
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");

        Assertions.assertThrows(RuntimeException.class, () -> {
            player.play(game1, 3);
            ;
        });
    }

    @Test
    public void showGameInWhichPlayedMost() {     // показать игру, в которую играли больше всего
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
    public void intallExistingGameForPlayer() {      // установить существующую игру игроку (изменений быть не должно)
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
    public void createPlayerNotName() {                //создать игрока без имени
        Player player1 = new Player("");

        //assertNull(player1);
        assertFalse(player1.getName() == "");
    }

    @Test
    public void showExistingGame() {                // показать существующую игру
        GameStore store = new GameStore();
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        assertTrue(game1.equals(game1));
    }

    @Test
    public void totalHoursPlayedInGame() {          //сумма часов в игре програнных игроком
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);
        int hours = 5;
        player.play(game, hours);
        player.play(game, hours);

        int expected = 15;
        int actual = player.play(game, hours);

        assertEquals(expected, actual);
    }

    @Test
    public void totalHoursPlayedInGameIfTimeNegative() {    //сумма часов в игре проигранном играком если время отрицательное
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);
        int time = -1;
        player.play(game, time);

        int expected = 0;
        int actual = player.play(game, time);

        assertEquals(expected, actual);
    }

    @Test
    public void showMostPlayedGameIfNoGenreOfGameInExistingGames() {
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
    public void shouldNotAddPlayTimeIfGameIsNotInstalled() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        int initialTime = store.getSumPlayedTime();
        Player player = new Player("Vasya");
        int playedTime = 5;
        store.addPlayTime("Vasya", playedTime);
        //player.play(game, playedTime);
        int expected = initialTime;
        int actual = store.getSumPlayedTime();

        assertEquals(expected, actual);
    }
}