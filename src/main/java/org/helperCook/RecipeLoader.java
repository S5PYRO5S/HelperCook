package org.example;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class RecipeLoader
{
    private final List<InstructionParser> parsers;

    public RecipeLoader() {this.parsers = initializeParser();}

    public Recipe loadRecipe(File recipeFile) throws IOException
    {
        Recipe.RecipeBuilder recipeBuilder = new Recipe.RecipeBuilder();
        recipeBuilder.setName(recipeFile.getName().replaceAll(RegexConstants.NAME_REGEX, "$1"));
        String[] instructionArray = FileReaderUtil.readFileContent(recipeFile).split("\\n\\s+\\n");

        for(String instruction : instructionArray)
        {
            Step step = parseStep(instruction, recipeBuilder);
            recipeBuilder.addStep(step);
        }
        return recipeBuilder.build();
    }

    private Step parseStep(String instruction, Recipe.RecipeBuilder recipeBuilder)
    {
        Step.StepBuilder stepBuilder = new Step.StepBuilder().setInstruction(instruction);
        for(InstructionParser parser : parsers)
        {
            parser.parse(instruction, stepBuilder, recipeBuilder);
        }
        return stepBuilder.build();
    }

    private List<InstructionParser> initializeParser()
    {
        List<InstructionParser> parsers = new ArrayList<>();
        parsers.add(new IngredientParser());
        parsers.add(new CookwareParser());
        parsers.add(new DurationParser());
        return parsers;
    }
}