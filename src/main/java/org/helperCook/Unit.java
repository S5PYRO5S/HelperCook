package org.helperCook;

/**
 * Represent a measurement unit with a value and a way to convert it in different units
 */
public class Unit
{
    private final double value;
    private final UnitConverter converter;

    /**
     *
     * @param value the value of the unit
     * @param converter the object that determines how to unit is converted
     */
    public Unit(double value, UnitConverter converter)
    {
        if (converter == null) throw new IllegalArgumentException("Converter cannot be null");
        this.value = value;
        this.converter = converter;
    }

    //returns the base value of the unit
    public double toBase() {return converter.toBase(value);}

    public String format() {return converter.format(value);}

    /**
     * Adds another unit to the unit calling the method (only if the UnitType is the same)
     * @param other the other Unit object
     * @return the new object that is the result of this + other
     */
    public Unit add(Unit other)
    {
        if (other == null) throw new IllegalArgumentException("Other unit cannot be null");
        if(this.getUnitType() != other.getUnitType()) throw new IllegalArgumentException("Cannot add units of different types.");
        double totalBaseValue = this.toBase() + other.toBase();
        double newValue = converter.fromBase(totalBaseValue);
        return new Unit(newValue, converter);
    }

    //return an enum that represent the type of the unit (MASS, VOLUME...)
    public UnitType getUnitType()
    {
        return converter.getUnitType();
    }

    @Override
    public String toString() {return format();}

    /**
     * Formats the value of the unit into the largest possible unit of the same time
     * (e.g. MASS "1000 Grams" > "1 Kilo")
     * @return a string representing the new format
     */
    public String formatInMax()
    {
        switch (converter.getUnitType())
        {
            case UnitType.MASS -> {return converter.formatIn(value, new Kilogram());}
            case UnitType.VOLUME -> {return converter.formatIn(value, new Liter());}
            default -> {return converter.formatIn(value, converter);}
        }
    }

    //Multiples the unit with the given factor
    public Unit multiplyByFactor(int factor)
    {
        factor = Math.abs(factor);
        double newValue = this.value * factor;
        return new Unit(newValue, converter);
    }
}
