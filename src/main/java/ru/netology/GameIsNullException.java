package ru.netology;

public class GameIsNullException extends RuntimeException {
    public GameIsNullException(String msg) {
        super(msg);
    }
}
