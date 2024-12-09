package org.helperCook;

public class Liter implements UnitConverter
{
    @Override
    public double toBase(double value) {return value * 1000;}
    @Override
    public double fromBase(double value) {return value / 1000;}
    @Override
    public String format(double value) {
        return (value % 1 == 0 ? String.format("%.0f li", value) : value + " li");
    }
    @Override
    public UnitType getUnitType() {return UnitType.VOLUME;}
}
