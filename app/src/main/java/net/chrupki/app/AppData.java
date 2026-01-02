package net.chrupki.app;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import net.chrupki.project.AppProject;

import java.nio.file.Path;

public class AppData {

    private static final StringProperty CURRENT_PROJECT_NAME = new SimpleStringProperty();
    private static final ObjectProperty<Path> CURRENT_PROJECT_PATH = new SimpleObjectProperty<>();

    public static void setCurrentProject(String name) {
        setCurrentProjectName(name);
        setCurrentProjectPath();
    }

    public static void setCurrentProjectName(String name) {
        CURRENT_PROJECT_NAME.set(name);
    }

    public static void setCurrentProjectPath() {
        for (Path p : AppProject.GetProjects()) {
            if (p.toString().contains(CURRENT_PROJECT_NAME.get())) {
                CURRENT_PROJECT_PATH.set(p);
                return;
            }
        }
    }

    public static String getCurrentProjectName() {
        return CURRENT_PROJECT_NAME.get();
    }

    public static Path getCurrentProjectPath() {
        return CURRENT_PROJECT_PATH.get();
    }

    public static ObjectProperty<Path> getPropertyCurrentProjectPath() {
        return CURRENT_PROJECT_PATH;
    }

    public static StringProperty getPropertyCurrentProjectName() {
        return CURRENT_PROJECT_NAME;
    }

}
