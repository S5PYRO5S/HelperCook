package org.helperCook;

public class Minutes extends TimeUnit
{
    @Override public double toSeconds(double value) {return value * 60;}
    @Override public double toMinutes(double value) {return value;}
    @Override public double toHours(double value) {return value / 60.0;}
    @Override public String getName(){return "minutes";}
}