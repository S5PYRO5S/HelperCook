package org.helperCook;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InvalidCommandLineArgumentException {

















        

        File recipe = new File (args[0]);
        System.out.println("1");
        RecipeLoader recipeLoader = new RecipeLoader(recipe);
        //recipeLoader.loadRecipe().print();

        List<Step> steps = recipeLoader.loadRecipe().getSteps();
        for (Step step : steps) {
            System.out.println(step.getInstruction()+ "\n");
            Scanner scanner = new Scanner(System.in);

            scanner.nextLine();
        }
        ArgumentChecker checkArguments = new ArgumentChecker( args, "-list");
        if (args.length == 0) {
            throw new InvalidCommandLineArgumentException("No command line argument(s)");
        }

    }
}
