package org.helperCook;

public class Minute implements UnitConverter
{
    @Override
    public double toBase(double value) {return value * 60;}

    @Override
    public double fromBase(double value) {return value / 60;}

    @Override
    public String format(double value) {return value + " minute" + (value > 1 ? "s" : "");}

    @Override
    public UnitType getUnitType() {return UnitType.TIME;}
}
