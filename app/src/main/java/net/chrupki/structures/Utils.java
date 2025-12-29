package net.chrupki.structures;

import java.nio.file.Path;

public class Utils {
    private static String CURRENT_PROJECT_STRING;
    private static Path CURRENT_PROJECT_PATH;

    public static void setCurrentProjectString(String name) { CURRENT_PROJECT_STRING = name; }
    public static void setCurrentProjectPath(Path path) { CURRENT_PROJECT_PATH = path; }

    public static String getCurrentProjectString() { return CURRENT_PROJECT_STRING; }
    public static Path getCurrentProjectPath() { return CURRENT_PROJECT_PATH; }

}
