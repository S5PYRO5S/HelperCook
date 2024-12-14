package org.helperCook;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class RecipeCLI {

    //Constructor
    public RecipeCLI() {
    }

    //CLI
    public void runCLI(String mode, List<File> files, int factor) throws IOException {
        RecipeLoader recipeLoader = new RecipeLoader();
        DisplayUtils recipeDisplay = new DisplayUtils();
        Recipe recipe;

        //Check which mode user inputs, then run it
        switch (mode) {
            case "recipe":
                recipe = recipeLoader.loadRecipe(files.getFirst(), factor);
                recipeDisplay.displayFullRecipe(recipe);
                break;
            case "list":
                ShoppingList shoppingList = new ShoppingList( recipeLoader.loadRecipes( files ));
                recipeDisplay.printShoppingList(shoppingList);
                break;
            default:
                System.out.println("Invalid mode");
                break;
        }
    }
}
