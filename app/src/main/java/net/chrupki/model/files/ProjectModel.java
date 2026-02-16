package net.chrupki.model.files;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import net.chrupki.dto.ProjectDTO;
import net.chrupki.dto.VersionDTO;

public class ProjectModel {

    private static final StringProperty name = new SimpleStringProperty();
    private static final StringProperty description = new SimpleStringProperty();

    public void from(ProjectDTO projectDTO) {
        name.set(projectDTO.getName());
        description.set(projectDTO.getDescription());
    }

    public void clear() {
        name.set(null);
        description.set(null);
    }

    public StringProperty getName() {
        return name;
    }

    public StringProperty getDescription() {
        return description;
    }
}
