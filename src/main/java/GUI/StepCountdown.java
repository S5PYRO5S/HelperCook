package GUI;

import gr.hua.dit.oop2.countdown.Countdown;
import gr.hua.dit.oop2.countdown.CountdownFactory;
import gr.hua.dit.oop2.countdown.CountdownImpl;
import gr.hua.dit.oop2.countdown.Notifier;
import org.helperCook.Step;

public class StepCountdown {
    int totalSeconds;

    public void startCountdown(Step step, int stepNumber) {
        totalSeconds = step.getDuration().getTotalSeconds();
        CountdownImpl countdown = (CountdownImpl) CountdownFactory.countdown("Step " + stepNumber + " timer", totalSeconds);
        countdown.start();
        Notifier notifier = new Notifier() {
            @Override
            public void finished(Countdown countdown) {

            }
        };
        countdown.addNotifier(notifier);



    }

}
