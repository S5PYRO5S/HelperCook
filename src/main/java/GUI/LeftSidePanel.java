package GUI;

import javax.swing.*;
import java.awt.*;

public class LeftSidePanel extends JPanel
{
    //private final JPanel panel;
    private final MainFrame mainFrame;
    private int splitPaneLocationBeforeClose;

    public LeftSidePanel(MainFrame mainFrame, JSplitPane splitPane)
    {
        this.mainFrame = mainFrame;
        //panel = new JPanel();
        setLayout(new BorderLayout());
        splitPaneLocationBeforeClose = GUIConstants.DEFAULT_DIVIDER_LOCATION;

        // Sidebar toggle button
        JButton folderToggleButton = initToggleButton(splitPane, "icons/folder_icon.png");

        // Setting button
        JButton settingButton = initSettingButton("icons/settings_icon.png");

        // Add buttons to panel
        add(folderToggleButton, BorderLayout.NORTH);
        add(settingButton, BorderLayout.SOUTH);
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
        boolean isSidebarVisible = splitPane.getLeftComponent().isVisible();
        if (isSidebarVisible)
        {
            //save current divider location if not hidden
            if(splitPane.getDividerLocation() != 0){splitPaneLocationBeforeClose = splitPane.getDividerLocation();}
            splitPane.getLeftComponent().setVisible(false);             //hide sidebar
            splitPane.setDividerSize(GUIConstants.DIVIDER_SIZE_HIDDEN); //hide divider
        }
        else
        {
            //show sidebar and restore divider location
            splitPane.getLeftComponent().setVisible(true);
            splitPane.setDividerSize(GUIConstants.DIVIDER_SIZE_VISIBLE); // Restore the divider size
            splitPane.setDividerLocation(splitPaneLocationBeforeClose);
        }
        //refresh panel
        revalidate();
        repaint();
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
        return this;
    }
}
