package GUI;

import javax.swing.*;
import java.awt.*;


public class HomePanel extends JPanel {
    private static int servings = 1;

    public HomePanel(MainContentPanel mainContentPanel) {
        setName("Home");
        JLabel servingsLabel = new JLabel("Specify number of servings:");

        JTextField textField = new JTextField(6);
        JButton submitButton = new JButton("Submit");
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Add the label centered
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3; // span across 3 columns (if you want to center the label across the full width)
        gbc.anchor = GridBagConstraints.CENTER;
        add(servingsLabel, gbc);

        // Add the text field on the left
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1; // Take only 1 column
        gbc.anchor = GridBagConstraints.WEST;
        add(textField, gbc);

        // Add the submit button on the right
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 1; // Take only 1 column
        gbc.anchor = GridBagConstraints.EAST;
        add(submitButton, gbc);

        // Add an ActionListener for validation
        submitButton.addActionListener(e -> {
            String input = textField.getText();
            try {
                servings = Integer.parseInt(input);
                if (servings <= 0) {
                    JOptionPane.showMessageDialog(this, "Please enter a positive number.");
                } else {
                    JOptionPane.showMessageDialog(this, "You selected " + servings + " servings.");
                    mainContentPanel.changeRecipesFactor(servings);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter a number.");
            }
        });
    }


    public static int getServings() {
        return servings;
    }

}
