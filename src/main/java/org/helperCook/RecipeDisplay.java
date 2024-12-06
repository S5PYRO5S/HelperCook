package org.helperCook;

import java.util.Map;

public class RecipeDisplay {

    //Constructor
    public RecipeDisplay() {
    }

    //Print total ingredients
    public void displayTotalIngredients(Recipe recipe) {
        System.out.println("Υλικά:");

        for (Map.Entry<Ingredient, Double> ingredientEntry : recipe.getTotalIngredients().entrySet()) {
            System.out.println(ingredientEntry.getKey().getName() + " " + ingredientEntry.getValue() + ingredientEntry.getKey().getUnit());
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
        System.out.println("Συνολική ώρα:");

        for (Duration duration : recipe.getTotalDuration()) {
            System.out.println(duration.toString());
        }
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
}
