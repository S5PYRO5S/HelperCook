package org.helperCook;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parser for extracting duration from an instruction
 */

public class DurationParser implements InstructionParser
{
    /**
     * Identifies duration from a given regex and
     * creates a duration object and updates the builder objects with the new duration
     */

    @Override
    public void parse(String instruction, Step.StepBuilder stepBuilder, Recipe.RecipeBuilder recipeBuilder)
    {
        Matcher timeMatcher = Pattern.compile(RegexConstants.TIME_REGEX).matcher(instruction);
        while(timeMatcher.find())
        {
            //(e.g. ~{1.5%hours})
            double time = Double.parseDouble(timeMatcher.group(1)); // 1.5
            String unit = timeMatcher.group(2); //hours
            Duration duration = new Duration(time, unit);
            stepBuilder.setDuration(duration);
            recipeBuilder.addDuration(duration);
        }
    }
}