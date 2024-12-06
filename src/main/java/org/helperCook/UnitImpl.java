package org.helperCook;

public class UnitImpl implements Unit
{
    private final String unitName;          //the unit name
    private final double value;             //the unit value
    private final UnitConverter converter;  //the unit class (Hour, Kilogram, Gram)

    public UnitImpl(double value, String unitName, UnitConverter converter)
    {
        this.unitName = unitName;
        this.value = value;
        this.converter = converter;
    }

    @Override
    public double toBase() {return converter.toBase(value);}    //takes the base value of the unit (1000 from 1 kg)

    @Override
    public String format() {return converter.format(value);}

    @Override
    public Unit add(Unit other)
    {
        //if other isn't a UnitImpl unit return null
        if (other instanceof UnitImpl)
        {
            UnitImpl that = (UnitImpl) other;

            //if that isn't from the same unit family as the obj that calls the method return null
            if(!this.getUnitType().equals(that.getUnitType())) return null;

            //take the base values of the 2 unit objects
            double totalBaseValue = this.toBase() + that.toBase();

            //returns new object from the combined base values of the 2 objs
            return new UnitImpl(converter.fromBase(totalBaseValue), unitName, converter);
        }
        return null;
    }

    @Override
    public UnitType getUnitType() {return converter.getUnitType();}

    @Override
    public String toString() {return format();}
}