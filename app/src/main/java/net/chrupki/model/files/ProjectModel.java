package net.chrupki.model.files;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import net.chrupki.dto.ProjectDTO;
import net.chrupki.dto.VersionDTO;

public class ProjectModel {

    private static final StringProperty name = new SimpleStringProperty();

    public void from(ProjectDTO projectDTO) {
        name.set(projectDTO.getName());
    }

    public void clear() {
        name.set(null);
    }

    public StringProperty getName() {
        return name;
    }
}
