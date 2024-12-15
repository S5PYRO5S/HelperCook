package org.helperCook;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Represents a duration of time with utility methods for manipulation (addition, decrementing)
 * and formatting(with a string representation of the duration e.g. 1 minute and 30 seconds).
 * </p>
 *
 * <p>
 * The duration is internally stored as a {@link Unit} object, which provides conversion
 * to a base unit (seconds) for calculations.
 * </p>
 */

public class Duration
{
    private Unit durationUnit;

    /**
     * constructor calls UnitFactory so it can make a UnitConverter object for the unit object with a string
     * @param value the value
     * @param unit  a string that represents the unit (e.g. "seconds"...)
     */
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
        //adds the hours to the format if its 1 or more hours
        if(getHours() > 0) parts.add(new Hour().format(getHours()));
        if(getMinutes() > 0) parts.add(new Minute().format(getMinutes()));
        if(getSeconds() > 0) parts.add(new Second().format(getSeconds()));

        //Combine the parts with a space between
        return String.join(" ", parts);
    }

    /**
     *
     * @param other the duration to add
     * @return the result of the operation
     */
    public Duration add(Duration other)
    {
        double totalSeconds = durationUnit.toBase() + other.durationUnit.toBase();
        return new Duration(totalSeconds, "seconds");
    }

    /**
     * Decreases the duration be 1 minute
     * @return the new {@link Duration} with the decreased duration
     */
    public Duration decrease()
    {
        double decreasedSeconds = durationUnit.toBase() - 1;
        if (decreasedSeconds < 0) {decreasedSeconds = 0;}
        return new Duration(decreasedSeconds, "seconds");
    }

    //Decreases the duration by one seconds and updates the current duration unit
    //the same as the decrease() method but it only updates the durationUnit
    public void decreamentByOneSecond()
    {
        if(durationUnit.toBase() != 0)
            durationUnit = new Unit(durationUnit.toBase() - 1, new Second());
    }

    @Override
    public String toString() {return getUnitFormat();}
    public int getTotalSeconds() {return (int) (durationUnit.toBase());}
}