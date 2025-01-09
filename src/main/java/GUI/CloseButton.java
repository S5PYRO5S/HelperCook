package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CloseButton extends JButton
{
    private JTabbedPane tabbedPane;
    private JPanel tabPanel;
    private MainContentPanel mainContentPanel;

    public CloseButton(final JTabbedPane tabbedPane, final JPanel tabPanel, MainContentPanel mainContentPanel)
    {
        this.tabbedPane = tabbedPane;
        this.tabPanel = tabPanel;
        this.mainContentPanel = mainContentPanel;

        setIcon(Utils.getImageIcon("icons/close_tab_icon.png"));
        setContentAreaFilled(false);
        setFocusPainted(false);
        setPreferredSize(new Dimension(20, 20));

        addActionListener(e -> closeTab());
    }

    private void closeTab()
    {
        //remove the tab from the tabbedPane
        int index = tabbedPane.indexOfComponent(tabPanel);
        if (index != -1) tabbedPane.removeTabAt(index);
        //remove the tab from the openTabs map (if exists)
        if (mainContentPanel != null) mainContentPanel.removeTab("Settings");
    }
}