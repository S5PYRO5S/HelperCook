package org.helperCook;

import java.io.File;
import java.util.List;

import static org.helperCook.ColorConstants.*;

public class Main
{
    public static void main(String[] args) throws InterruptedException {
//        RecipeLoader loader = new RecipeLoader();
//
//        List<File> recipeFiles = List.of(new File("RecipesFolder/pancakes.cook"), new File("pancakes_recipe.txt"));
//        List<Recipe> recipes = loader.loadRecipes(recipeFiles);
//
//        for(Recipe r : recipes) r.printRecipe();


        //System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        List<File> files = null;
        try {
            files = CheckArguments.Check(args);
        } catch (Exception e) {
            //throw new RuntimeException( e );
            System.err.println( ANSI_RED + "Error: " + e.getMessage() + ANSI_RESET );
            System.out.println(ANSI_YELLOW+ """ 
                Usage:
                      java -jar helperCook.jar <file> [integer]
                      or
                      java -jar helperCook.jar -list <file> <file> ...""" + ANSI_RESET);
            System.exit( 1 );
        }
        RecipeLoader loader = new RecipeLoader();
        List<Recipe> recipeList= loader.loadRecipes(files);
        ShoppingList shoppingList = new ShoppingList(recipeList);
        shoppingList.generateShoppingList();
        shoppingList.PrintShoppingList();


    }
}