package org.helperCook;

public class Seconds implements TimeUnit
{
    @Override
    public int toSeconds(double value) {return (int) value;}

    @Override
    public String format(int count) {return count + " second" + (count > 1 ? "s" : "");}
}
