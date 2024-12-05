package org.helperCook;

import java.io.File;
import java.util.List;
import java.util.Map;

import static org.helperCook.ColorConstants.*;

public class Main
{
    public static void main(String[] args) {


        //System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        List<File> files = null;
        try {
            CheckArguments checkArguments = new CheckArguments();
            files = checkArguments.Check( args );
            System.out.println( checkArguments.getMode() );
        } catch (Exception e) {
            //throw new RuntimeException( e );
            System.err.println( ANSI_RED + "Error: " + e.getMessage() + ANSI_RESET );
            System.out.println( ANSI_YELLOW + """ 
                        Usage:
                            java -jar helperCook.jar <file> [integer]    # Use relative or absolute paths for files
                            or
                            java -jar helperCook.jar -list <file> <file> ...  # Use relative or absolute paths for files
                    """ + ANSI_RESET );

            System.exit( 1 );
        }
        RecipeLoader loader = new RecipeLoader();
        List<Recipe> recipeList= loader.loadRecipes(files);
        ShoppingList shoppingList = new ShoppingList(recipeList);



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