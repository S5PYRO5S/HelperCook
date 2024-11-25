package org.helperCook;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {


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
        ArgumentChecker checkArguments = new ArgumentChecker( args, "-list"); // check to
        if (checkArguments.checkList()) {
            System.out.println("Shopping list: ");

        } else {
            try {
                throw new InvalidCommandLineArgumentException("Invalid command line argument");
            } catch (InvalidCommandLineArgumentException e) {

            }

        }

    }
}