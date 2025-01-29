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
        System.out.println(ColorConstants.ANSI_PURPLE + "Υλικά:" + ColorConstants.ANSI_RESET);

        for (Map.Entry<Ingredient, Unit> ingredientEntry : recipe.getTotalIngredientsAndUnit().entrySet()) {
            System.out.println(ingredientEntry.getKey().getName() + " " + ingredientEntry.getValue());
        }
    }
    //Print total cookware
    public void displayTotalCookware(Recipe recipe) {
        System.out.println(ColorConstants.ANSI_PURPLE + "Σκεύη:" + ColorConstants.ANSI_RESET);

        for (Cookware cookware : recipe.getTotalCookware()) {
            System.out.println(cookware.getName());
        }
    }

    //Print total time needed
    public void displayTotalDuration(Recipe recipe) {
        System.out.println(ColorConstants.ANSI_PURPLE + "Συνολική ώρα:\n" + ColorConstants.ANSI_RESET + recipe.getTotalDuration());
    }

    //Print steps
    public void displaySteps(Recipe recipe) {
        int i = 1;
        System.out.println(ColorConstants.ANSI_PURPLE + "Βήματα:" + ColorConstants.ANSI_RESET);

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

    //Print Shopping List
    public void printShoppingList(ShoppingList shoppingList) {
        System.out.println(ColorConstants.ANSI_CYAN + "Λίστα Αγορών:" + ColorConstants.ANSI_RESET);
        for (Ingredient ingredient : shoppingList.getTotalShoppingListIngredients().keySet()) {
            System.out.println( ingredient.getName() + " ".repeat( shoppingList.getMaxIngredientLength() - ingredient.getName().length() ) + " : " + shoppingList.getTotalShoppingListIngredients().get( ingredient ) );
        }
    }

    //Print Usage format
    public static void printUsage() {
        System.out.println( ANSI_YELLOW + """ 
                        Usage for CLI:
                            java -jar helperCook2.0.jar <file> [integer] -t   # Use relative or absolute paths for files
                            or
                            java -jar helperCook2.0.jar -list <file1> <file1> ... -t # Use relative or absolute paths for files
                        Usage for GUI:
                            java -jar helperCook2.0.jar
                            or
                            java -jar helperCook2.0.jar <file1> <file2> ...   # Use relative or absolute paths for files
                    """ + ANSI_RESET );
    }
}
