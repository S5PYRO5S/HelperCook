package org.helperCook;


import java.util.*;


public class ShoppingList {
    private final Map<Ingredient, Unit> totalIngredients = new HashMap<>(); // total ingredients in the shopping list
    private final List<Recipe> totalRecipes;
    private int maxIngredientLength = 0; // maximum length of the ingredient name ( for formatting purposes)

    public ShoppingList(List<Recipe> totalRecipes) {
        // it is not possible for the list to be null
        // so no need for null check
        this.totalRecipes = totalRecipes;
        this.generateShoppingList();
    }
    private void addIngredient(Ingredient ingredient, Unit unit){ // add a single ingredient to the shopping list
        this.totalIngredients.merge(ingredient, unit, (existingUnit, newUnit) -> {
            // Check if the unit types are different
            if (!existingUnit.getUnitType().equals(newUnit.getUnitType())) {
                // Simply replace the existing unit with the new unit (or handle as per requirement)
                return newUnit;
            }
            // If unit types are the same, merge them
            return existingUnit.add(newUnit);
        });
            maxIngredientLength = Math.max(maxIngredientLength, ingredient.getName().length());
    }
    private void generateShoppingList(){ // add multiple ingredients to the shopping list
        for (Recipe recipe : totalRecipes){
            for (Map.Entry<Ingredient, Unit> entry : recipe.getTotalIngredientsAndUnit().entrySet()) {
                addIngredient( entry.getKey(), entry.getValue() );
            }
        }
    }
    public  Map<Ingredient, Unit> getTotalShoppingListIngredients(){
        return totalIngredients;
    }
    public int getMaxIngredientLength(){
        return maxIngredientLength;
    }
}
