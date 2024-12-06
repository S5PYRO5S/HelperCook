package org.helperCook;

public class Gram implements UnitConverter
{
    @Override
    public double toBase(double value) {return value;}

    @Override
    public double fromBase(double value) {return value;}

    @Override
    public String format(double value) {return value + " g";}

    @Override
    public UnitType getUnitType() {return UnitType.MASS;}
}