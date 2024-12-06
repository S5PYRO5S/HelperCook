package org.helperCook;

import java.util.ArrayList;
import java.util.List;

public class Duration
{
    private final UnitImpl durationUnit;

    public Duration(double value, String unit)
    {
        this.durationUnit = (UnitImpl) UnitFactory.create(value, unit);
    }


    private int getHours()
    {
        return (int) (durationUnit.toBase() / 3600); // Total hours
    }

    private int getMinutes()
    {
        return (int) ((durationUnit.toBase() % 3600) / 60); // Remaining minutes after hours
    }

    private int getSeconds()
    {
        return (int) (durationUnit.toBase() % 60); // Remaining seconds after minutes
    }

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

    //TODO
    public Duration decrease(){return null;}

    @Override
    public String toString() {return getUnitFormat();}
}

//Old Duration class
//public class Duration
//{
//    private final int totalSeconds;
//
//    public Duration(double value, String unit)
//    {
//        this.totalSeconds = UnitFactory.create(unit).toBase(value);
//    }
//
//    public int getHours() {return totalSeconds / 3600;}
//    public int getMinutes() {return (totalSeconds % 3600) / 60;}
//    public int getSeconds() {return totalSeconds % 60;}
//
//    private String getUnitName()
//    {
//        List<String> parts = new ArrayList<>();
//        if (getHours() > 0) parts.add(new Hour().format(getHours()));
//        if (getMinutes() > 0) parts.add(new Minute().format(getMinutes()));
//        if (getSeconds() > 0) parts.add(new Second().format(getSeconds()));
//        return parts.isEmpty() ? "0 seconds" : String.join(" and ", parts);
//    }
//
//    public int toSeconds() {return this.totalSeconds;}
//
//    @Override
//    public String toString() {return getUnitName();}
//
//    public Duration add(Duration duration)
//    {
//        int totalSeconds = this.totalSeconds + duration.toSeconds();
//        return new Duration(totalSeconds, "seconds");
//    }
//}
