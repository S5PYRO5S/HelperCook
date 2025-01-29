package GUI;

import javax.swing.*;
import java.awt.*;


public class HomePanel extends JPanel {
    private static int servings = 1;

    public HomePanel(MainContentPanel mainContentPanel) {
        setName("Home");

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Add the label centered
        JLabel servingsLabel = new JLabel("Specify number of servings:");
        servingsLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3; // span across 3 columns (if you want to center the label across the full width)
        gbc.anchor = GridBagConstraints.CENTER;
        add(servingsLabel, gbc);

        // Add the text field on the left
        JTextField textField = new JTextField(10);
        textField.setPreferredSize(new Dimension(100, 30));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1; // Take only 1 column
        gbc.anchor = GridBagConstraints.WEST;
        add(textField, gbc);

        // Add the submit button on the right
        JButton submitButton = new JButton("Submit");
        submitButton.setPreferredSize(new Dimension(100, 30));
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 1; // Take only 1 column
        gbc.anchor = GridBagConstraints.EAST;
        add(submitButton, gbc);

        // Add a label for displaying current amount of servings
        JLabel currentServingsLabel = new JLabel("Current servings: " + servings);
        currentServingsLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        add(currentServingsLabel, gbc);


        // Add an ActionListener for validation
        submitButton.addActionListener(e -> {
            String input = textField.getText();
            try {
                servings = Integer.parseInt(input);
                if (servings <= 0) {
                    JOptionPane.showMessageDialog(this, "Please enter a positive integer.");
                    return;
                }

                if (servings > 100) {
                    JOptionPane.showMessageDialog(this, "Please enter a reasonable number of servings.");
                    return;
                }

                currentServingsLabel.setText("Current servings: " + servings); // Update the label
                mainContentPanel.changeRecipesFactor(servings); // Update the recipes factor

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter an integer.");
            }
        });
    }


    public static int getServings() {
        return servings;
    }

}
