package org.helperCook;

public class TimeUnitFactory
{
    public static TimeUnit create(String unit)
    {
        return switch (unit.toLowerCase())
        {
            case "seconds" -> new Seconds();
            case "minutes" -> new Minutes();
            case "hours" -> new Hours();
            default -> throw new IllegalArgumentException("Invalid unit: " + unit);
        };
    }
}
