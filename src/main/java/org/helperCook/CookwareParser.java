package org.helperCook;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parser for extracting cookware from an instruction
 */

public class CookwareParser implements InstructionParser
{
    /**
     * Identifies cookware from a given regex and
     * creates a cookware object and updates the builder objects with the new cookware
     */

    @Override
    public void parse(String instruction, Step.StepBuilder stepBuilder, Recipe.RecipeBuilder recipeBuilder)
    {
        Matcher cookwareMatcher = Pattern.compile(RegexConstants.COOKWARE_REGEX).matcher(instruction);
        while (cookwareMatcher.find())
        {
            String cookwareName = "";// string name init
            //check if the name if more than 1 word
            if(cookwareMatcher.group(1) != null) cookwareName = cookwareMatcher.group(1);
            else if(cookwareMatcher.group(2) != null) cookwareName = cookwareMatcher.group(2);

            Cookware cookware = new Cookware(cookwareName);
            stepBuilder.addCookware(cookware);
            recipeBuilder.addCookware(cookware);
        }
    }
}
