package GUI;

import javax.swing.*;
import java.io.File;
import java.util.List;

public class GUIMain
{
    public static void startGUI(List<File> files)
    {
        SwingUtilities.invokeLater(() -> new MainFrame(files));
    }
}