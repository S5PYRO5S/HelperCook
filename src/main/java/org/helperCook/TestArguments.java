package org.helperCook;

import static org.helperCook.ColorConstants.ANSI_RED;
import static org.helperCook.ColorConstants.ANSI_RESET;


public class TestArguments {
    public static void main(String[] args) throws InvalidCommandLineArgumentException {
        ArgumentChecker checkArguments = new ArgumentChecker( args, "-list");
        if (args.length == 0) {
            throw new InvalidCommandLineArgumentException( ANSI_RED + "No command line arguments" + ANSI_RESET );
        }
        int argPosition = checkArguments.checkList();
        if (argPosition == -1) {

            throw new InvalidCommandLineArgumentException( ANSI_RED + "Invalid command line arguments" + ANSI_RESET );
        } else {
            for (int i=0; i<args.length; i++){
                if ( i == argPosition ) { // skip the argument
                    continue;
                }
                if (args[i].matches( ".*\\.cook$" )){
                    System.out.println( ANSI_RED + "Cook file detected: " + args[i] + ANSI_RESET );
                }
                System.out.println(args[i]);
            }
        }

    }
}
