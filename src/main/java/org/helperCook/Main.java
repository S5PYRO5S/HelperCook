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

    }
    public void PrintShoppingList(ShoppingList shoppingList) {
        System.out.println( ANSI_PURPLE + "Shopping List:" );
        String format = "%-" + ( shoppingList.getMaxIngredientLength() + 6) + "s %10.2f";
        for ( Map.Entry<Ingredient, Double> ingredient : shoppingList.getTotalShoppingListIngredients().entrySet()) {
            System.out.println( ANSI_BLUE + String.format( format, ingredient.getKey().getName()
                    + ANSI_CYAN, ingredient.getValue() ) + ANSI_RESET );
        }
    }
}