package se.yrgo.error;

/**
 * Thrown when a requested GameSession cannot be found in the database.
 */
public class GameSessionDeleteException extends RuntimeException {
    public GameSessionDeleteException(String message) {
        super(message);
    }
}
