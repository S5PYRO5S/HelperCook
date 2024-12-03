package org.helperCook;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import static org.helperCook.RegexConstants.FILE_REGEX;

public class Arguments {
    private final List<Argument> args = new LinkedList<>();

    public Arguments(List<String> argList) {
        for ( String arg : argList) {
            Argument argument = new Argument(arg);
            args.add(argument);
        }
    }

    public void isValidCookFile() throws InvalidCommandLineArgumentException {
        for ( Argument arg : args ) arg.isValidCookFile();
    }
}
