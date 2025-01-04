package GUI;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class MainFrame extends JFrame
{
    private final MainContentPanel mainContentPanel;

    public MainFrame()
    {
        //instantiate app components
        setTitle("Helper Cook");
        TreePanel treePanel = new TreePanel(this);
        mainContentPanel = new MainContentPanel();

        //make splitPane with treePanel and mainContentPanel
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treePanel.getPanel(), mainContentPanel.getPanel());

        //make leftSidePanel with splitPane as parameter to be able to hide and show the treePanel
        LeftSidePanel leftSidePanel = new LeftSidePanel(this, splitPane);

        //load icon
        URL appIconURL = MainFrame.class.getClassLoader().getResource("icons/app_icon.png");
        if (appIconURL != null)
        {
            ImageIcon appIcon = new ImageIcon(appIconURL);
            setIconImage(appIcon.getImage());
        }

        // main frame options
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        //splitPane options
        splitPane.setDividerLocation(GUIConstants.DEFAULT_DIVIDER_LOCATION);
        splitPane.setDividerSize(GUIConstants.DIVIDER_SIZE_VISIBLE);
        splitPane.setContinuousLayout(true);

        //add components to the main frame
        add(splitPane, BorderLayout.CENTER);
        add(leftSidePanel.getPanel(), BorderLayout.WEST);

        //make frame appear to the screen
        setLocationRelativeTo(null);

        setVisible(true);
    }

    public MainContentPanel getMainContentPanel()
    {
        return mainContentPanel;
    }
}