package org.helperCook;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.helperCook.ColorConstants.ANSI_RED;
import static org.helperCook.ColorConstants.ANSI_RESET;


public class CheckArguments {
    public static List<File> Check(String[] args) throws InvalidCommandLineArgumentException {
        List<File> fileList = new ArrayList<>();
        switch (args.length) {

            case 0 -> throw new InvalidCommandLineArgumentException(
                                ANSI_RED + "No command line arguments" + ANSI_RESET );

            case 1 -> {
                Argument arg = new Argument( args[0] );
                File file = arg.isValidCookFile();
                fileList.add( file );
                return fileList;
            }
            case 2 -> {
                Argument arg = new Argument( args[0] );
                File file = arg.isValidCookFile();
                try{
                    Integer.parseInt( args[1] );
                } catch (NumberFormatException e) {
                    throw new InvalidCommandLineArgumentException(
                            ANSI_RED + "Invalid command line arguments" + ANSI_RESET );
                }
                fileList.add( file );
                return fileList;
            }

            default -> {
                int argPos = ListChecker.checkList( args, "-list" );
                if ( argPos == -1 )
                    throw new InvalidCommandLineArgumentException( ANSI_RED + "Invalid command line arguments" + ANSI_RESET );
                for (int i = 0; i < args.length; i++) {
                    if ( i == argPos ) continue; // skip the argument that was checked
                    Argument arg = new Argument( args[i] );
                    fileList.add( arg.isValidCookFile() );

                }
                return fileList;
            }
        }
    }
}
