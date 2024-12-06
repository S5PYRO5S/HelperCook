package org.helperCook;


import java.util.*;


public class ShoppingList {
    private final Map<Ingredient, Double> totalIngredients = new HashMap<>();
    private final List<Recipe> totalRecipes;
    private int maxIngredientLength = 0;

    public ShoppingList(List<Recipe> totalRecipes) {
        // it is not possible for the list to be null
        this.totalRecipes = totalRecipes;
        this.generateShoppingList();
    }
    private void addIngredient(Map<Ingredient, Double> ingredients){
        for ( Map.Entry<Ingredient, Double> ingredient : ingredients.entrySet() ){
            totalIngredients.merge(ingredient.getKey(), ingredient.getValue(), Double::sum);
            maxIngredientLength = Math.max(maxIngredientLength, ingredient.getKey().getName().length());
        }
    }
    private void generateShoppingList(){
        for (Recipe recipe : totalRecipes){
            addIngredient(recipe.getTotalIngredients());
        }
    }
    public  Map<Ingredient, Double> getTotalShoppingListIngredients(){
        return totalIngredients;
    }
    public int getMaxIngredientLength(){
        return maxIngredientLength;
    }

}
