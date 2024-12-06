package org.helperCook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public void printRecipe()
    {
        // Print recipe name
        System.out.println("Recipe: " + this.getName());
        System.out.println("====================================");

        // Print total ingredients
        System.out.println("Ingredients:");
        for (Ingredient ingredient : totalIngredientsAndUnit.keySet()) {
            // Print ingredient name and its unit
            Unit unit = totalIngredientsAndUnit.get(ingredient);
            System.out.println("- " + ingredient.getName() + ": " + unit.format());
        }
        System.out.println();

        // Print total cookware
        System.out.println("Cookware:");
        for (Cookware cookware : this.getTotalCookware()) {
            System.out.println("- " + cookware.getName());
        }
        System.out.println();

        // Print total durations
        System.out.println("Total Duration:");
        System.out.println(this.getTotalDuration().toString());
        System.out.println();

        // Print step-by-step details
        System.out.println("Steps:");
        int stepCounter = 1;
        for (Step step : this.getSteps())
        {
            System.out.println("Step " + stepCounter + ":");
            System.out.println("  Instruction: " + step.getInstruction());

            // Print ingredients for this step
            if (!step.getIngredients().isEmpty())
            {
                System.out.println("  Ingredients:");
                step.getIngredients().forEach((ingredient, unit) ->
                        System.out.println("    - " + ingredient.getName() + ": " + unit));
            }

            // Print cookware for this step
            if (!step.getCookwares().isEmpty())
            {
                System.out.println("  Cookware:");
                for (Cookware cookware : step.getCookwares()) {
                    System.out.println("    - " + cookware.getName());
                }
            }

            // Print duration for this step
            if (step.getDuration() != null) {
                System.out.println("  Duration: " + step.getDuration().toString());
            } else {
                System.out.println("  Duration: 0 minutes (default)");
            }

            System.out.println();

            stepCounter++;
        }
    }


//    public void printIngredients()
//    {
//        System.out.println("Ingredients:");
//        for (Map.Entry<Ingredient, Double> entry : totalIngredients.entrySet()) {
//            Ingredient ingredient = entry.getKey();
//            double quantityInBase = entry.getValue();
//
//            if (ingredient.getUnit() != null) {
//                // Convert quantity back to the most appropriate unit and format it
//                String formattedQuantity = ingredient.getUnit().format((int) quantityInBase);
//                System.out.println("- " + ingredient.getName() + ": " + formattedQuantity);
//            } else {
//                // Handle ingredients without a predefined unit
//                System.out.println("- " + ingredient.getName() + ": " + quantityInBase + " " + ingredient.getUnitName());
//            }
//        }
//    }

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

//        public RecipeBuilder addIngredient(Ingredient ingredient, double quantity)
//        {
////            //update ingredient quantity if it already exists
////            ingredients.merge(ingredient, quantity, Double::sum);
////            return this;
//            // Convert the incoming quantity to the base unit
//
//
//            return this;
//        }

        public RecipeBuilder addIngredient(Ingredient ingredient, Unit unit)
        {
            // Merge the ingredient and unit into the map
            this.ingredients.merge(ingredient, unit, (existingUnit, newUnit) ->
            {
                // Check if both the existing unit and the new unit are of the same UnitType
                if (existingUnit.getUnitType().equals(newUnit.getUnitType()))
                {
                    // Try to merge them by adding their base values
                    Unit mergedUnit = existingUnit.add(newUnit);
                    // If merging is successful (i.e., not null), return the merged unit
                    return mergedUnit != null ? mergedUnit : existingUnit;
                }
                // If they are not the same UnitType, return the existing unit as is
                return existingUnit;
            });
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