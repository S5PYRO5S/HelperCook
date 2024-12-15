package org.helperCook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a recipe that contains name, ingredients, cookware, steps and duration
 * It uses the builder pattern to allow the dynamic construction of the Recipe object
 */
public class Recipe
{
    private final String name;
    private final Map<Ingredient, Unit> totalIngredientsAndUnit;
    private final List<Cookware> totalCookware;
    private final List<Step> steps;
    private final Duration totalDuration;

    private Recipe(RecipeBuilder recipeBuilder)
    {
        this.name = recipeBuilder.name;
        this.totalIngredientsAndUnit = recipeBuilder.ingredients;
        this.totalCookware = recipeBuilder.cookwareSet;
        this.steps = recipeBuilder.steps;
        this.totalDuration = recipeBuilder.totalDuration;
    }

    public String getName() {return name;}
    public Map<Ingredient, Unit> getTotalIngredientsAndUnit() {return totalIngredientsAndUnit;}
    public List<Cookware> getTotalCookware() {return totalCookware;}
    public List<Step> getSteps() {return steps;}
    public Duration getTotalDuration() {return totalDuration;}

    //uses method chaining for dynamic object construction (not necessary for the program)
    public static class RecipeBuilder
    {
        String name;
        Map<Ingredient, Unit> ingredients = new HashMap<>();
        List<Cookware> cookwareSet = new ArrayList<>();
        List<Step> steps = new ArrayList<>();
        Duration totalDuration;

        public Recipe build()
        {
            return new Recipe(this);
        }

        public RecipeBuilder setName(String name)
        {
            this.name = name;
            return this;
        }

        public RecipeBuilder addIngredient(Ingredient ingredient, Unit unit)
        {
            // Check if the ingredient is already in the map
            if (this.ingredients.containsKey(ingredient))
            {
                Unit existingUnit = this.ingredients.get(ingredient);

                // Check if the unit types are different
                if (!existingUnit.getUnitType().equals(unit.getUnitType()))
                {
                    // Create a new Ingredient entry for the different unit type
                    this.ingredients.put(ingredient, unit);
                }
                else
                {
                    // If unit types are the same, merge units
                    this.ingredients.put(ingredient, existingUnit.add(unit));
                }
            }
            else
            {
                // If the ingredient does not already exist, add it
                this.ingredients.put(ingredient, unit);
            }
            return this;
        }


        public RecipeBuilder addCookware(Cookware cookware)
        {
            cookwareSet.add(cookware);
            return this;
        }

        public RecipeBuilder addStep(Step step)
        {
            steps.add(step);
            return this;
        }

        public RecipeBuilder addDuration(Duration duration)
        {
            if(this.totalDuration == null) this.totalDuration = duration;   //if the duration object isn't initialized
            else this.totalDuration = this.totalDuration.add(duration);     //if duration already exists
            return this;
        }
    }
}