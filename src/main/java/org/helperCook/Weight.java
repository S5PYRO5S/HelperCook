package org.helperCook;

public class Weight extends Unit
{
    public Weight(double value, UnitConverter converter)
    {
        super(value, converter);
        if(converter.getUnitType() != UnitType.MASS)
        {
            throw new IllegalArgumentException("Converter must be of type MASS");
        }
    }
    public Weight add(Weight other)
    {
        return (Weight) super.add(other);
    }
}
