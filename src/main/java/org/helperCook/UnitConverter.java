package org.helperCook;

public interface UnitConverter
{
    double toBase(double value);
    double fromBase(double value);
    String format(double value);
    UnitType getUnitType();

    //allows a unit to be formated to a specific unit
    default String formatIn(double value, UnitConverter converter)
    {
        double convertedValue = converter.fromBase(toBase(value));
        return converter.format(convertedValue);
    }
}
