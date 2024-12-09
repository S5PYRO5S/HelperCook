package org.helperCook;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.helperCook.ColorConstants.*;
import static org.helperCook.ColorConstants.ANSI_RESET;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        RecipeLoader loader = new RecipeLoader();
        File recipeFile1 = new File("RecipesFolder/pancakes.txt");
        File recipeFile2 = new File("RecipesFolder/cacio_e_pepe.txt");

        Recipe recipe1 = loader.loadRecipe(recipeFile1);
        Recipe recipe2 = loader.loadRecipe(recipeFile2);
        recipe2.printRecipe();
//        Recipe recipe1 = loader.loadRecipe(recipeFile1);
//        recipe1.printRecipe();
//        Recipe recipe2 = loader.loadRecipe(recipeFile2);
//        recipe2.printRecipe();

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
        Unit unit1 = UnitFactory.create(2, "kg"); // 2 kg
        Unit unit2 = UnitFactory.create(100, "gr"); // 100 g

        Unit combined = unit1.add(unit2); // Result in kg (calling unit)
        Unit combined2 = unit2.add(unit1); // Result in g (calling unit)

        System.out.println(combined.toString());
        System.out.println(combined2.toString());

        //Duration dur4 = dur1.add(dur2).add(dur3);
        // System.out.println(dur4.toString());
    }
}