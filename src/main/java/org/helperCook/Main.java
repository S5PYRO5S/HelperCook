package org.helperCook;

import java.io.File;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        RecipeLoader loader = new RecipeLoader();

        List<File> recipeFiles = List.of(new File("Recipe1.txt"), new File("pancaks_recipe.txt"));
        List<Recipe> recipes = loader.loadRecipes(recipeFiles);

        for(Recipe r : recipes) r.printRecipe();
    }
}