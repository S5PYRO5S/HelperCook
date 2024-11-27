package org.helperCook;

import static org.helperCook.ColorConstants.*;
import static org.helperCook.RegexConstants.FILE_REGEX;


public class TestArguments {
    public static void main(String[] args) throws InvalidCommandLineArgumentException {

        switch (args.length){

            case 0 -> throw new InvalidCommandLineArgumentException( ANSI_RED + "No command line arguments" + ANSI_RESET );

            case 1 -> {
                if ( args[0].matches( FILE_REGEX ) ) {
                    //TODO call method to read file +++
                    return;
                }
                throw new InvalidCommandLineArgumentException( ANSI_RED + "Invalid command line arguments" + ANSI_RESET );
            }

            default -> {
                int argPos = ArgumentChecker.checkList(args, "-list");
                if (argPos == -1)
                    throw new InvalidCommandLineArgumentException( ANSI_RED + "Invalid command line arguments" + ANSI_RESET );
                for (int i = 0; i < args.length; i++) {
                    if ( i == argPos )
                        continue; // skip the argument that was checked
                    if ( args[i].matches( FILE_REGEX ) ) {
                        System.out.println( ANSI_CYAN + "Cook file detected: " + args[i] + ANSI_RESET );
                    }
                    System.out.println( args[i] );
                }
            }
        }
    }
}
