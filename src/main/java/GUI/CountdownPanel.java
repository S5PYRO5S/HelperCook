package GUI;

import gr.hua.dit.oop2.countdown.Countdown;
import gr.hua.dit.oop2.countdown.CountdownFactory;
import gr.hua.dit.oop2.countdown.CountdownImpl;
import gr.hua.dit.oop2.countdown.Notifier;


import org.helperCook.Step;

import javax.swing.*;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CountdownPanel extends JPanel {
    int totalSeconds;
    long lastUpdatedTime = -1; // Variable to store the last updated time
    CountdownImpl countdown; // The Countdown from the library provided
    JLabel secondsLabel;
    JProgressBar progressBar;
    JButton stopButton;

    public void setUpTimer(Step step, int stepNumber, RecipeStepsGUI parent) {
        totalSeconds = step.getDuration().getTotalSeconds(); // Get the total seconds from the step

        // Create a new CountdownImpl instance from the library
        countdown = (CountdownImpl) CountdownFactory.countdown("Step " + stepNumber + " timer", totalSeconds);
        // Create the notifier to handle countdown completion
        Notifier notifier = new Notifier() {
            @Override
            public void finished(Countdown countdown) {
                // update the parent component
                parent.countdownFinished(CountdownPanel.this);
            }
        };
        // Register the notifier with the countdown instance
        countdown.addNotifier(notifier);

        secondsLabel = new JLabel(formatTime(totalSeconds));
        add(secondsLabel);

        progressBar = new JProgressBar(0, totalSeconds);
        progressBar.setValue(0); // Start at the max value
        progressBar.setStringPainted(true); // Show percentage
        add(progressBar);

        // Create a stop button to stop the countdown whenever needed
        stopButton = new JButton("Stop");
        stopButton.addActionListener(e -> {
            countdown.stop();
            // update the parent component
            parent.countdownFinished(this);
        });
        add(stopButton);
    }

    public void startCountdown() {

        countdown.start();
        // Use ScheduledExecutorService to update the UI every second
        // makes sure that the UI is updated correctly every second
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(() -> {
            long remainingTime = countdown.secondsRemaining();
            // Update UI only if the remaining time has changed ( to avoid unintended UI updates)
            if (remainingTime != lastUpdatedTime) {
                lastUpdatedTime = remainingTime;  // Store the updated time
                SwingUtilities.invokeLater(() -> {
                    secondsLabel.setText(formatTime(remainingTime));
                    progressBar.setValue((int) (totalSeconds - remainingTime));
                    getParent().revalidate();
                    getParent().repaint();
                });
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    // Helper method to format time as HH:MM:SS
    private String formatTime(long seconds) {
        long hours = seconds / 3600;
        long minutes = seconds / 60;
        long remainingSeconds = seconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds);
    }

}
