package org.helperCook;

public class Cookware implements Nameable
{
    private final String name;
    public Cookware(String name) {this.name = name;}
    @Override public String getName() {return name;}
}
