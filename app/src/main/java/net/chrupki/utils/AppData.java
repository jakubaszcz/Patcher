package net.chrupki.utils;

import net.chrupki.project.AppProject;

import java.nio.file.Path;

public class AppData {

    private static String CURRENT_PROJECT_NAME;
    private static Path CURRENT_PROJECT_PATH;

    public static void SetCurrentProject(String name) {
        SetCurrentProjectName(name);
        SetCurrentProjectPath();

        System.out.println("Name:" + CURRENT_PROJECT_NAME + " Path:"+CURRENT_PROJECT_PATH.toString());
    }

    public static void SetCurrentProjectName(String name) {
        CURRENT_PROJECT_NAME = name;
    }

    public static String GetCurrentProjectName() {
        return CURRENT_PROJECT_NAME;
    }

    public static void SetCurrentProjectPath() {
        for (Path p : AppProject.GetProjects()) {
            if (p.toString().contains(CURRENT_PROJECT_NAME)) {
                CURRENT_PROJECT_PATH = p;
                return;
            }
        }
    }

}
