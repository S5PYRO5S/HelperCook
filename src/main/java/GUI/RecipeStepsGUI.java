package GUI;


import org.helperCook.Recipe;
import org.helperCook.Step;

import javax.swing.*;
import java.awt.*;
import java.util.List;


public class RecipeStepsGUI extends JSplitPane {

    private int currentStep = 0;
    private List<Step> steps;
    private JButton timerButton;
    private JButton prevButton;
    private JButton nextButton;
    private JCheckBox checkBox;
    private JTextArea textArea;
    private final Recipe recipe;
    private boolean isTimerRunning;


    public RecipeStepsGUI(Recipe recipe) {
        this.recipe = recipe;
        makeStepsPanel();

    }

    private void makeStepsPanel() {


        // Create buttons
        prevButton = new JButton("Previous Step");
        prevButton.setToolTipText("Go to the previous step");
        timerButton = new JButton("Start Timer");
        timerButton.setToolTipText("Start the timer for the current step");
        nextButton = new JButton("Next Step");
        nextButton.setToolTipText("Go to the next step");
        checkBox = new JCheckBox("Mark as done");

        // Add buttons to button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(prevButton);
        buttonPanel.add(timerButton);
        buttonPanel.add(nextButton);
        buttonPanel.add(checkBox);


        // Initialize the step label
        steps = recipe.getSteps();
        textArea = new JTextArea("Step " + (currentStep + 1) + ".\n" + steps.get(currentStep).getInstruction());
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("Dialog", Font.PLAIN, 15));

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.add(new JScrollPane(textArea));

        // Create a split pane with the text panel and button panel
        JSplitPane stepSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, textPanel, buttonPanel);
        stepSplitPane.setResizeWeight(0.9);
        stepSplitPane.setDividerLocation(0.9);
        stepSplitPane.setDividerSize(1);

        setLeftComponent(stepSplitPane);
        setRightComponent(null); // Initially no countdown panel
        setDividerSize(0); // Initially no divider
        // Initially update button states based on the step
        updateButtonState();

        // Action for Start button
        timerButton.addActionListener(e -> {
            // Create a new CountdownPanel object and start the timer
            CountdownPanel countdownPanel = new CountdownPanel();
            countdownPanel.setUpTimer(steps.get(currentStep), currentStep + 1, this);
            countdownPanel.startCountdown();
            isTimerRunning = true;
            updateButtonState();
            updatePanel(countdownPanel);
        });

        // Action for Prev button
        prevButton.addActionListener(e -> {
            currentStep--; // Decrement step
            updateStepLabel();
            updateButtonState();
        });

        // Action for Next button
        nextButton.addActionListener(e -> {
            currentStep++; // Increment step
            updateStepLabel();
            updateButtonState();
            checkBox.setSelected(false); // Uncheck the checkbox
        });
        // Action for checkbox
        checkBox.addActionListener(e -> updateButtonState());
    }

    // Method to enable/disable buttons based on the current step
    private void updateButtonState() {
        // Use SwingUtilities to ensure UI updates happen on the EDT (Event Dispatch Thread)
        SwingUtilities.invokeLater(() -> {
            if (isTimerRunning) {
                // Disable all buttons and checkbox if the timer is running
                prevButton.setEnabled(false);
                nextButton.setEnabled(false);
                timerButton.setEnabled(false);
                checkBox.setEnabled(false);
                checkBox.setToolTipText("Cannot mark as done while timer is running");
                return;
            }
            // Enable/disable buttons based on the current step
            timerButton.setEnabled(steps.get(currentStep).getDuration() != null); // Enable the timer button only if the step has a duration
            prevButton.setEnabled(currentStep != 0); // Enable the previous button only if the current step is not the first step
            // Enable the next button only if the checkbox is checked and the current step is not the last step
            nextButton.setEnabled((checkBox.isSelected() && currentStep != steps.size() - 1));
            checkBox.setEnabled(true);
            checkBox.setToolTipText(null);
        });
    }

    // Method to update the panel based on the timer state
    private void updatePanel(CountdownPanel countdownPanel) {
        SwingUtilities.invokeLater(() -> {
            if (isTimerRunning) {
                setDividerSize(6);
                setRightComponent(countdownPanel);
            } else {
                setRightComponent(null);
                setDividerSize(0);
            }
            repaint();
            revalidate();
        });
    }

    // Method to update the step label
    private void updateStepLabel() {
        // Use SwingUtilities to ensure UI updates happen on the EDT (Event Dispatch Thread)
        SwingUtilities.invokeLater(() -> textArea.setText("Step " + (currentStep + 1) + ".\n" + steps.get(currentStep).getInstruction()));
    }
    // Method to handle countdown completion
    public void countdownFinished(CountdownPanel countdownPanel) {
        isTimerRunning = false;
        updateButtonState();
        updatePanel(countdownPanel);
    }

}
