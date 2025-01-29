package GUI;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Manages the main content area
 */
public class MainContentPanel extends JPanel
{
    private final JTabbedPane tabbedPane;
    private final Map<String, JPanel> openTabs;

    /**
     * Constructor
     * Initializes the tabbed pane and sets up the Home tab
     */
    public MainContentPanel()
    {
        setLayout(new BorderLayout());
        tabbedPane = new JTabbedPane();
        openTabs = new HashMap<>();

        JPanel homePanel = new HomePanel(this);  //Home tab
        tabbedPane.addTab("Home", homePanel);              //add home panel to tabbedPane
        add(tabbedPane, BorderLayout.CENTER);                   //add tabbedPane to the main panel
    }

    private JPanel createTabWithCloseButton(JPanel contentPanel, String tabTitle)
    {
        CloseButton closeButton = new CloseButton(tabbedPane, contentPanel, this, tabTitle);
        JPanel tabTitlePanel = new JPanel(new BorderLayout());
        tabTitlePanel.add(new JLabel(tabTitle), BorderLayout.CENTER);
        tabTitlePanel.add(closeButton, BorderLayout.EAST);
        return tabTitlePanel;
    }

    /**
     * Adds a new tab for the specified file, or selects the tab if it is already open
     *
     * @param file the file to add or open
     */
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
        SingleRecipeTabs singleRecipeTabs = new SingleRecipeTabs(file, HomePanel.getServings());
        filePanel.add(singleRecipeTabs);

        //add close button functionality to the tab
        CloseButton closeButton = new CloseButton(tabbedPane, filePanel, this, file.getName());

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

    /**
     * Removes a tab with the specified title
     *
     * @param tabTitle the title of the tab to remove
     */
    public void removeTab(String tabTitle)
    {
        JPanel tabPanel = openTabs.remove(tabTitle);  //remove tab name from map
        if (tabPanel != null)
        {
            int index = tabbedPane.indexOfComponent(tabPanel);
            if (index != -1) tabbedPane.removeTabAt(index); //remove tab from tabbedPane
        }
    }

    /**
     * Helper method to check if the tab with the specified title exists
     *
     * @param tabTitle the title to add
     * @return a boolean value (if it exists true, otherwise false)
     */
    private boolean containsTab(String tabTitle)
    {
        return openTabs.containsKey(tabTitle);
    }

    /**
     * getter for this panel
     * @return this panel
     */
    public JPanel getPanel()
    {
        return this;
    }

    /**
     * Changes the scaling factor for recipes in all tabs
     *
     * @param factor the scaling factor
     */
    public void changeRecipesFactor(int factor){
        for (String key : openTabs.keySet()) {
            SingleRecipeTabs singleRecipeTabs = (SingleRecipeTabs) openTabs.get(key).getComponent(0);
            singleRecipeTabs.changeFactor(factor);
        }
        repaint();
        revalidate();
    }
}
