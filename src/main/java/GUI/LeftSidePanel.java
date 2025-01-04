package GUI;

import javax.swing.*;
import java.awt.*;

public class LeftSidePanel
{
    private final JPanel panel;
    private final MainFrame mainFrame;

    public LeftSidePanel(MainFrame mainFrame, JSplitPane splitPane)
    {
        this.mainFrame = mainFrame;
        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Sidebar toggle button
        JButton folderToggleButton = initToggleButton(splitPane, "icons/folder_icon.png");

        // Setting button
        JButton settingButton = initSettingButton("icons/settings_icon.png");

        // Add buttons to panel
        panel.add(folderToggleButton, BorderLayout.NORTH);
        panel.add(settingButton, BorderLayout.SOUTH);
    }

    //button creation method
    private JButton initToggleButton(JSplitPane splitPane, String iconPath)
    {
        JButton button = Utils.createButton(iconPath);
        button.addActionListener(e -> toggleSidebar(splitPane));
        return button;
    }
    //button creation method
    private JButton initSettingButton(String iconPath)
    {
        JButton button = Utils.createButton(iconPath);
        button.addActionListener(e -> openSettings());
        return button;
    }

    //action
    private void toggleSidebar(JSplitPane splitPane)
    {
        if (splitPane.getLeftComponent().isVisible())
        {
            splitPane.getLeftComponent().setVisible(false);
            splitPane.setDividerSize(GUIConstants.DIVIDER_SIZE_HIDDEN); // Hide the divider
        }
        else
        {
            splitPane.getLeftComponent().setVisible(true);
            splitPane.setDividerSize(GUIConstants.DIVIDER_SIZE_VISIBLE); // Restore the divider size
            splitPane.setDividerLocation(GUIConstants.DEFAULT_DIVIDER_LOCATION);
        }
        panel.revalidate();
        panel.repaint();
    }

    //action
    private void openSettings()
    {
        //add a new settings tab to the MainContentPanel
        MainContentPanel mainContentPanel = mainFrame.getMainContentPanel();
        mainContentPanel.addSettingsTab();
    }

    public JPanel getPanel()
    {
        return panel;
    }
}
