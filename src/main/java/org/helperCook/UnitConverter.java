package org.helperCook;

public interface UnitConverter
{
    double toBase(double value);
    double fromBase(double value);
    String format(double value);
    UnitType getUnitType();
}
