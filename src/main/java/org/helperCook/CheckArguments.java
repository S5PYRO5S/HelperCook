package org.helperCook;

import java.io.File;

import static org.helperCook.ColorConstants.ANSI_RED;
import static org.helperCook.ColorConstants.ANSI_RESET;


public class CheckArguments {
    public static File[] Check(String[] args) throws InvalidCommandLineArgumentException {

        switch (args.length) {

            case 0 -> throw new RuntimeException(
                        new InvalidCommandLineArgumentException(
                                ANSI_RED + "No command line arguments" + ANSI_RESET ));

            case 1 -> {
                Argument arg = new Argument( args[0] );
                File file;
                try {
                    file = arg.isValidCookFile();
                } catch (InvalidCommandLineArgumentException e) {
                    throw new RuntimeException( e );
                }
                return file.listFiles();
            }

            default -> {
                File[] argFilesArray = new File[args.length - 1];
                int argPos = ListChecker.checkList( args, "-list" );
                if ( argPos == -1 )
                    throw new InvalidCommandLineArgumentException( ANSI_RED + "Invalid command line arguments" + ANSI_RESET );
                int arrayPos =0;
                for (int i = 0; i < args.length; i++) {
                    if ( i == argPos ) continue; // skip the argument that was checked

                    Argument arg = new Argument( args[i] );
                    arg.isValidCookFile();
                    argFilesArray[arrayPos++] = new File( args[i] );
                }
                return argFilesArray;
            }
        }
    }
}
