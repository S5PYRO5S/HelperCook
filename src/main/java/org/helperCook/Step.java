package org.helperCook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Step
{
    private final String instruction;
    private final Map<Ingredient, Unit> ingredientsAndUnits;
    private final List<Cookware> cookwares;
    private final Duration duration;

    public Step(StepBuilder builder)
    {
        this.instruction = builder.instruction;
        this.ingredientsAndUnits = builder.ingredientsAndUnit;
        this.cookwares = builder.cookwares;
        this.duration = builder.duration;
    }

    public String getInstruction() {return instruction;}
    public Map<Ingredient, Unit> getIngredients() {return ingredientsAndUnits;}
    public List<Cookware> getCookwares() {return cookwares;}
    public Duration getDuration() {return duration;}

    public static class StepBuilder
    {
        private String instruction;
        private final Map<Ingredient, Unit> ingredientsAndUnit = new HashMap<>();
        private final ArrayList<Cookware> cookwares = new ArrayList<>();
        private Duration duration;

        public Step build() { return new Step(this); }

        public StepBuilder setInstruction(String instruction)
        {
            this.instruction = instruction;
            return this;
        }

//        public StepBuilder addIngredient(Ingredient ingredient, UnitImpl quantity) {
//            //update ingredient quantity if it already exists
//            ingredients.merge(ingredient, quantity, Double::sum);
//            return this;
//        }

        public StepBuilder addIngredient(Ingredient ingredient, Unit unit)
        {
            if (ingredientsAndUnit.containsKey(ingredient)) {
                Unit existingUnit = ingredientsAndUnit.get(ingredient); // Get the existing unit for the ingredient
                if (existingUnit != null) {
                    Unit newUnit = existingUnit.add(unit); // Add the units together
                    ingredientsAndUnit.put(ingredient, newUnit);
                } else {
                    // Handle case where the existing unit is null
                    // You can throw an exception or handle the case differently depending on the logic
                    System.err.println("Existing unit for ingredient " + ingredient.getName() + " is null.");
                }
            } else {
                // If it's not in the map, simply add it
                ingredientsAndUnit.put(ingredient, unit);
            }
            return this;
        }

        public StepBuilder addCookware(Cookware cookware)
        {
            this.cookwares.add(cookware);
            return this;
        }

        public StepBuilder setDuration(Duration duration)
        {
            if(this.duration == null) this.duration = duration;
            else this.duration = this.duration.add(duration);
            return this;
        }
    }
}
