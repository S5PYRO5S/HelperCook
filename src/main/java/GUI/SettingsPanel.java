package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class SettingsPanel extends JPanel
{
    private final CardLayout cardLayout;
    private final HashMap<String, JPanel> categoryPanels;

    public SettingsPanel()
    {
        setLayout(new BorderLayout());

        //set up left panel with a JList of categories
        JPanel leftPanel = new JPanel(new BorderLayout());
        String[] categories = {"Appearance"};
        JList<String> categoryList = new JList<>(categories);
        categoryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //add the list to a scroll pane
        leftPanel.add(new JScrollPane(categoryList), BorderLayout.CENTER);

        //create right panel with card layout
        cardLayout = new CardLayout();
        JPanel rightPanel = new JPanel(cardLayout);

        //hashmap that contains string as key and panel as value
        categoryPanels = new HashMap<>();
        categoryPanels.put("Appearance", createAppearancePanel());

        //add category panels to right panel
        for (String category : categories) rightPanel.add(categoryPanels.get(category), category);

        //add listener for category selection to switch panels
        categoryList.addListSelectionListener(e ->
        {
            String selectedCategory = categoryList.getSelectedValue();
            if (selectedCategory != null) cardLayout.show(rightPanel, selectedCategory);
        });

        //set default to the first category
        categoryList.setSelectedIndex(0);

        //create JSplitPane with left and right panel
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        splitPane.setContinuousLayout(true);
        splitPane.setDividerSize(8);
        splitPane.setDividerLocation(150);

        add(splitPane, BorderLayout.CENTER);
    }

    private JPanel createAppearancePanel()
    {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel label = new JLabel("Appearance Settings");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label, BorderLayout.CENTER);

        //light and dark mode buttons
        JRadioButton lightModeButton = new JRadioButton("Light Mode");
        JRadioButton darkModeButton = new JRadioButton("Dark Mode");

        //group the buttons
        ButtonGroup group = new ButtonGroup();
        group.add(lightModeButton);
        group.add(darkModeButton);

        //default selection
        lightModeButton.setSelected(true);

        //add listeners to the buttons
        lightModeButton.addActionListener(e -> switchToLightMode());
        darkModeButton.addActionListener(e -> switchToDarkMode());

        //panel to hold the buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(lightModeButton);
        buttonPanel.add(darkModeButton);

        //add button panel to appearance panel
        panel.add(buttonPanel, BorderLayout.CENTER);

        return panel;
    }

    private void switchToLightMode()
    {
        System.out.println("Switched to Light Mode");
        //TODO
    }

    private void switchToDarkMode()
    {
        System.out.println("Switched to Dark Mode");
        //TODO
    }
}
