package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
        Player player2 = new Player("Robert");
        player2.installGame(game1);
        player2.installGame(game2);

        player1.play(game1, 1);
        player1.play(game2, 1);
        player1.play(game3, 1);
        player2.play(game1, 1);
        player2.play(game2, 1);

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
        Game game2 = store.publishGame("PacMan", "Action");


        Player player = new Player("Petya");
        player.installGame(game1);
        player.installGame(game2);


        player.play(game1, 3);
        player.play(game2, 4);
        player.installGame(game1);

        System.out.println(game2);                     //кол-во часов в игре должно остаться 4 часа
    }
}
