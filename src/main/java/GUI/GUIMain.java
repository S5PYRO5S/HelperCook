package GUI;


import org.helperCook.Recipe;
import org.helperCook.RecipeLoader;
import org.helperCook.Step;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;



public class GUIMain {

    private int step = 0;
    private JTextArea textArea;
    private List<Recipe> recipes;
    private List<Step> steps;

    public GUIMain(List<File> files) {
        makeRecipes(files);
    }

    private void makeRecipes(List<File> files) {
        RecipeLoader recipeLoader = new RecipeLoader();
        try {
            recipes = recipeLoader.loadRecipes(files);
        } catch (IOException e) {
            System.err.println("Unexpected Error:" + e.getMessage());
        }
    }

    public void startGUI() {
        // Create the frame
        MainFrame frame = new MainFrame();

        // Create buttons
        JButton prevButton = createButton("Prev Step", "Press this button to show the previous step");
        JButton timerButton = createButton("Start Timer", "Press this button to start the timer");
        JButton nextButton = createButton("Next Step", "Press this button to show the next step");

        // Add buttons to Panel
        JPanel buttonPanel = new JPanel();

        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(prevButton);
        buttonPanel.add(timerButton);
        buttonPanel.add(nextButton);
        buttonPanel.setBackground(Color.DARK_GRAY);

        // Initially update button states based on the step
        updateButtonState(prevButton, nextButton, timerButton);

        // Initialize the step label
        steps = recipes.getFirst().getSteps();
        textArea = new JTextArea("Step " + (step + 1) + ".\n" + steps.get(step).getInstruction());
        textArea.setEditable(false);
        textArea.setBackground(Color.DARK_GRAY);
        textArea.setForeground(Color.WHITE);

        textArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));


        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("Dialog", Font.PLAIN, 15));
        textPanel.add(new JScrollPane(textArea));
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, textPanel, buttonPanel);
        splitPane.setResizeWeight(1);
        splitPane.setDividerSize(0);  // not clickable divider
        frame.setMinimumSize(new Dimension(500, 375));
        frame.addComp(splitPane);

        // Action for Start button
        timerButton.addActionListener(e -> {
            timerButton.setEnabled(false); // Disable the start button after it's clicked
            // visible cue that the button cannot be pressed
            timerButton.setBackground(new Color(0xe0e0e0));
            timerButton.setForeground(new Color(0x8a8a8a));
        });

        // Action for Prev button
        prevButton.addActionListener(e -> {
            step--; // Decrement step
            updateStepLabel();
            updateButtonState(prevButton, nextButton, timerButton);
        });

        // Action for Next button
        nextButton.addActionListener(e -> {
            step++; // Increment step
            updateStepLabel();
            updateButtonState(prevButton, nextButton, timerButton);
        });


    }

    // Method to enable/disable buttons based on the current step
    private void updateButtonState(JButton prevButton, JButton nextButton, JButton timerButton) {
        // Use SwingUtilities to ensure UI updates happen on the EDT (Event Dispatch Thread)
        SwingUtilities.invokeLater(() -> {
            // Enable Prev button otherwise
            if (step==0){
                prevButton.setBackground(new Color(0xd3d3d3));
                prevButton.setForeground(new Color(0x8a8a8a));
                prevButton.setEnabled(false);
            } else {
                prevButton.setBackground(new Color(0xF5F5DC));
                prevButton.setForeground(new Color(0x333333));
                prevButton.setEnabled(true);
            }
            if(step==steps.size()-1){
                nextButton.setBackground(new Color(0xd3d3d3));
                nextButton.setForeground(new Color(0x8a8a8a));
                nextButton.setEnabled(false);
            } else {
                nextButton.setBackground(new Color(0xF5F5DC));
                nextButton.setForeground(new Color(0x333333));
                nextButton.setEnabled(true);
            }
            if (steps.get(step).getDuration() == null) {
                timerButton.setBackground(new Color(0xe0e0e0));
                timerButton.setForeground(new Color(0x333333));
                timerButton.setEnabled(false);
            } else {
                timerButton.setEnabled(true);
                timerButton.setBackground(new Color(0xff7f50));
                timerButton.setForeground(new Color(0x333333));
            }
        });
    }

    // Method to update the step label
    private void updateStepLabel() {
        // Use SwingUtilities to ensure UI updates happen on the EDT (Event Dispatch Thread)
        SwingUtilities.invokeLater(() -> textArea.setText("Step " + (step + 1) + ".\n" + steps.get(step).getInstruction()));
    }

    private JButton createButton(String label, String toolTip) {
        JButton button = new JButton(label);
        button.setFont(new Font("Dialog", Font.BOLD, 12));
        button.setFocusPainted(false);
        button.setFocusable(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(true);
        button.setToolTipText(toolTip);
        button.setOpaque(true);
        button.setPreferredSize(new Dimension(100, 27));
        return button;
    }


    private static class MainFrame extends JFrame {
        public MainFrame() {
            this.setTitle("Helper Cook");
            this.setSize(800, 600);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setLocationRelativeTo(null);
            this.setVisible(true);
        }

        public void addComp(Component component) {
            super.add(component);
            this.revalidate();
            this.repaint();
        }


    }
}
