package org.helperCook;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ShoppingList {
    public static void PrintShoppingList(File[] args) {

        for ( File file : args) {
            List<Ingredient> ingredientList = new LinkedList<>();
            List<Cookware> cookwareList = new LinkedList<>();
            List<Duration> durationList = new LinkedList<>();
            List<Step> stepList = new LinkedList<>();
            RecipeLoader recipeLoader = new RecipeLoader();

        }
    }
}
