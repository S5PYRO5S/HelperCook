package org.helperCook;

public class Unit
{
    private final double value;
    private final UnitConverter converter;
    private Double cachedBaseValue = null;

    public Unit(double value, UnitConverter converter)
    {
        if (converter == null) throw new IllegalArgumentException("Converter cannot be null");
        this.value = value;
        this.converter = converter;
    }

    public double toBase()
    {
        if(cachedBaseValue == null) cachedBaseValue = converter.toBase(value);
        return cachedBaseValue;
    }

    public String format() {return converter.format(value);}

    public Unit add(Unit other)
    {
        if(this.getUnitType() != other.getUnitType()) throw new IllegalArgumentException("Cannot add units of different types.");
        if (other == null) throw new IllegalArgumentException("Other unit cannot be null");
        double totalBaseValue = this.toBase() + other.toBase();
        double newValue = converter.fromBase(totalBaseValue);
        return new Unit(newValue, converter);
    }

    public UnitType getUnitType() {
        return converter.getUnitType();
    }

    @Override
    public String toString() {
        return format();
    }

    public String formatInMax() {
        switch (converter.getUnitType())
        {
            case UnitType.MASS -> {return converter.formatIn(value, new Kilogram());}
            case UnitType.VOLUME -> {return converter.formatIn(value, new Liter());}
            default -> {return converter.formatIn(value, converter);}
        }

    }

    public Unit multiplyByFactor(int factor)
    {
        factor = Math.abs(factor);
        double newValue = this.value * factor;
        return new Unit(newValue, converter);
    }
}
