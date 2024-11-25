package org.helperCook;

import java.util.List;

public class Duration
{
    private final double value;
    private final TimeUnit unit;

    public Duration(double number, String unit)
    {
        this.value = number;
        this.unit = new TimeUnitFactory().create(unit);
    }

    public double getSeconds(){return unit.toSeconds(value);}
    public double getMinutes() {return unit.toMinutes(value);}
    public double getHours() {return unit.toHours(value);}
    public double getNormalized(double value)
    {
        if(unit.toHours(value) >= 1) return unit.toHours(value);
        else if(unit.toMinutes(value) >= 1) return unit.toMinutes(value);
        else return unit.toSeconds(value);
    }

    @Override
    public String toString() {return value + " " + unit.getName();}

    //get a list of duration objects and return a duration object of the sum of the list
    public static Duration sumOfDurationList(List<Duration> durationList)
    {
        //get total time from all durations and convert them to seconds and add into totalSum
        double totalSum = durationList.stream().mapToDouble(Duration::getSeconds).sum();

        //create a base TimeUnit obj (seconds)
        TimeUnit baseUnit = new TimeUnitFactory().create("seconds");

        //create a new Duration obj from total seconds
        return baseUnit.normalize(totalSum);
    }
}