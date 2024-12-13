package org.helperCook;


import java.util.*;


public class ShoppingList {
    private final Map<Ingredient, Unit> totalIngredients = new HashMap<>();
    private final List<Recipe> totalRecipes;
    private int maxIngredientLength = 0;

    public ShoppingList(List<Recipe> totalRecipes) {
        // it is not possible for the list to be null
        this.totalRecipes = totalRecipes;
        this.generateShoppingList();
    }
    private void addIngredient(Ingredient ingredient, Unit unit){
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
    private void generateShoppingList(){
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
