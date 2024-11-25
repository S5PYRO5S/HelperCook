package org.helperCook;

import java.util.Objects;

public class Ingredient implements Nameable
{
    private final String name;
    private final String unit;

    public Ingredient(String name, String unit) {this.name = name;this.unit = unit;}

    @Override public String getName() {return name;}
    public String getUnit() {return unit;}

    @Override
    public boolean equals(Object o)
    {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Ingredient ingredient = (Ingredient)o;
        return name.equals(ingredient.name) && unit.equals(ingredient.unit);
    }

    @Override public int hashCode() {return Objects.hash(name, unit);}
}