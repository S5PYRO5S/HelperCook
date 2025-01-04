package GUI;

import javax.swing.*;

import java.awt.*;
import java.net.URL;

import static GUI.GUIConstants.*;

public class Utils
{
    private static ImageIcon resizeIcon(URL iconURL, int width, int height)
    {
        ImageIcon icon = new ImageIcon(iconURL);
        Image image = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }

    public static JButton createButton(String iconPath)
    {
        URL iconURL = Utils.class.getClassLoader().getResource(iconPath);
        if (iconURL == null)
        {
            System.err.println("Icon not found: " + iconPath);
            return new JButton("?");
        }
        else
        {
            ImageIcon icon = resizeIcon(iconURL, ICON_WIDTH, ICON_HEIGHT);
            JButton button = new JButton(icon);
            button.setBorderPainted(false);
            button.setFocusPainted(false);
            button.setContentAreaFilled(false);
            return button;
        }
    }

    public static ImageIcon getImageIcon(String iconPath)
    {
        URL iconURL = LeftSidePanel.class.getClassLoader().getResource(iconPath);
        if (iconURL != null)
        {
            return resizeIcon(iconURL, ICON_WIDTH, ICON_HEIGHT);
        }
        return null;
    }
}
