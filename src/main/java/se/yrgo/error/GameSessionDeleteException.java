package se.yrgo.error;

public class GameSessionDeleteException extends RuntimeException {
    public GameSessionDeleteException(String message) {
        super(message);
    }
}
