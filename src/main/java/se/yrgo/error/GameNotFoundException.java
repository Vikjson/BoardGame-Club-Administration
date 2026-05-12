package se.yrgo.error;

/**
 * Thrown when a requested Game cannot be found in the database.
 */
public class GameNotFoundException extends RuntimeException {
    public GameNotFoundException() {

    }

    public GameNotFoundException(String message) {
        super(message);
    }

    public GameNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public GameNotFoundException(Throwable cause) {
        super(cause);
    }
}
