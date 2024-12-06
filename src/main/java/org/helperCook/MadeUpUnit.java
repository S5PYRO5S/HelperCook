package org.helperCook;

public class MadeUpUnit implements UnitConverter
{
    private final String unitName;

    public MadeUpUnit(String unitName) {this.unitName = unitName;}

    @Override
    public double toBase(double value) {return value;}  //made up units doesn't need conversion

    @Override
    public double fromBase(double value) {return value;}

    @Override
    public String format(double value) {return value + " " + unitName;}

    @Override
    public UnitType getUnitType() {return UnitType.MADE_UP;}
}
