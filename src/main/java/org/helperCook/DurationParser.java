package org.helperCook;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DurationParser implements InstructionParser
{
    @Override
    public void parse(String instruction, Step.StepBuilder stepBuilder, Recipe.RecipeBuilder recipeBuilder)
    {
        Matcher timeMatcher = Pattern.compile(RegexConstants.TIME_REGEX).matcher(instruction);
        while(timeMatcher.find())
        {
            double time = Double.parseDouble(timeMatcher.group(1));
            String unit = timeMatcher.group(2);
            Duration duration = new Duration(time, unit);
            stepBuilder.setDuration(duration);
            recipeBuilder.addDuration(duration);
        }
    }
}
