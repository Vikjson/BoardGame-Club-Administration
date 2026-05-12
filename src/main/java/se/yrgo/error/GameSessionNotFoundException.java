package se.yrgo.error;


public class GameSessionNotFoundException extends RuntimeException {

    public GameSessionNotFoundException(String message) {
        super(message);
    }
}
