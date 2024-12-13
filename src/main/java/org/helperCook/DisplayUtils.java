package org.helperCook;

import java.util.Map;

import static org.helperCook.ColorConstants.ANSI_RESET;
import static org.helperCook.ColorConstants.ANSI_YELLOW;

public class DisplayUtils {

    //Constructor
    public DisplayUtils() {
    }

    //Print total ingredients
    public void displayTotalIngredients(Recipe recipe) {
        System.out.println(ColorConstants.ANSI_PURPLE +"Υλικά:" + ColorConstants.ANSI_RESET);

        for (Map.Entry<Ingredient, Unit> ingredientEntry : recipe.getTotalIngredientsAndUnit().entrySet()) {
            System.out.println(ingredientEntry.getKey().getName() + " " + ingredientEntry.getValue());
        }
    }

    //Print total cookware
    public void displayTotalCookware(Recipe recipe) {
        System.out.println("Σκεύη:");

        for (Cookware cookware : recipe.getTotalCookware()) {
            System.out.println(cookware.getName());
        }
    }

    //Print total time needed
    public void displayTotalDuration(Recipe recipe) {
        System.out.println("Συνολική ώρα:\n" + recipe.getTotalDuration());
    }

    //Print steps
    public void displaySteps(Recipe recipe) {
        int i = 1;
        System.out.println("Βήματα:");

        for (Step step : recipe.getSteps()) {
            System.out.println(i + ". " + step.getInstruction() + "\n");
            i ++;
        }
    }


    //Print full recipe
    public void displayFullRecipe(Recipe recipe) {
        this.displayTotalIngredients(recipe);
        System.out.println();
        this.displayTotalCookware(recipe);
        System.out.println();
        this.displayTotalDuration(recipe);
        System.out.println();
        this.displaySteps(recipe);
    }

    public void printShoppingList(ShoppingList shoppingList) {
        System.out.println(ColorConstants.ANSI_CYAN + "Λίστα Αγορών:" + ColorConstants.ANSI_RESET);
        for (Ingredient ingredient : shoppingList.getTotalShoppingListIngredients().keySet()) {
            System.out.println( ingredient.getName() + " ".repeat( shoppingList.getMaxIngredientLength() - ingredient.getName().length() ) + " : " + shoppingList.getTotalShoppingListIngredients().get( ingredient ) );
        }
    }

    public static void printUsage() {
        System.out.println( ANSI_YELLOW + """ 
                        Usage:
                            java -jar helperCook.jar <file> [integer]    # Use relative or absolute paths for files
                            or
                            java -jar helperCook.jar -list <file> <file> ...  # Use relative or absolute paths for files
                    """ + ANSI_RESET );
    }
}
