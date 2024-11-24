package org.example;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class RecipeLoader
{
    private final String[] instructionArray;
    private final Recipe.RecipeBuilder recipeBuilder;
    private final List<InstructionParser> parsers;

    public RecipeLoader(File recipeFile) throws IOException
    {
        this.recipeBuilder = new Recipe.RecipeBuilder();
        this.recipeBuilder.setName(recipeFile.getName().replaceAll(RegexConstants.NAME_REGEX, "$1"));
        String rawFileContent = FileReaderUtil.readFileContent(recipeFile);
        this.instructionArray = rawFileContent.split("\\n\\s+\\n");
        this.parsers = initializeParser();
    }

    public RecipeLoader(String recipeFilePath) throws IOException
    {
        this(new File(recipeFilePath));
    }

    public Recipe loadRecipe()
    {
        for(String instruction : instructionArray)
        {
            Step step = parseStep(instruction);
            recipeBuilder.addStep(step);
        }
        return recipeBuilder.build();
    }

    private Step parseStep(String instruction)
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