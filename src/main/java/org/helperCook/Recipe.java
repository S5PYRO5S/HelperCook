package org.helperCook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Recipe
{
    private final String name;
    private final Map<Ingredient, Double> totalIngredients;
    private final List<Cookware> totalCookware;
    private final List<Step> steps;
    private final List<Duration> totalDuration;

    private Recipe(RecipeBuilder recipeBuilder)
    {
        this.name = recipeBuilder.name;
        this.totalIngredients = recipeBuilder.ingredients;
        this.totalCookware = recipeBuilder.cookwareSet;
        this.steps = recipeBuilder.steps;
        this.totalDuration = recipeBuilder.totalDuration;
    }

    public String getName() {return name;}
    public Map<Ingredient, Double> getTotalIngredients() {return totalIngredients;}
    public List<Cookware> getTotalCookware() {return totalCookware;}
    public List<Step> getSteps() {return steps;}
    public List<Duration> getTotalDuration() {return totalDuration;}

    //debug
    public void print()
    {
        System.out.println("Total Ingredients: " + this.totalIngredients.size());
        System.out.println("Total Cookware: " + this.totalCookware.size());

        System.out.println("Name: " + name);
        for(Step step : steps)
        {
            System.out.println(step.getInstruction());
            System.out.println("\n");
        }
    }

    public void printRecipe() {
        // Print recipe name
        System.out.println("Recipe: " + this.getName());
        System.out.println("====================================");

        // Print total ingredients
        System.out.println("Ingredients:");
        for(Ingredient ingredient : totalIngredients.keySet())
        {
            System.out.println(ingredient.getName());
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
        Duration totalDuration = Duration.sumOfDurationList(this.getTotalDuration());
        System.out.println(totalDuration.getUnitName());
        System.out.println();

        // Print step-by-step details
        System.out.println("Steps:");
        int stepCounter = 1;
        for (Step step : this.getSteps()) {
            System.out.println("Step " + stepCounter + ":");
            System.out.println("  Instruction: " + step.getInstruction());

            // Print ingredients for this step
            if (!step.getIngredients().isEmpty()) {
                System.out.println("  Ingredients:");
                step.getIngredients().forEach((ingredient, quantity) ->
                        System.out.println("  - " + ingredient.getName() + ": " + quantity + " " + ingredient.getUnit()));
            }

            // Print cookware for this step
            if (!step.getCookwares().isEmpty()) {
                System.out.println("  Cookware:");
                for (Cookware cookware : step.getCookwares()) {
                    System.out.println("  - " + cookware.getName());
                }
            }

            // Print duration for this step
            if (step.getDuration() != null) {
                System.out.println("  Duration: " + step.getDuration().getUnitName());
            } else {
                System.out.println("  Duration: 0 minutes (default)");
            }
            System.out.println();

            stepCounter++;
        }
    }

    public static class RecipeBuilder
    {
        String name;
        Map<Ingredient, Double> ingredients = new HashMap<>();
        List<Cookware> cookwareSet = new ArrayList<>();
        List<Step> steps = new ArrayList<>();
        List<Duration> totalDuration = new ArrayList<>();

        public Recipe build()
        {
            return new Recipe(this);
        }

        public RecipeBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public RecipeBuilder addIngredient(Ingredient ingredient, double quantity) {
            //update ingredient quantity if it already exists
            ingredients.merge(ingredient, quantity, Double::sum);
            return this;
        }

        public RecipeBuilder addCookware(Cookware cookware){
            cookwareSet.add(cookware);
            return this;
        }

        public RecipeBuilder addStep(Step step){
            steps.add(step);
            return this;
        }

        public RecipeBuilder addDuration(Duration duration)
        {
            totalDuration.add(duration);
            return this;
        }
    }
}
