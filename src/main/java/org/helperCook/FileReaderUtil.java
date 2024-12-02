package org.helperCook;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class FileReaderUtil
{
    //returns an array of strings that represent each step instruction
    public static String[] readFileContent(File file) throws IOException
    {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = Files.newBufferedReader(file.toPath(), StandardCharsets.UTF_8))
        {
            String line;
            while ((line = reader.readLine()) != null) {content.append(line).append(System.lineSeparator());}
        }
        return content.toString().trim().split("\\n\\s+\\n");
    }
}