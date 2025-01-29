package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Button used to close tabs in the tabbed pane
 */
public class CloseButton extends JButton
{
    private final JTabbedPane tabbedPane;
    private final JPanel tabPanel;
    private final MainContentPanel mainContentPanel;
    private final String tabTitle;

    /**
     * Constructor
     * Sets up the button
     *
     * @param tabbedPane        The JTabbedPane containing the tab
     * @param tabPanel          The panel associated with the tab
     * @param mainContentPanel  The MainContentPanel managing the tab
     * @param tabTitle          The title of the tab
     */
    public CloseButton(final JTabbedPane tabbedPane, final JPanel tabPanel, MainContentPanel mainContentPanel, String tabTitle)
    {
        this.tabbedPane = tabbedPane;
        this.tabPanel = tabPanel;
        this.mainContentPanel = mainContentPanel;
        this.tabTitle = tabTitle;

        setIcon(Utils.getImageIcon("icons/close_tab_icon.png"));
        setContentAreaFilled(false);
        setFocusPainted(false);
        setPreferredSize(new Dimension(20, 20));

        addActionListener(e -> closeTab());
    }

    /**
     * Action for the button
     */
    private void closeTab()
    {
        //remove the tab from the tabbedPane
        int index = tabbedPane.indexOfComponent(tabPanel);
        if (index != -1) tabbedPane.removeTabAt(index);
        //remove the tab from the openTabs map (if exists)
        if (mainContentPanel != null) mainContentPanel.removeTab(tabTitle);
    }
}