package GUI;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class MainContentPanel extends JPanel
{
    private JTabbedPane tabbedPane;

    public MainContentPanel()
    {
        setLayout(new BorderLayout());
        tabbedPane = new JTabbedPane();

        //Home tab
        JPanel homePanel = new HomePanel();
        //add home panel to tabbedPane
        tabbedPane.addTab("Home", homePanel);

        //add tabbedPane to the main panel
        add(tabbedPane, BorderLayout.CENTER);
    }

    //method to add settings tab
    public void addSettingsTab()
    {
        //create new SettingsPanel
        SettingsPanel settingsPanel = new SettingsPanel();

        //add the settings panel to a new tab
        JPanel tabPanel = new JPanel(new BorderLayout());
        tabPanel.add(settingsPanel, BorderLayout.CENTER);

        //add close button
        CloseButton closeButton = new CloseButton(tabbedPane, tabPanel);

        //create a panel to hold the close button and the content
        JPanel tabTitlePanel = new JPanel(new BorderLayout());
        tabTitlePanel.add(new JLabel("Settings"), BorderLayout.CENTER);
        tabTitlePanel.add(closeButton, BorderLayout.EAST);

        //add the tab to the tabbedPane
        tabbedPane.addTab(null, tabPanel);
        tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, tabTitlePanel);
    }

    public void addFileTab(File file)
    {
        // Create the content panel for the file tab
        JPanel filePanel = new JPanel(new BorderLayout());
        filePanel.add(new JLabel("File: " + file.getName()), BorderLayout.NORTH);  // Display file name

        // Create two buttons inside the tab (TODO functionality for now)
        JButton button1 = new JButton("Button 1 (TODO)");
        JButton button2 = new JButton("Button 2 (TODO)");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(button1);
        buttonPanel.add(button2);

        // Add the buttons to the file panel
        filePanel.add(buttonPanel, BorderLayout.CENTER);

        // Add close button functionality to the tab
        CloseButton closeButton = new CloseButton(tabbedPane, filePanel);

        // Create a panel to hold the close button and the file name label
        JPanel tabTitlePanel = new JPanel(new BorderLayout());
        tabTitlePanel.add(new JLabel(file.getName()), BorderLayout.CENTER);
        tabTitlePanel.add(closeButton, BorderLayout.EAST);

        // Add the file panel as a tab in the tabbed pane
        tabbedPane.addTab(null, filePanel);
        tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, tabTitlePanel);
    }

    public JPanel getPanel()
    {
        return this;
    }
}
