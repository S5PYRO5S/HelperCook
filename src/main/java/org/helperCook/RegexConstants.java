package org.helperCook;

public class RegexConstants
{
    public final static String NAME_REGEX = "^(.*?)(\\.[^.]*?)?$";
    public final static String INGREDIENT_REGEX = "@(\\p{L}[\\p{L}\\p{N}_]*)(?:\\{(\\d+)(?:%(\\p{L}[\\p{L}\\p{N}_]*))?\\})?";
    public final static String COOKWARE_REGEX = "#([^\\s@~#{}.,?]+(?:\\s[^\\s@~#{}.,?]+)*)\\{[^#~]*}|#([^\\s@~#{}.,?]+)";
    public static final String TIME_REGEX = "~\\{(\\d+(?:\\.\\d+)?)%([a-zA-Z]+)}";

//    public final static String COMBINED_REGEX =
//            "#([^\\s@~#{}.,?]+(?:\\s[^\\s@~#{}.,?]+)*)\\{[^#~]*}|#([^\\s@~#{}.,?]+)@(\\p{L}[\\p{L}\\p{N}_]*)(?:\\{(\\d+)(?:%(\\p{L}[\\p{L}\\p{N}_]*))?\\})?";

}
