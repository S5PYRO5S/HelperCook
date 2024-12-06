package org.helperCook;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        RecipeLoader loader = new RecipeLoader();
        File recipeFile1 = new File("RecipesFolder/pancakes.txt");
        File recipeFile2 = new File("RecipesFolder/cacio_e_pepe.txt");

        Recipe recipe1 = loader.loadRecipe(recipeFile1);
        Recipe recipe2 = loader.loadRecipe(recipeFile2);

//        recipe1.printRecipe();
        recipe2.printRecipe();
//        recipe2.printIngredients();

//        Duration sec1 = new Duration(30, "seconds");
//        Duration sec2 = new Duration(1, "minutes");
//        Duration sec3 = new Duration(2, "hours");
//        Duration sec1_2 = sec1.add(sec2);
//        System.out.println(sec1_2.toString());
//
//        Duration sec2_2 = sec1_2.add(sec3);
//        System.out.println(sec2_2.toString());

//        UnitImpl wUnit1 = new UnitImpl(500, "g", new Gram());
//        UnitImpl wUnit2 = new UnitImpl(1, "kg", new Kilogram());
//        UnitImpl tUnit1 = new UnitImpl(500, "hour", new Hour());
//
//        UnitImpl mUnit1 = new UnitImpl(1, "πρέζα", new MadeUpUnit("πρέζα"));
//        UnitImpl mUnit2 = new UnitImpl(1, "πρέζα", new MadeUpUnit("πρέζα"));
//        System.out.println(mUnit1.add(mUnit2).toString());
//
//        UnitImpl wUnit3 = (UnitImpl) tUnit1.add(wUnit2);
//        if(wUnit3 != null) System.out.println(wUnit3.toString());

//        UnitImpl unit1 = (UnitImpl) UnitFactory.create(2, "kg");
//        UnitImpl unit2 = (UnitImpl) UnitFactory.create(100, "g");
//        System.out.println(unit1.add(unit2).toString());
    }
}