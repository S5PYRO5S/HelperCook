package org.helperCook;

public class RegexConstants
{
    public final static String NAME_REGEX = "^(.*?)(\\.[^.]*?)?$";
    public final static String COOKWARE_REGEX = "#([^\\s@~#{}.,?]+(?:\\s[^\\s@~#{}.,?]+)*)\\{[^#~]*}|#([^\\s@~#{}.,?]+)";
    public final static String TIME_REGEX = "~\\{(\\d+(?:\\.\\d+)?)%([a-zA-Z]+)}";
    public final static String INGREDIENT_REGEX = "@([^\\s{}~@#]+(?:\\s+[^\\s{}~@#]+)*(?=\\s*\\{[^~@#]*})|[^\\s{}~@#]+)(?:\\{(\\d+)(?:%([^}]+))?})?";
    public static final String FILE_REGEX = "(?i).*\\.cook$"; // regex for a file with .cook extension
}
