package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * side panel to hold additional button/s (if they are to be implemented)
 */
public class LeftSidePanel extends JPanel
{
    //private final JPanel panel;
    private final MainFrame mainFrame;
    private int splitPaneLocationBeforeClose;

    public LeftSidePanel(MainFrame mainFrame, JSplitPane splitPane)
    {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());
        splitPaneLocationBeforeClose = GUIConstants.DEFAULT_DIVIDER_LOCATION;

        JButton folderToggleButton = initToggleButton(splitPane, "icons/folder_icon.png");//sidebar toggle button
        add(folderToggleButton, BorderLayout.NORTH); //add button to panel
    }

    // ===== button creation =====
    private JButton initToggleButton(JSplitPane splitPane, String iconPath)
    {
        JButton button = Utils.createButton(iconPath);
        button.addActionListener(e -> toggleSidebar(splitPane));
        return button;
    }
    // ===== folder button action =====
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
    public JPanel getPanel()
    {
        return this;
    }
}
