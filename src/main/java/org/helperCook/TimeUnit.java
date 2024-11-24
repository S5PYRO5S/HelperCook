package org.example;

public abstract class TimeUnit implements Nameable
{
    public abstract double toSeconds(double value);
    public abstract double toMinutes(double value);
    public abstract double toHours(double value);
    public abstract String getName();

    public double toNormalized(double value)
    {
        if(toHours(value) >= 1) return toHours(value);
        else if(toMinutes(value) >= 1) return toMinutes(value);
        else return toSeconds(value);
    }

    public Duration normalize(double value)
    {
        if(toHours(value) >= 1) return new Duration(toHours(value), "hours");
        else if(toMinutes(value) >= 1) return new Duration(toMinutes(value), "minutes");
        else return new Duration(toSeconds(value), "seconds");
    }
}
