package org.helperCook;

import java.util.ArrayList;
import java.util.List;

public class Duration
{
    private final Unit durationUnit;

    public Duration(double value, String unit)
    {
        this.durationUnit = UnitFactory.create(value, unit);
    }
    private int getHours() {return (int) (durationUnit.toBase() / 3600);} // Total hours
    private int getMinutes() {return (int) ((durationUnit.toBase() % 3600) / 60);} // Remaining minutes after hours
    private int getSeconds() {return (int) (durationUnit.toBase() % 60);} // Remaining seconds after minutes

    private String getUnitFormat()
    {
        List<String> parts = new ArrayList<>();
        if(getHours() > 0) parts.add(new Hour().format(getHours()));
        if(getMinutes() > 0) parts.add(new Minute().format(getMinutes()));
        if(getSeconds() > 0) parts.add(new Second().format(getSeconds()));
        return String.join(" ", parts);
    }

    public Duration add(Duration other)
    {
        double totalSeconds = durationUnit.toBase() + other.durationUnit.toBase();
        return new Duration(totalSeconds, "seconds");
    }

    public Duration decrease()
    {
        double decreasedSeconds = durationUnit.toBase() - 1;
        if (decreasedSeconds < 0) {decreasedSeconds = 0;}
        return new Duration(decreasedSeconds, "seconds");
    }

    @Override
    public String toString() {return getUnitFormat();}

    public int getTotalSeconds()
    {
        return (int) (durationUnit.toBase());
    }
}