package org.helperCook;

public class RegexConstants
{
    public final static String NAME_REGEX = "^(.*?)(\\.[^.]*?)?$";
    //public final static String INGREDIENT_REGEX = "@(\\p{L}[\\p{L}\\p{N}_]*)(?:\\{(\\d+)(?:%(\\p{L}[\\p{L}\\p{N}_]*))?\\})?";
    public static final String COOKWARE_REGEX = "#([^\\s@~#{}.,?]+(?:\\s[^\\s@~#{}.,?]+)*)\\{[^#~]*}|#([^\\s@~#{}.,?]+)";
    public static final String TIME_REGEX = "~\\{(\\d+(?:\\.\\d+)?)%([a-zA-Z]+)}";
    public static final String INGREDIENT_REGEX = "@([^\\s{}~@#]+(?:\\s+[^\\s{}~@#]+)*(?=\\s*\\{[^~@#]*})|[^\\s{}~@#]+)(?:\\{(\\d+)(?:%([^}]+))?})?";
    public static final String FILE_REGEX = "(?i).*\\.cook$";
    public static final String BLANK_LINE_REGEX = "\\n\\s+\\n";
}
