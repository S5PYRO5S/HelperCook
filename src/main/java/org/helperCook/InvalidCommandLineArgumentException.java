package org.helperCook;

public class InvalidCommandLineArgumentException extends RuntimeException { // Custom Runtime exception (since it is catastrophic) class for invalid command line arguments
    public InvalidCommandLineArgumentException(String message) {
        super(message);
    }
}