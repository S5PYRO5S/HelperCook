package org.helperCook;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory class for creating instances of {@link Unit} with the right convertors.
 * Contains a Map of unit names mapped to their {@link UnitConverter} objects
 * if a string isn't registered to the map it defaults to {@link MadeUpUnit} as a convertor
 */
public class UnitFactory
{
    private static final Map<String, UnitConverter> UNITS = new HashMap<>();
    static
    {
        UNITS.put("seconds", new Second());
        UNITS.put("sec", new Second());
        UNITS.put("s", new Second());
        UNITS.put("minutes", new Minute());
        UNITS.put("minute", new Minute());
        UNITS.put("min", new Minute());
        UNITS.put("hours", new Hour());
        UNITS.put("hour", new Hour());
        UNITS.put("h", new Hour());
        UNITS.put("gr", new Gram());
        UNITS.put("g", new Gram());
        UNITS.put("kg", new Kilogram());
        UNITS.put("kilos", new Kilogram());
        UNITS.put("ml", new Milliliter());
        UNITS.put("l", new Liter());
    }

    public static Unit create(double value, String unitName)
    {
        if (unitName == null) throw new IllegalArgumentException("Unit name cannot be null");
        UnitConverter result = UNITS.get(unitName.toLowerCase());
        if (result == null) return new Unit(value, new MadeUpUnit(unitName));
        return new Unit(value, result);
    }
}