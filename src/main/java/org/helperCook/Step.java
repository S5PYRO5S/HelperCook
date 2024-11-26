package org.helperCook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Step
{
    private final String instruction;
    private final Map<Ingredient, Double> ingredients;
    private final List<Cookware> cookwares;
    private final Duration duration;

    public Step(StepBuilder builder)
    {
        this.instruction = builder.instruction;
        this.ingredients = builder.ingredients;
        this.cookwares = builder.cookwares;
        this.duration = builder.duration;
    }

    public String getInstruction() {return instruction;}
    public Map<Ingredient, Double> getIngredients() {return ingredients;}
    public List<Cookware> getCookwares() {return cookwares;}
    public Duration getDuration() {return duration;}

    public static class StepBuilder
    {
        private String instruction;
        private final Map<Ingredient, Double> ingredients = new HashMap<>();
        private final ArrayList<Cookware> cookwares = new ArrayList<>();
        private Duration duration;

        public Step build() { return new Step(this); }

        public StepBuilder setInstruction(String instruction)
        {
            this.instruction = instruction;
            return this;
        }

        public StepBuilder addIngredient(Ingredient ingredient, double quantity) {
            //update ingredient quantity if it already exists
            ingredients.merge(ingredient, quantity, Double::sum);
            return this;
        }

        public StepBuilder addCookware(Cookware cookware)
        {
            this.cookwares.add(cookware);
            return this;
        }

        public StepBuilder setDuration(Duration duration)
        {
            this.duration = duration;
            return this;
        }
    }
}
