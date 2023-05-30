package dat.backend.model.exceptions;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The type Database exception.
 */
public class DatabaseException extends Exception {
    /**
     * Instantiates a new Database exception.
     *
     * @param message the message
     */
    public DatabaseException(String message) {
        super(message);
        Logger.getLogger("web").log(Level.SEVERE, message);
    }

    /**
     * Instantiates a new Database exception.
     *
     * @param ex      the ex
     * @param message the message
     */
    public DatabaseException(Exception ex, String message) {
        super(message);
        Logger.getLogger("web").log(Level.SEVERE, message, ex.getMessage());
    }
}
