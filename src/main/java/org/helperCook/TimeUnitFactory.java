package org.helperCook;

public class TimeUnitFactory
{
    public TimeUnit create(String unit)
    {
        switch (unit.toLowerCase())
        {
            case "seconds" -> {return new Seconds();}
            case "minutes" -> {return new Minutes();}
            case "hours" -> {return new Hours();}
            default -> {return null;}
        }
    }
}
