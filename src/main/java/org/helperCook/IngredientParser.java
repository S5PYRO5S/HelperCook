package org.helperCook;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parser for extracting ingredient the ingredient unit from an instruction
 */

public class IngredientParser implements InstructionParser
{
    /**
     * Identifies ingredient and unit from a given regex,
     * creates the 2 objects and adds them to the builder objects
     */
    @Override
    public void parse(String instruction, Step.StepBuilder stepBuilder, Recipe.RecipeBuilder recipeBuilder)
    {
        Matcher ingredientMatcher = Pattern.compile(RegexConstants.INGREDIENT_REGEX).matcher(instruction);
        while (ingredientMatcher.find())
        {
            // e.g. @αυγά{3}
            String ingredientName = ingredientMatcher.group(1); // αυγά

            // Defaults quantity to 1 if not specified
            double quantity = ingredientMatcher.group(2) != null ? Double.parseDouble(ingredientMatcher.group(2)) : 1;//3

            //for the example it saves an empty string
            String unitName = ingredientMatcher.group(3) != null ? ingredientMatcher.group(3) : ""; //for the example

            Unit unit = UnitFactory.create(quantity, unitName);
            Ingredient ingredient = new Ingredient(ingredientName);

            stepBuilder.addIngredient(ingredient, unit);
            recipeBuilder.addIngredient(ingredient, unit);
        }
    }
}