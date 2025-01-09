package GUI;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class MainContentPanel extends JPanel
{
    private final JTabbedPane tabbedPane;
    private final Map<String, JPanel> openTabs;

    public MainContentPanel()
    {
        setLayout(new BorderLayout());
        tabbedPane = new JTabbedPane();
        openTabs = new HashMap<>();

        //Home tab
        JPanel homePanel = new HomePanel();
        //add home panel to tabbedPane
        tabbedPane.addTab("Home", homePanel);

        //add tabbedPane to the main panel
        add(tabbedPane, BorderLayout.CENTER);
    }

    private JPanel createTabWithCloseButton(JPanel contentPanel, String tabTitle)
    {
        CloseButton closeButton = new CloseButton(tabbedPane, contentPanel, this);

        JPanel tabTitlePanel = new JPanel(new BorderLayout());
        tabTitlePanel.add(new JLabel(tabTitle), BorderLayout.CENTER);
        tabTitlePanel.add(closeButton, BorderLayout.EAST);
        return tabTitlePanel;
    }

    //method to add settings tab
    public void addSettingsTab()
    {
        //if settings already open don't reopen
        if (containsTab("Settings"))
        {
            int index = tabbedPane.indexOfComponent(openTabs.get("Settings"));
            tabbedPane.setSelectedIndex(index);
            return;
        }

        //create new SettingsPanel
        SettingsPanel settingsPanel = new SettingsPanel();

        //add the settings panel to a new tab
        JPanel tabPanel = new JPanel(new BorderLayout());
        tabPanel.add(settingsPanel, BorderLayout.CENTER);

        //create a panel to hold the close button and the content
        JPanel tabTitlePanel = createTabWithCloseButton(tabPanel, "Settings");

        //add the tab to the tabbedPane
        tabbedPane.addTab(null, tabPanel);
        tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, tabTitlePanel);

        //save the settings tab
        openTabs.put("Settings", tabPanel);

        //select the tab
        tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);
    }

    public void addFileTab(File file)
    {
        //check if the file tab is already open
        if (containsTab(file.getName()))
        {
            int index = tabbedPane.indexOfComponent(openTabs.get(file.getName()));
            tabbedPane.setSelectedIndex(index);
            return;
        }

        //create the content panel for the file tab
        JPanel filePanel = new JPanel(new BorderLayout());
        filePanel.add(new JLabel("File: " + file.getName()), BorderLayout.NORTH);  // Display file name

        // Create two buttons inside the tab (TODO functionality for now)
        JButton button1 = new JButton("Button 1 (TODO)");
        JButton button2 = new JButton("Button 2 (TODO)");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(button1);
        buttonPanel.add(button2);

        //add the buttons to the file panel
        filePanel.add(buttonPanel, BorderLayout.CENTER);

        //add close button functionality to the tab
        CloseButton closeButton = new CloseButton(tabbedPane, filePanel, this);

        //create a panel to hold the close button and the file name label
        JPanel tabTitlePanel = new JPanel(new BorderLayout());
        tabTitlePanel.add(new JLabel(file.getName()), BorderLayout.CENTER);
        tabTitlePanel.add(closeButton, BorderLayout.EAST);

        //add the file panel as a tab in the tabbed pane
        tabbedPane.addTab(null, filePanel);
        tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, tabTitlePanel);

        //save the tab in the openTabs map using the File object as the key
        openTabs.put(file.getName(), filePanel);

        //select tab
        tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);  //select the tab
    }

    public void removeTab(String tabTitle)
    {
        JPanel tabPanel = openTabs.remove(tabTitle);  //remove tab name from map
        if (tabPanel != null)
        {
            int index = tabbedPane.indexOfComponent(tabPanel);
            if (index != -1) tabbedPane.removeTabAt(index); //remove tab from tabbedPane
        }
    }

    private boolean containsTab(String tabTitle)
    {
        return openTabs.containsKey(tabTitle);
    }

    public JPanel getPanel()
    {
        return this;
    }
}
