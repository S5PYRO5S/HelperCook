/*
 * Project "helperCook" - final part of the project assignment for the course Object-Oriented "Programming 2"
 * Made by : it2023003, it2023052, it2023100
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package org.helperCook;

import GUI.GUIMain;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        List<File> files = null;
        String mode = "";
        int factor = 1;
        try {
            CheckArguments checkArguments = new CheckArguments();
            files = checkArguments.check(args);
            mode = checkArguments.getMode();
            factor = checkArguments.getServings();
        } catch (Exception e) {
            System.out.println(ColorConstants.ANSI_RED + "Error: " + e.getMessage() + ColorConstants.ANSI_RESET);
            DisplayUtils.printUsage();
            System.exit(1);
        }
        if (mode.equals("windows")) {
            GUIMain windowsMain = new GUIMain(files);

            windowsMain.startGUI();
        } else {
            RecipeCLI recipeCLI = new RecipeCLI();
            recipeCLI.runCLI(mode, files, factor);
        }
    }
}