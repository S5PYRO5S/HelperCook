package org.example;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException
    {

        File recipeFile = new File("Recipe1.txt");
        RecipeLoader loader = new RecipeLoader(recipeFile);
        Recipe recipe = loader.loadRecipe();
        recipe.printRecipe();

        RecipeLoader loader2 = new RecipeLoader("Recipe1.txt");
        recipe = loader2.loadRecipe();
        recipe.printRecipe();
    }
}