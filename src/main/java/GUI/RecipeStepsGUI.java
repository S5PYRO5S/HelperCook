package GUI;


import org.helperCook.Recipe;
import org.helperCook.Step;

import javax.swing.*;
import java.awt.*;
import java.util.List;


public class RecipeStepsGUI {

    private int currentStep = 0;
    private List<Step> steps;
    private JTextArea textArea;
    private JPanel buttonPanel;
    private JSplitPane buttonNCountNTextPane;
    private JSplitPane buttonNCountPane;
    private final Recipe recipe;


    public RecipeStepsGUI(Recipe recipe) {
        this.recipe = recipe;
        makeStepsPanel();

    }

    private void makeStepsPanel() {


        // Create buttons
        JButton prevButton = new JButton("Previous Step");
        prevButton.setToolTipText("Go to the previous step");
        JButton timerButton = new JButton("Start Timer");
        timerButton.setToolTipText("Start the timer for the current step");
        JButton nextButton = new JButton("Next Step");
        nextButton.setToolTipText("Go to the next step");
        JCheckBox checkBox = new JCheckBox("Mark as done");

        // Add buttons to Panel
        buttonPanel = new JPanel();

        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(prevButton);
        buttonPanel.add(timerButton);
        buttonPanel.add(nextButton);
        buttonPanel.add(checkBox);

        // Initially update button states based on the step
        updateButtonState(prevButton, nextButton, timerButton, checkBox);

        // Initialize the step label
        steps = recipe.getSteps();
        textArea = new JTextArea("Step " + (currentStep + 1) + ".\n" + steps.get(currentStep).getInstruction());
        textArea.setEditable(false);


        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("Dialog", Font.PLAIN, 15));
        textPanel.add(new JScrollPane(textArea));

        JPanel countdownPanel = new JPanel();
        buttonNCountPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, buttonPanel, countdownPanel);
        countdownPanel.setVisible(false);
        buttonNCountPane.setDividerSize(0);  // not clickable divider
        buttonNCountPane.setResizeWeight(0.1);
        buttonNCountNTextPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, textPanel, buttonNCountPane);
        buttonNCountNTextPane.setResizeWeight(1);
        buttonNCountNTextPane.setDividerLocation(0.9);
        buttonNCountNTextPane.setDividerSize(0);  // not clickable divider



        // Action for Start button
        timerButton.addActionListener(e -> {
            timerButton.setEnabled(false); // Disable the start button after it's clicked
            // visible cue that the button cannot be pressed again
//          StepCountdown stepCountdown = new StepCountdown();
//          stepCountdown.startCountdown(steps.get(currentStep), currentStep, buttonNTextPane, timerButton, this);
            SwingUtilities.invokeLater(() -> {
                countdownPanel.setVisible(true);
            });
        });


        // Action for Prev button
        prevButton.addActionListener(e -> {
            countdownPanel.setVisible(false);
            currentStep--; // Decrement step
            updateStepLabel();
            updateButtonState(prevButton, nextButton, timerButton, checkBox);
        });

        // Action for Next button
        nextButton.addActionListener(e -> {
            countdownPanel.setVisible(false);
            currentStep++; // Increment step
            updateStepLabel();
            updateButtonState(prevButton, nextButton, timerButton, checkBox);
            checkBox.setSelected(false); // Uncheck the checkbox
        });
        // Action for checkbox
        checkBox.addActionListener(e -> updateButtonState(prevButton, nextButton, timerButton, checkBox));

    }

    // Method to enable/disable buttons based on the current step
    private void updateButtonState(JButton prevButton, JButton nextButton, JButton timerButton, JCheckBox checkBox) {
        // Use SwingUtilities to ensure UI updates happen on the EDT (Event Dispatch Thread)
        SwingUtilities.invokeLater(() -> {
            timerButton.setEnabled(steps.get(currentStep).getDuration() != null);
            prevButton.setEnabled(currentStep != 0);
            // Enable the next button only if the checkbox is checked and the current step is not the last step
            nextButton.setEnabled((checkBox.isSelected() && currentStep != steps.size() - 1));
        });
    }


    // Method to update the step label
    private void updateStepLabel() {
        // Use SwingUtilities to ensure UI updates happen on the EDT (Event Dispatch Thread)
        SwingUtilities.invokeLater(() -> textArea.setText("Step " + (currentStep + 1) + ".\n" + steps.get(currentStep).getInstruction()));
    }


    public JSplitPane getSplitPane() {
        return buttonNCountNTextPane;
    }


}
