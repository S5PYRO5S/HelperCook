package org.helperCook;

public class InvalidCommandLineArgumentException extends RuntimeException {
    public InvalidCommandLineArgumentException(String message) {
        super(message);
    }
}
