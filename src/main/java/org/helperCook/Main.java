package org.helperCook;

import java.io.File;
import java.io.IOException;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        RecipeLoader loader = new RecipeLoader();
        File recipeFile = new File("Recipe1.txt");
        Recipe recipe = loader.loadRecipe(recipeFile);
        recipe.printRecipe();
//
//
//        Duration secDur1 = new Duration(30, "seconds");
//
//        Duration dur1 = new Duration(30, "minutes");
//        System.out.println(dur1.toString());
//
//        Duration dur2 = new Duration(60, "minutes");
//        System.out.println(dur2.toString());
//
//        Duration dur3 = new Duration(120, "minutes");
//        System.out.println(dur3.toString());
//
//        Duration dur4 = new Duration(30, "minutes");
//        dur4 = dur4.add(dur3).add(dur1).add(secDur1).add(secDur1);
//        System.out.println(dur4.toString());



    }
}