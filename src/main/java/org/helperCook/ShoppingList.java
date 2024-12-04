package org.helperCook;

import java.io.File;
import java.nio.file.Files;
import java.util.*;

public class ShoppingList {
    private Map<Ingredient, Double> totalIngredients = new HashMap<>();
    private List<Recipe> totalRecipes;

    public ShoppingList(List<Recipe> totalRecipes) {
        this.totalRecipes = totalRecipes;
    }
    private void addIngredient(Map<Ingredient, Double> ingredients){
        for ( Map.Entry<Ingredient, Double> ingredient : ingredients.entrySet() ){
            totalIngredients.merge(ingredient.getKey(), ingredient.getValue(), Double::sum);
        }
    }
    public void generateShoppingList(){
        for (Recipe recipe : totalRecipes){
            addIngredient(recipe.getTotalIngredients());
        }
    }
    public void PrintShoppingList() throws InterruptedException {
        System.out.println("Shopping List:");
        Thread.sleep(1000);
        for (Map.Entry<Ingredient, Double> ingredient : totalIngredients.entrySet()) {
            System.out.println(ingredient.getValue() + " " + ingredient.getKey().getName());
            Thread.sleep(500);
        }



    }
}
