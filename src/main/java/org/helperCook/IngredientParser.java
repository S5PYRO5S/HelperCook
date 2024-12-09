package org.helperCook;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IngredientParser implements InstructionParser
{

    @Override
    public void parse(String instruction, Step.StepBuilder stepBuilder, Recipe.RecipeBuilder recipeBuilder)
    {
        Matcher ingredientMatcher = Pattern.compile(RegexConstants.INGREDIENT_REGEX).matcher(instruction);
        while (ingredientMatcher.find())
        {
            String ingredientName = ingredientMatcher.group(1);

            double quantity = ingredientMatcher.group(2) != null ? Double.parseDouble(ingredientMatcher.group(2)) : 1;
            String unitName = ingredientMatcher.group(3) != null ? ingredientMatcher.group(3) : "";
            Unit unit = UnitFactory.create(quantity, unitName);

            System.out.println(ingredientName + unitName + unit.toString() +" "+ unit.getUnitType()); //debug

            Ingredient ingredient = new Ingredient(ingredientName);
            stepBuilder.addIngredient(ingredient, unit);
            recipeBuilder.addIngredient(ingredient, unit);
        }
    }
}