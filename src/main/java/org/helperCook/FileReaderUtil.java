package org.helperCook;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileReaderUtil
{
    public static String readFileContent(File file) throws IOException
    {
        return new String(Files.readAllBytes(file.toPath()));
    }
}
