package GUI;

import gr.hua.dit.oop2.countdown.Countdown;
import gr.hua.dit.oop2.countdown.CountdownFactory;
import gr.hua.dit.oop2.countdown.CountdownImpl;
import gr.hua.dit.oop2.countdown.Notifier;

import org.helperCook.Step;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class StepCountdown {
    int totalSeconds;
    CountdownImpl countdown; // The Countdown from the library
    long lastUpdatedTime = -1; // Variable to store the last updated time
    JSplitPane finalWindow;

    public void startCountdown(Step step, int stepNumber, JSplitPane panel, JButton timerButton, RecipeStepsGUI recipeStepsGui) {

        totalSeconds = step.getDuration().getTotalSeconds();

        // Create a new CountdownImpl instance from the library
        countdown = (CountdownImpl) CountdownFactory.countdown("Step " + stepNumber + " timer", totalSeconds);
        // Create the notifier to handle countdown completion
        Notifier notifier = new Notifier() {
            @Override
            public void finished(Countdown countdown) {
                SwingUtilities.invokeLater(() -> {
                    // Enable the timer button after countdown finishes
                    timerButton.setEnabled(true);
                    System.out.println("Countdown finished, dialog disposed"); //TODO debug remove


                });
            }
        };

        // Register the notifier with the countdown instance
        countdown.addNotifier(notifier);

        // Frame and text area



        JLabel secondsLabel = new JLabel(String.valueOf(totalSeconds));
        secondsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        secondsLabel.setVerticalAlignment(SwingConstants.CENTER);
        secondsLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        JButton stopButton = new JButton("Stop");
        stopButton.addActionListener(e -> {
            countdown.stop();
            //TODO update mainframe and remove countdown split plane
        });

        JProgressBar progressBar = new JProgressBar(0, totalSeconds);
        progressBar.setValue(0); // Start at the max value
        progressBar.setStringPainted(true); // Show percentage


        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, secondsLabel, progressBar);
        splitPane.setDividerSize(0);
        splitPane.setResizeWeight(0.5);
        finalWindow = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,splitPane, panel );
        finalWindow.setDividerSize(0);
        finalWindow.setResizeWeight(0.5);
        finalWindow.setDividerLocation(100);
        finalWindow.setVisible(true);

        // Start the countdown (it already manages the Timer internally)
        countdown.start();
        // Use ScheduledExecutorService to update the UI every second
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(() -> {
            long remainingTime = countdown.secondsRemaining();
            // Update UI only if the remaining time has changed
            if (remainingTime != lastUpdatedTime) {
//                System.out.println(countdown.secondsRemaining() + " seconds");
                secondsLabel.setText(formatTime(remainingTime));
                lastUpdatedTime = remainingTime;  // Store the updated time
                progressBar.setValue((int) (totalSeconds - remainingTime));
            }
        }, 0, 1, TimeUnit.SECONDS);


    }

    // Helper method to format time as MM:SS
    private String formatTime(long seconds) {
        long hours = seconds / 3600;
        long minutes = seconds / 60;
        long remainingSeconds = seconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds);
    }
    public JSplitPane getFinalWindow(){
        return finalWindow;
    }
}
