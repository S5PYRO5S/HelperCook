package org.helperCook;

import java.io.File;
import java.util.List;

import static org.helperCook.ColorConstants.*;
import static org.helperCook.ColorConstants.ANSI_RESET;

public class Main
{
    public static void main(String[] args)
    {
        List<File> files = null;
        try {
            CheckArguments checkArguments = new CheckArguments();
            files = checkArguments.Check( args );
            System.out.println( checkArguments.getMode() );
        } catch (Exception e) {
            //throw new RuntimeException( e );
            System.err.println( ANSI_RED + "Error: " + e.getMessage() + ANSI_RESET );
            System.out.println( ANSI_YELLOW + """ 
                        Usage:
                            java -jar helperCook.jar <file> [integer]    # Use relative or absolute paths for files
                            or
                            java -jar helperCook.jar -list <file> <file> ...  # Use relative or absolute paths for files
                    """ + ANSI_RESET );
        }
    }
}