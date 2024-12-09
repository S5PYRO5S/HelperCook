package org.helperCook;

public class Minute implements UnitConverter {
    @Override
    public double toBase(double value) {
        return value * 60;
    }

    @Override
    public double fromBase(double value) {
        return value / 60;
    }

    @Override
    public String format(double value) {
        if (value == (int) value) return (int) value + " minute" + (value != 1 ? "s" : "");
        return String.format("%s minute%s", value, value == 1 ? "" : "s");
    }

    @Override
    public UnitType getUnitType() {
        return UnitType.TIME;
    }
}
