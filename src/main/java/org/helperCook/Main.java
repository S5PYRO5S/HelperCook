package org.helperCook;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        RecipeLoader loader = new RecipeLoader();

        List<File> recipeFiles = List.of(new File("RecipesFolder/pancakes.cook"), new File("pancakes_recipe.txt"));
        List<Recipe> recipes = loader.loadRecipes(recipeFiles);

        for(Recipe r : recipes) r.printRecipe();


        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        File[] files;
        try {
            files = CheckArguments.Check(args);
        } catch (Exception e) {
            //throw new RuntimeException( e );
            System.out.println( "Error: " + e.getMessage() );
            System.out.println("Usage: java -jar helperCook.jar <file> or java -jar helperCook.jar -list <file> <file> ...");
            System.exit( 1 );
        }
    }
}