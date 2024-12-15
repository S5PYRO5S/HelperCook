package org.helperCook;

import java.io.File;

import static org.helperCook.ColorConstants.ANSI_RED;
import static org.helperCook.ColorConstants.ANSI_RESET;
import static org.helperCook.RegexConstants.FILE_REGEX;

public class Argument { // Class to check if the argument is a valid file and returns the file ( create a file object only once )
    private final String arg;

    public Argument(String arg) {
        this.arg = arg;
    }

    public File isValidCookFile() throws InvalidCommandLineArgumentException {
        File file = new File( arg );
        if ( !file.exists() ) { // if file does not exist, then invalid
            throw new InvalidCommandLineArgumentException( ANSI_RED + "File does not exist: " + arg + ANSI_RESET);
        }
        if ( !file.isFile() ) { // if path is not a file, then invalid
            throw new InvalidCommandLineArgumentException( ANSI_RED + "Path is not a file: " + arg + ANSI_RESET);
        }
        if ( !file.canRead() ) { // if file is not readable, then invalid
            throw new InvalidCommandLineArgumentException( ANSI_RED + "File is not readable: " + arg + ANSI_RESET);
        }
        if ( !file.getName().matches( FILE_REGEX ) ) { // if file name does not match the regex, then invalid
            throw new InvalidCommandLineArgumentException( ANSI_RED + "Invalid file name: " + arg + ANSI_RESET );
        }
        return file;
    }

}
