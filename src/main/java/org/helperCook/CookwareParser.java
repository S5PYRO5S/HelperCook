package org.helperCook;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CookwareParser implements InstructionParser
{
    @Override
    public void parse(String instruction, Step.StepBuilder stepBuilder, Recipe.RecipeBuilder recipeBuilder)
    {
        Matcher cookwareMatcher = Pattern.compile(RegexConstants.COOKWARE_REGEX).matcher(instruction);
        while (cookwareMatcher.find())
        {
            String cookwareName = "";
            if(cookwareMatcher.group(1) != null) cookwareName = cookwareMatcher.group(1);
            else if(cookwareMatcher.group(2) != null) cookwareName = cookwareMatcher.group(2);

            //System.out.println(cookwareName);

            Cookware cookware = new Cookware(cookwareName);
            stepBuilder.addCookware(cookware);
            recipeBuilder.addCookware(cookware);
        }
    }
}
