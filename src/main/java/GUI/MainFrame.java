package GUI;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URL;

/**
 * Represents the main application window for the GUI program
 * It sets up the overall windows layout.
 */
class MainFrame extends JFrame
{
    private final MainContentPanel mainContentPanel;

    /**
     * Constructor
     * Initializes the components of the application
     *
     * @param files a list of files to be displayed in the TreePanel(if not null).
     */
    public MainFrame(java.util.List<File> files)
    {
        setTitle("Helper Cook");

        mainContentPanel = new MainContentPanel();
        TreePanel treePanel = new TreePanel(this, files);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treePanel.getPanel(), mainContentPanel.getPanel());
        LeftSidePanel leftSidePanel = new LeftSidePanel(this, splitPane);

        //set app icon
        URL appIconURL = MainFrame.class.getClassLoader().getResource("icons/app_icon.png");
        if (appIconURL != null)
        {
            ImageIcon appIcon = new ImageIcon(appIconURL);
            setIconImage(appIcon.getImage());
        }

        //frame config
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());
        splitPane.setDividerLocation(GUIConstants.DEFAULT_DIVIDER_LOCATION);
        splitPane.setDividerSize(GUIConstants.DIVIDER_SIZE_VISIBLE);
        splitPane.setContinuousLayout(true);

        //add components to frame
        add(splitPane, BorderLayout.CENTER);
        add(leftSidePanel.getPanel(), BorderLayout.WEST);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * getter for mainContentPanel
     * @return the instance of that panel
     */
    public MainContentPanel getMainContentPanel()
    {
        return mainContentPanel;
    }
}