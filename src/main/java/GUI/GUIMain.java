package GUI;

import java.io.File;
import java.util.List;

public class GUIMain {
    public static void main(List<File> files) {
        System.out.println("GUI opened with " + files.size() + " files");
        for (File file : files) {
            System.out.println(file);
        }
    }
}
