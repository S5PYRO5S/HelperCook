package GUI;

import javax.swing.*;
import java.io.File;
import java.util.List;

public class GUIMain
{
    public static void startGUI() {SwingUtilities.invokeLater(GUIMain::run);}
    private static void run() {MainFrame mainFrame = new MainFrame();}
}
