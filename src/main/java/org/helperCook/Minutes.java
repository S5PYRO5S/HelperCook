package org.helperCook;

public class Minutes implements TimeUnit
{
    @Override
    public int toSeconds(double value) {return (int) (value * 60);}

    @Override
    public String format(int count) {return count + " minute" + (count > 1 ? "s" : "");}
}
