package org.helperCook;

/**
 * Interface representing a parser for instructions.
 */

public interface InstructionParser
{
    /**
     * Parses a String and updates the stepBuilder and recipeBuilder respectively
     *
     * @param instruction the String to parse
     * @param stepBuilder the builder for the current step
     * @param recipeBuilder the builder that represent the entire recipe
     */

    void parse(String instruction, Step.StepBuilder stepBuilder, Recipe.RecipeBuilder recipeBuilder);
}
