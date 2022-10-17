package ru.netology;

public class UnableCreateException extends RuntimeException {

    public UnableCreateException(String msg) {
        super(msg);
    }
}