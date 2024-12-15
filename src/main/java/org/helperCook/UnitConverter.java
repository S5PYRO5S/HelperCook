package org.helperCook;

/**
 * Implementations are used for converting values to and from a base of a unit
 * e.g. toBase() from a TIME unitType returns the a value the represents seconds
 */
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
