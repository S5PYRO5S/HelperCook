package org.helperCook;

import java.util.List;

public class Duration
{
    private int hours;
    private int minutes;
    private int seconds;
    private String unitName;

    // Constructor with value and unit
    public Duration(double value, String unit)
    {
        int totalSeconds = convertToSeconds(value, unit);
        setSeconds(totalSeconds);
        updateUnitName();
    }

    public int getHours() {return hours;}
    public int getMinutes() {return minutes;}
    public int getSeconds() {return seconds;}
    public String getUnitName() {return unitName;}

    public int toSeconds() {return hours * 3600 + minutes * 60 + seconds;}

    public void setSeconds(int totalSeconds)
    {
        int m = totalSeconds / 60;
        this.seconds = totalSeconds % 60;
        this.minutes = m % 60;
        this.hours = m / 60;
    }

    public boolean equals(Duration d) {return this.toSeconds() == d.toSeconds();}

    @Override
    public String toString() {return hours + " : " + minutes + " : " + seconds;}

    // Converts a value and unit into seconds
    private int convertToSeconds(double value, String unit)
    {
        switch (unit.toLowerCase())
        {
            case "seconds" -> {return (int) value;}
            case "minutes" -> {return (int) (value * 60);}
            case "hours" -> {return (int) (value * 3600);}
            default -> throw new IllegalArgumentException("Unknown time unit: " + unit);
        }
    }

    public Duration normalize() {return new Duration(this.toSeconds(), "seconds");}

    public Duration add(Duration other)
    {
        return new Duration(this.toSeconds() + other.toSeconds(), "seconds").normalize();
    }

    public static Duration sumOfDurationList(List<Duration> durations) {
        int totalSeconds = durations.stream()
                .mapToInt(Duration::toSeconds)
                .sum();
        return new Duration(totalSeconds, "seconds").normalize();
    }

    private void updateUnitName()
    {
        if (hours > 0)
        {
            if (minutes > 0 || seconds > 0)
            {
                this.unitName = hours + " hour" + (hours > 1 ? "s" : "")
                        + (minutes > 0 ? " and " + minutes + " minute" + (minutes > 1 ? "s" : "") : "")
                        + (seconds > 0 ? " and " + seconds + " second" + (seconds > 1 ? "s" : "") : "");
            }
            else
            {
                this.unitName = hours + " hour" + (hours > 1 ? "s" : "");
            }
        }
        else if (minutes > 0)
        {
            if (seconds > 0)
            {
                this.unitName = minutes + " minute" + (minutes > 1 ? "s" : "") + " and "
                        + seconds + " second" + (seconds > 1 ? "s" : "");
            }
            else
            {
                this.unitName = minutes + " minute" + (minutes > 1 ? "s" : "");
            }
        }
        else
        {
            this.unitName = seconds + " second" + (seconds > 1 ? "s" : "");
        }
    }
}