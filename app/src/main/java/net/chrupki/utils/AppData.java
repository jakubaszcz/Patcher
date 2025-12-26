package net.chrupki.utils;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import net.chrupki.project.AppProject;

import java.nio.file.Path;

public class AppData {

    private static final StringProperty CURRENT_PROJECT_NAME = new SimpleStringProperty();
    private static final ObjectProperty<Path> CURRENT_PROJECT_PATH = new SimpleObjectProperty<>();

    public static void SetCurrentProject(String name) {
        SetCurrentProjectName(name);
        SetCurrentProjectPath();
    }

    public static void SetCurrentProjectName(String name) {
        CURRENT_PROJECT_NAME.set(name);
    }

    public static void SetCurrentProjectPath() {
        for (Path p : AppProject.GetProjects()) {
            if (p.toString().contains(CURRENT_PROJECT_NAME.get())) {
                CURRENT_PROJECT_PATH.set(p);
                return;
            }
        }
    }

    public static String GetCurrentProjectName() {
        return CURRENT_PROJECT_NAME.get();
    }

    public static Path GetCurrentProjectPath() {
        return CURRENT_PROJECT_PATH.get();
    }

    public static ObjectProperty<Path> GetPropertyCurrentProjectPath() {
        return CURRENT_PROJECT_PATH;
    }

    public static StringProperty GetPropertyCurrentProjectName() {
        return CURRENT_PROJECT_NAME;
    }

}
