package ru.netology;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class GameStoreTest {

    /*
    тестирование publishGame() через containsGame()
     */
    @Test
    public void shouldAddGame() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        assertTrue(store.containsGame(game));
    }


    @Test
    public void shouldNotAddGameIfGameTitleIsEmpty() {
        GameStore store = new GameStore();
        Game game = store.publishGame("", "РПГ");

        assertNull(game);
        assertFalse(store.containsGame(game));
    }

    @Test
    public void shouldNotAddGameIfGameGenreIsEmpty() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Винни-Пух и его друзья", "");

        assertNull(game);
        assertFalse(store.containsGame(game));
    }

    @Test
    public void shouldNotAddGameIfGameExistsInStore() {
        GameStore store = new GameStore();
        Game game1 = store.publishGame("Red Alert 15", "RTS");
        Game game2 = store.publishGame("Red Alert 15", "RTS");

        assertFalse(store.containsGame(game2));
    }

/*
тестируем containsGame()
*/
    @Test
    public void containsGameShouldReturnTrueIfGameContainsInStore() {
        GameStore store = new GameStore();
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = store.publishGame("Red Alert 15", "RTS");
        Game game3 = store.publishGame("Винни-Пух и его друзья", "Quest");
        Game game4 = store.publishGame("Quake 10", "Shooter");
        Game game5 = store.publishGame("Civilization 15", "Strategy");

        assertTrue(store.containsGame(game3));
    }

    @Test
    public void containsGameShouldReturnTrueIfGameContainsInStoreAtFirstPosition() {
        GameStore store = new GameStore();
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = store.publishGame("Red Alert 15", "RTS");
        Game game3 = store.publishGame("Винни-Пух и его друзья", "Quest");
        Game game4 = store.publishGame("Quake 10", "Shooter");
        Game game5 = store.publishGame("Civilization 15", "Strategy");

        assertTrue(store.containsGame(game1));
    }

    @Test
    public void containsGameShouldReturnTrueIfGameContainsInStoreAtLastPosition() {
        GameStore store = new GameStore();
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = store.publishGame("Red Alert 15", "RTS");
        Game game3 = store.publishGame("Винни-Пух и его друзья", "Quest");
        Game game4 = store.publishGame("Quake 10", "");
        Game game5 = store.publishGame("Civilization 15", "Strategy");

        assertTrue(store.containsGame(game5));
    }

    @Test
    public void containsGameShouldReturnFalseIfGameIsNull() {
        GameStore store = new GameStore();
        Game game = null;

        assertFalse(store.containsGame(game));
    }


    @Test
    public void containsGameShouldReturnFalseIfGameAbsentInStore() {
        GameStore store1 = new GameStore();
        GameStore store2 = new GameStore();

        Game game1 = store1.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = store1.publishGame("Red Alert 15", "RTS");
        Game game3 = store1.publishGame("Винни-Пух и его друзья", "Quest");
        Game game4 = store1.publishGame("Quake 10", "");
        Game game5 = store1.publishGame("Civilization 15", "Strategy");

        Game game6 = new Game("Solitaire", "Puzzle ", store1);
        Game game7 = new Game("Solitaire", "Puzzle ", store2);
        Game game8 = new Game("Solitaire", "Puzzle ", null);

        assertFalse(store1.containsGame(game6));
        assertFalse(store1.containsGame(game7));
        assertFalse(store1.containsGame(game8));
    }

/*
тестирование addPlayTime(playerName, hours) через getSumPlayedTime
*/
    @Test
    public void shouldAddPlayTimeIfPlayerExistAndGameInstalled() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        int initialTime = store.getSumPlayedTime();
        Player player = new Player("Vasya");
        player.installGame(game);
        int playedTime = 5;
        //player.play(game, playedTime);
        store.addPlayTime(player.getName(), playedTime);

        int expected = initialTime + playedTime;
        int actual = store.getSumPlayedTime();

        assertEquals(expected, actual);
    }

    //поскольку addPlayTime вызывается методом Player.play(), то проверка (не тест) на инсталлированность
    //должна быть в реализации Player.play()
    //тест целесообразно убрать
    /*
    @Test
    public void shouldNotAddPlayTimeIfGameIsNotInstalled() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        int initialTime = store.getSumPlayedTime();
        Player player = new Player("Vasya");
        int playedTime = 5;
        store.addPlayTime("Vasya",playedTime);
        //player.play(game, playedTime);
        int expected = initialTime;
        int actual = store.getSumPlayedTime();

        assertEquals(expected, actual);
    }
*/

    @Test
    public void shouldNotAddPlayTimeIfGameIsNotPublished() {
        GameStore store = new GameStore();
        Game game = new Game("Нетология Баттл Онлайн", "Аркады", null);
        int initialTime = store.getSumPlayedTime();
        Player player = new Player("Vasya");
        int playedTime = 5;
        store.addPlayTime("Vasya",playedTime);
        //player.play(game, playedTime);
        int expected = initialTime;
        int actual = store.getSumPlayedTime();

        assertEquals(expected, actual);
    }
    @Test
    public void shouldAddPlayerAndPlayTimeIfPlayerAbsent() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        int initialTime = store.getSumPlayedTime();
        String initialMostPlayer = store.getMostPlayer();

        Player player = new Player("Vasya");
        player.installGame(game);
        int playedTime = 5;
        store.addPlayTime("Vasya",playedTime);
        //player.play(game, playedTime);

        int expectedTime = playedTime;
        int actualTime = store.getSumPlayedTime() - initialTime;
        String expectedMostPlayer = player.getName();
        String actualMostPlayer = store.getMostPlayer();

        assertNull(initialMostPlayer);
        assertEquals(expectedTime, actualTime);
        assertSame(expectedMostPlayer, actualMostPlayer);
    }

    @Test
    public void shouldNotAddPlayTimeIfPlayedTimeIsNegative() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        int initialTime = store.getSumPlayedTime();
        Player player = new Player("Vasya");
        player.installGame(game);
        int playedTime = -5;
        store.addPlayTime("Vasya",playedTime);
        //player.play(game, playedTime);
        int expected = initialTime;
        int actual = store.getSumPlayedTime();

        assertEquals(expected, actual);
    }

/*
тестирование getMostPlayer()
*/
    @Test
    public void getMostPlayerShouldReturnNullIfNoPlayersExist() {
        GameStore store = new GameStore();
        assertNull(store.getMostPlayer());
    }

    @Test
    public void getMostPlayerShouldReturnMostPlayerOfOnePlayer() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Player player = new Player("Vasya");
        player.installGame(game);
        player.play(game, 5);
        assertSame(player.getName(), store.getMostPlayer());
    }

    @Test
    public void getMostPlayerShouldReturnMostPlayerOfSomePlayers() {
        GameStore store = new GameStore();
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = store.publishGame("Red Alert 15", "RTS");
        Player player1 = new Player("Vasya");
        player1.installGame(game1);
        player1.play(game1, 5);
        Player player2 = new Player("Kolya");
        player2.installGame(game1);
        player2.play(game1, 15);

        assertSame(player2.getName(), store.getMostPlayer());
    }


/*
тестируем getSumPlayedTime()
*/
    @Test
    public void getSumPlayedTimeShouldReturnZeroIfNoPlayers() {
        GameStore store = new GameStore();
        int actual = store.getSumPlayedTime();

        assertNull(store.getMostPlayer());
        assertEquals(0, actual);
    }

    @Test
    public void getSumPlayedTimeShouldReturnSumOfPlayedTimeAfterOnePlay() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Player player = new Player("Vasya");
        player.installGame(game);
        int curPlayedTime = 5;
        player.play(game, curPlayedTime);
        int expected = curPlayedTime;
        int actual = store.getSumPlayedTime();

        assertEquals(expected, actual);
    }

    @Test
    public void getSumPlayedTimeShouldReturnSumOfPlayedTimeAfterSomePlay() {
        GameStore store = new GameStore();
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = store.publishGame("Red Alert 15", "RTS");
        Game game3 = store.publishGame("Винни-Пух и его друзья", "Quest");
        Game game4 = store.publishGame("Quake 10", "FPS");
        Game game5 = store.publishGame("Civilization 15", "Strategy");

        int expected = 0;

        Player player1 = new Player("Vasya");
        Player player2 = new Player("Kolya");
        Player player3 = new Player("Petya");

        player1.installGame(game1);
        int curPlayedTime = 5;
        player1.play(game1, curPlayedTime);
        expected += curPlayedTime;

        player1.installGame(game2);
        curPlayedTime = 15;
        player1.play(game2, curPlayedTime);
        expected += curPlayedTime;

        player2.installGame(game3);
        curPlayedTime = 7;
        player2.play(game3, curPlayedTime);
        expected += curPlayedTime;

        player2.installGame(game4);
        curPlayedTime = 10;
        player2.play(game4, curPlayedTime);
        expected += curPlayedTime;

        player3.installGame(game5);
        curPlayedTime = 8;
        player3.play(game5, curPlayedTime);
        expected += curPlayedTime;

        player3.installGame(game1);
        curPlayedTime = 4;
        player3.play(game1, curPlayedTime);
        expected += curPlayedTime;

        player3.installGame(game2);
        curPlayedTime = 6;
        player3.play(game2, curPlayedTime);
        expected += curPlayedTime;

        player3.installGame(game3);
        curPlayedTime = 9;
        player3.play(game3, curPlayedTime);
        expected += curPlayedTime;

        assertEquals(expected, store.getSumPlayedTime());
    }


}
