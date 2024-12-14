package org.helperCook;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.helperCook.ColorConstants.ANSI_RED;
import static org.helperCook.ColorConstants.ANSI_RESET;


public class CheckArguments {
    private String mode;
    private final List<File> fileList = new ArrayList<>();
    private int servings=1;

    public List<File> Check(String[] args) throws InvalidCommandLineArgumentException {

        switch (args.length) {

            case 0 -> throw new InvalidCommandLineArgumentException(
                    ANSI_RED + "No command line arguments" + ANSI_RESET );

            case 1 -> {
                Argument arg = new Argument( args[0] );
                File file = arg.isValidCookFile();
                fileList.add( file );
                mode = "recipe";
                return fileList;
            }
            case 2 -> {
                if ( args[0].equals( "-list" ) ) {
                    Argument arg = new Argument( args[1] );
                    File file = arg.isValidCookFile();
                    fileList.add( file );
                    mode = "list";
                    return fileList;
                } // if not -list, then check if it is a single recipe and has # servings
                try {
                    servings = Integer.parseInt( args[1] );
                    if (servings < 1)
                        throw new InvalidCommandLineArgumentException(
                            ANSI_RED + "Invalid command line arguments" + ANSI_RESET );
                } catch (NumberFormatException e) {
                    throw new InvalidCommandLineArgumentException(
                            ANSI_RED + "Invalid command line arguments" + ANSI_RESET );
                }
                Argument arg = new Argument( args[0] );
                File file = arg.isValidCookFile();
                fileList.add( file );
                mode = "recipe";
                return fileList;
            }

            default -> {
                if ( !args[0].equals( "-list" ) ) // if not -list as first argument, then invalid
                    throw new InvalidCommandLineArgumentException( ANSI_RED + "Invalid command line arguments" + ANSI_RESET );
                for ( int i = 1; i < args.length; i++ ) {
                    Argument arg = new Argument( args[i] );
                    fileList.add( arg.isValidCookFile() );
                }
                mode = "list";
                return fileList;
            }
        }
    }

    public String getMode() {
        return mode;
    }
    public int getServings() {
        return servings;
    }
}
