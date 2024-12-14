package org.helperCook;
/*
 * Project "helperCook" - 1st assignment for the course Object-Oriented Programming 2
 * Made by : it2023003, it2023052, it2023100
 *
 *
 *
 *
 *
 *
 *
 *
 */

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.helperCook.ColorConstants.*;
import static org.helperCook.ColorConstants.ANSI_RESET;

public class Main
{
    public static void main(String[] args) throws IOException {

        List<File> files = null;
        String mode = "";
        int factor = 1;
        try {
            CheckArguments checkArguments = new CheckArguments();
            files = checkArguments.Check( args );
            mode = checkArguments.getMode();
            factor = checkArguments.getServings();
        } catch (Exception e) {
            System.err.println( ANSI_RED + "Error: " + e.getMessage() + ANSI_RESET );
            DisplayUtils.printUsage();
            System.exit( 1 );
        }
        RecipeLoader recipeLoader = new RecipeLoader();
        RecipeCLI recipeCLI = new RecipeCLI();
        recipeCLI.runCLI(mode, files, factor);




    }

}