package org.helperCook;

public class Hours implements TimeUnit
{
    @Override
    public int toSeconds(double value) {return (int) (value * 3600);}

    @Override
    public String format(int count) {return count + " hour" + (count > 1 ? "s" : "");}
}
