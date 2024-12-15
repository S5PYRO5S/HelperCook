package org.helperCook;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

/**
 * Handles the loading of recipes from file inputs and parsing instructions into recipe objects.
 * It supports:
 * - Loading a single recipe from a file
 * - Loading multiple recipes from a list of files
 */
public class RecipeLoader
{
    private final List<InstructionParser> parsers;

    public RecipeLoader() {this.parsers = initializeParser();}

    /**
     * Loads a single recipe from a file and scales ingredient quantities based on a factor.
     * @param recipeFile the file that contains the instructions
     * @param factor the scaling factor for ingredient quantities
     * @return the recipe object
     * @throws IOException if an error occurs while reading file
     */
    public Recipe loadRecipe(File recipeFile, int factor) throws IOException
    {
        Recipe.RecipeBuilder recipeBuilder = new Recipe.RecipeBuilder();
        recipeBuilder.setName(recipeFile.getName().replaceAll(RegexConstants.NAME_REGEX, "$1"));

        String[] instructionArray = FileReaderUtil.readFileContent((recipeFile));   //may throw exception
        FileReaderUtil.updateInstructionQuantities(instructionArray, factor);

        for(String instruction : instructionArray)
        {
            Step step = parseStep(instruction, recipeBuilder);
            recipeBuilder.addStep(step);
        }
        return recipeBuilder.build();
    }

    /**
     * Loads multiple recipes from a list of files
     * @param recipeFiles the list of files
     * @return the list of file objects
     * @throws IOException if an error occurs while reading any file
     */
    public List<Recipe> loadRecipes(List<File> recipeFiles) throws IOException
    {
        List<Recipe> recipes = new ArrayList<>();
        for(File file : recipeFiles) recipes.add(loadRecipe(file, 1));
        return recipes;
    }

    /**
     * From an instruction parses and builds the step object with the list of registered parsers
     * @param instruction the string to parse
     * @param recipeBuilder the recipe that current recipe that we update with each step
     * @return the created step object
     */
    private Step parseStep(String instruction, Recipe.RecipeBuilder recipeBuilder)
    {
        Step.StepBuilder stepBuilder = new Step.StepBuilder().setInstruction(instruction);
        for(InstructionParser parser : parsers) parser.parse(instruction, stepBuilder, recipeBuilder);
        return stepBuilder.build();
    }

    /**
     * Initializes parser objects
     * @return the parser objects
     */
    private List<InstructionParser> initializeParser()
    {
        List<InstructionParser> parsers = new ArrayList<>();
        parsers.add(new IngredientParser());
        parsers.add(new CookwareParser());
        parsers.add(new DurationParser());
        return parsers;
    }
}