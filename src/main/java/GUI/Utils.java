package GUI;

import javax.swing.*;

import java.awt.*;
import java.net.URL;

import static GUI.GUIConstants.*;

/**
 * Utility class with helper methods for GUI components
 */
public class Utils
{
    /**
     * Resizes an icon
     *
     * @param iconURL  URL of the icon to resize
     * @param width    target width
     * @param height   target height
     * @return         resized ImageIcon
     */
    private static ImageIcon resizeIcon(URL iconURL, int width, int height)
    {
        ImageIcon icon = new ImageIcon(iconURL);
        Image image = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }

    /**
     * Creates a JButton with an icon.
     *
     * @param iconPath path to the icon
     * @return  a JButton with the icon
     */
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

    /**
     * Returns a resized ImageIcon
     * @param iconPath path to the icon.
     * @return resized ImageIcon or null (if the icon cannot be found)
     */
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
