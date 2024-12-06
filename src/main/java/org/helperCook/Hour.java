package org.helperCook;

public class Hour implements UnitConverter
{
    @Override
    public double toBase(double value) {return value * 3600;} //converts to base unit(seconds)

    @Override
    public double fromBase(double value) {return value / 3600;}

    @Override
    public String format(double value) {return value + " hour" + (value > 1 ? "s" : "");}

    @Override
    public UnitType getUnitType() {return UnitType.TIME;}
}