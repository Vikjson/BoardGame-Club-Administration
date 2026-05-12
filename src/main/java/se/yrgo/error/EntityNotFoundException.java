package se.yrgo.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Base exception for cases where an entity cannot be found.
 * <p>
 * This exception is mapped to HTTP 404 (NOT_FOUND) and is used
 * across the application when requested data does not exist.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Not found.")
public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }
    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    public EntityNotFoundException(Throwable cause) {
        super(cause);
    }
}
