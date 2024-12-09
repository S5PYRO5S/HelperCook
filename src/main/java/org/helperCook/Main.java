package org.helperCook;

import java.io.File;
import java.io.IOException;


public class Main
{
    public static void main(String[] args) throws IOException
    {
        RecipeLoader loader = new RecipeLoader();
        File recipeFile1 = new File("RecipesFolder/pancakes.txt");
        File recipeFile2 = new File("RecipesFolder/cacio_e_pepe.txt");

//        Recipe recipe1 = loader.loadRecipe(recipeFile1);
//        recipe1.printRecipe();
//        Recipe recipe2 = loader.loadRecipe(recipeFile2);
//        recipe2.printRecipe();

        Unit unit1 = UnitFactory.create(2, "kg"); // 2 kg
        Unit unit2 = UnitFactory.create(100, "gr"); // 100 g

        Unit combined = unit1.add(unit2); // Result in kg (calling unit)
        Unit combined2 = unit2.add(unit1); // Result in g (calling unit)

        System.out.println(combined.toString());
        System.out.println(combined2.toString());

        //Duration dur4 = dur1.add(dur2).add(dur3);
        // System.out.println(dur4.toString());
    }
}