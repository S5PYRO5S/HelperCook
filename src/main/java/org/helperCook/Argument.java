package org.helperCook;

import java.io.File;

import static org.helperCook.ColorConstants.*;
import static org.helperCook.RegexConstants.FILE_REGEX;

public class Argument {
    private final String arg;

    public Argument(String arg) {
        this.arg = arg;
    }

    public File isValidCookFile() throws InvalidCommandLineArgumentException {
        File file = new File( arg );
        if ( !file.exists() ) {
            throw new InvalidCommandLineArgumentException( ANSI_RED + "File does not exist: " + arg + ANSI_RESET);
        }
        if ( !file.isFile() ) {
            throw new InvalidCommandLineArgumentException( ANSI_RED + "Path is not a file: " + arg + ANSI_RESET);
        }
        if ( !file.canRead() ) {
            throw new InvalidCommandLineArgumentException( ANSI_RED + "File is not readable: " + arg + ANSI_RESET);
        }
        if ( !file.getName().matches( FILE_REGEX ) ) {
            throw new InvalidCommandLineArgumentException( ANSI_RED + "Invalid file name: " + arg + ANSI_RESET );
        }
        return file;
    }

}
