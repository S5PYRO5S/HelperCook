package org.helperCook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Same as the {@link Recipe} class but in scale of a single instruction
 */
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

        public StepBuilder addIngredient(Ingredient ingredient, Unit unit)
        {
            // Check if the ingredient already exists in the map
            if (ingredientsAndUnit.containsKey(ingredient))
            {
                Unit existingUnit = ingredientsAndUnit.get(ingredient);

                // Check if the unit types are different
                if (!existingUnit.getUnitType().equals(unit.getUnitType()))
                {
                    // Create a new Ingredient entry for the different unit type
                    ingredientsAndUnit.put(ingredient, unit);
                }
                else
                {
                    // If unit types are the same, merge units
                    ingredientsAndUnit.put(ingredient, existingUnit.add(unit));
                }
            }
            else
            {
                // If the ingredient is not in the map, add it
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
