package net.chrupki.app.context;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import net.chrupki.project.AppProject;

import java.nio.file.Path;

public class ProjectContext {

    private static final StringProperty name = new SimpleStringProperty();
    private final ObjectProperty<Path> path = new SimpleObjectProperty<>();

    public void setName(String set) {
        name.set(set);
    }

    public StringProperty getName() {
        return name;
    }
}
