package org.helperCook;

public class Kilogram implements UnitConverter
{
    @Override
    public double toBase(double value) {return value * 1000;}

    @Override
    public double fromBase(double value) {return value / 1000;}

    @Override
    public String format(double value) {return value + " kg";}

    @Override
    public UnitType getUnitType() {return UnitType.MASS;}
}
