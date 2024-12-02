package org.helperCook;

import java.util.List;

public class Duration
{
    private final int totalSeconds;

    public Duration(double value, String unit)
    {
        this.totalSeconds = TimeUnitFactory.create(unit).toSeconds(value);
    }

    public int getHours() {return totalSeconds / 3600;}
    public int getMinutes() {return (totalSeconds % 3600) / 60;}
    public int getSeconds() {return totalSeconds % 60;}

    private String getUnitName()
    {
        int hours = getHours();
        int minutes = getMinutes();
        int seconds = getSeconds();
        StringBuilder result = new StringBuilder();

        if (hours > 0) result.append(hours).append(" hour").append(hours > 1 ? "s" : "");
        if (minutes > 0)
        {
            if (!result.isEmpty()) result.append(" and ");
            result.append(minutes).append(" minute").append(minutes > 1 ? "s" : "");
        }
        if (seconds > 0)
        {
            if (!result.isEmpty()) result.append(" and ");
            result.append(seconds).append(" second").append(seconds > 1 ? "s" : "");
        }
        if (result.isEmpty()) result.append("0 seconds");

        return result.toString();
    }

    public int toSeconds() {return this.totalSeconds;}

    @Override
    public String toString() {return getUnitName();}

    public static Duration sumOfDurationList(List<Duration> durations)
    {
        int totalSeconds = durations.stream()
                .mapToInt(Duration::toSeconds)
                .sum();
        return new Duration(totalSeconds, "seconds");
    }
}
