package ru.netology;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class GameStoreTest {

    @Test
    public void shouldAddGame() {

        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        assertTrue(store.containsGame(game));
    }

    // другие ваши тесты
    /*
    publishGame(title, genre)

shouldAddGame()
shouldNotAddGameIfGameTitleIsEmpty()
shouldNotAddGameIfGameGenreIsEmpty()
shouldNotAddGameIfGameExistsInStore()

containsGame(game)

shouldReturnTrueIfGameContainsInStore()
ShouldReturnFalseIfGameIsNull()
shouldReturnFalseIfGameAbsentInStore()

addPlayTime(playerName, hours)

shouldAddPlayTimeIfPlayerExist()
shouldAddPlayTimeIfPlayerAbsent()
shouldNotAddPlayTimeIfPlayedTimeIsNegative()

getMostPlayer()

shouldReturnNullIfNoPlayersExist()
shouldReturnMostPlayer()

getSumPlayedTime()

shouldReturnZeroIfNoPlayers()
shouldReturnSumOfPlayedTime()
     */
}
