package net.chrupki.ui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import net.chrupki.model.Patch;
import net.chrupki.model.Version;

public class ProjectModel {

    public ProjectModel() {}

    private final ObservableList<Version> versions = FXCollections.observableArrayList();
    private final ObservableList<String> projects = FXCollections.observableArrayList();
    private final ObservableList<Patch> patches = FXCollections.observableArrayList();

    private final BooleanProperty editProject = new SimpleBooleanProperty(false);
    private final BooleanProperty editVersion = new SimpleBooleanProperty(false);
    private final BooleanProperty editPatch = new SimpleBooleanProperty(false);

    public void setProjectProperty(BooleanProperty booleanProperty) {
        editProject.set(booleanProperty.get());
    }

    public BooleanProperty getProjectProperty() {
        return editProject;
    }

    public void setVersionProperty(BooleanProperty booleanProperty) {
        editVersion.set(booleanProperty.get());
    }

    public BooleanProperty getVersionProperty() {
        return editVersion;
    }

    public void setPatchProperty(BooleanProperty booleanProperty) {
        editPatch.set(booleanProperty.get());
    }

    public BooleanProperty getPatchProperty() {
        return editPatch;
    }

    public ObservableList<Version> getVersions() {
        return versions;
    }

    public ObservableList<String> getProjects() {
        return projects;
    }

    public ObservableList<Patch> getPatches() { return patches; }
}
