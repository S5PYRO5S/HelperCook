package org.helperCook;

import java.util.HashMap;
import java.util.Map;

public class UnitFactory
{
    private static final Map<String, UnitConverter> UNITS = new HashMap<>();
    static
    {
        //Time units
        UNITS.put("seconds", new Second());
        UNITS.put("minutes", new Minute());
        UNITS.put("hours", new Hour());

        //Weight units
        UNITS.put("g", new Gram());
        UNITS.put("kg", new Kilogram());
    }

    //factory method to return objects based on a String value
    public static Unit create(double value, String unitName)
    {
        //if string unit doesn't exist create Unit with converter MadeUpUnit
        UnitConverter result = UNITS.get(unitName.toLowerCase());
        if(result == null) return new UnitImpl(value, unitName, new MadeUpUnit(unitName));
        return new UnitImpl(value, unitName, result);
    }


    //old factory
//    public static TimeUnit create(String unit)
//    {
//        return switch (unit.toLowerCase())
//        {
//            case "seconds" -> new Seconds();
//            case "minutes" -> new Minutes();
//            case "hours" -> new Hours();
//            default -> throw new IllegalArgumentException("Invalid unit: " + unit);
//        };
//    }
}
