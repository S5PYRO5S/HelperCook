package org.helperCook;

import java.util.Objects;

public class Ingredient implements Nameable
{
    private final String name;

    public Ingredient(String name)
    {
        this.name = name.trim().toLowerCase();
    }

    @Override public String getName() {return name;}

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ingredient that = (Ingredient) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(name);
    }
}