package org.example;

import java.io.File;
import java.io.IOException;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        RecipeLoader loader = new RecipeLoader();
        File recipeFile = new File("Recipe1.txt");
        Recipe recipe = loader.loadRecipe(recipeFile);
        recipe.printRecipe();
    }
}