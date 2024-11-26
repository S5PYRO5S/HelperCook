package org.helperCook;

public class Hours extends TimeUnit
{
    @Override public double toSeconds(double value) {return value * 3600;}
    @Override public double toMinutes(double value) {return value * 60.0;}
    @Override public double toHours(double value) {return value;}
    @Override public String getName(){return "hours";}
}
