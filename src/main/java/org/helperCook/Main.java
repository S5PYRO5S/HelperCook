package org.helperCook;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class Main
{
    public static void main(String[] args) throws IOException {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        File[] files;
        try {
            files = CheckArguments.Check(args);
        } catch (Exception e) {
            //throw new RuntimeException( e );
            System.out.println( "Error: " + e.getMessage() );
            System.out.println("Usage: java -jar helperCook.jar <file> or java -jar helperCook.jar -list <file> <file> ...");
            System.exit( 1 );
        }
    }
}