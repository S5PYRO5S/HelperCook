package org.helperCook;

public interface Unit
{
    double toBase();
    String format();    //used for unit display
    Unit add(Unit other);
    UnitType getUnitType();
}