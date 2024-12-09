package org.helperCook;

public class Milliliter implements UnitConverter
{
    @Override
    public double toBase(double value) {return value;}
    @Override
    public double fromBase(double value) {return value;}
    @Override
    public String format(double value) {
        return (value % 1 == 0 ? String.format("%.0f ml", value) : value + " ml");
    }
    @Override
    public UnitType getUnitType() {return UnitType.VOLUME;}
}
