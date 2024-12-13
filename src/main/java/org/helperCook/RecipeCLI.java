package org.helperCook;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class RecipeCLI {

    //Constructor
    public RecipeCLI() {
    }

    public void runCLI(String mode, List<File> files) throws IOException {
        RecipeLoader recipeLoader = new RecipeLoader();
        DisplayUtils recipeDisplay = new DisplayUtils();
        Recipe recipe;
        switch (mode) {
            case "recipe":
                recipe = recipeLoader.loadRecipe(files.getFirst());
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
