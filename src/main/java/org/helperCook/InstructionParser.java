package org.helperCook;

public interface InstructionParser
{
    void parse(String instruction, Step.StepBuilder stepBuilder, Recipe.RecipeBuilder recipeBuilder);
}
