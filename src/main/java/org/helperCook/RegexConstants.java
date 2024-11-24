package org.example;

public class RegexConstants
{
    public final static String NAME_REGEX = "^(.*?)(\\.[^.]*?)?$";
    public final static String INGREDIENT_REGEX = "@(\\p{L}[\\p{L}\\p{N}_]*)(?:\\{(\\d+)(?:%(\\p{L}[\\p{L}\\p{N}_]*))?\\})?";
    public final static String COOKWARE_REGEX = "#([^\\s@~#{}.,?]+(?:\\s[^\\s@~#{}.,?]+)*)\\{[^#~]*}|#([^\\s@~#{}.,?]+)";
    public final static String TIME_REGEX = "~\\{(\\d+)%([a-zA-Z]+)}";
}
