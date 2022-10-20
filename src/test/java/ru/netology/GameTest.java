package ru.netology;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class GameTest {


    @Test
    public void gameEqualsShouldReturnFalseIfObjectIsNull() {
        GameStore store = new GameStore();
        Game game = new Game("Нетология Баттл Онлайн", "Аркады", store);
        assertFalse(game.equals(null));
    }

    @Test
    public void gameEqualsShouldReturnFalseIfObjectIsNotGame() {
        GameStore store = new GameStore();
        Game game = new Game("Нетология Баттл Онлайн", "Аркады", store);
        assertFalse(game.equals(store));
    }

    @Test
    public void gameEqualsShouldReturnFalseIfTitlesAreNotEquals() {
        GameStore store = new GameStore();
        Game game1 = new Game("Нетология Баттл Онлайн", "Аркады", store);
        Game game2 = new Game("Нетология Баттл Онлай", "Аркады", store);
        assertFalse(game1.equals(game2));
    }

    @Test
    public void gameEqualsShouldReturnFalseIfGenresAreNotEquals() {
        GameStore store = new GameStore();
        Game game1 = new Game("Нетология Баттл Онлайн", "Аркады", store);
        Game game2 = new Game("Нетология Баттл Онлайн", "FPS", store);
        assertFalse(game1.equals(game2));
    }

    @Test
    public void gameEqualsShouldReturnFalseIfStoresAreNotEquals() {
        GameStore store1 = new GameStore();
        GameStore store2 = new GameStore();
        Game game1 = new Game("Нетология Баттл Онлайн", "Аркады", store1);
        Game game2 = new Game("Нетология Баттл Онлайн", "Аркады", store2);
        assertFalse(game1.equals(game2));
    }
}
