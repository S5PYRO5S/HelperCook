package org.example;

public interface InstructionParser
{
    void parse(String instruction, Step.StepBuilder stepBuilder, Recipe.RecipeBuilder recipeBuilder);
}
