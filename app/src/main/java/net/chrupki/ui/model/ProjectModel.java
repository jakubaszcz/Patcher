package net.chrupki.ui.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import net.chrupki.model.Patch;
import net.chrupki.model.Version;

public class ProjectModel {

    public ProjectModel() {}

    private final ObservableList<Version> versions = FXCollections.observableArrayList();
    private final ObservableList<String> projects = FXCollections.observableArrayList();
    private final ObservableList<Patch> patches = FXCollections.observableArrayList();

    private final BooleanProperty editActive = new SimpleBooleanProperty(false);
    private final BooleanProperty editProject = new SimpleBooleanProperty(false);
    private final BooleanProperty editVersion = new SimpleBooleanProperty(false);
    private final BooleanProperty editPatch = new SimpleBooleanProperty(false);

    public void setEditActiveProperty(boolean booleanProperty) {
        editActive.set(booleanProperty);
    }

    public BooleanProperty getEditActiveProperty() {
        return editActive;
    }

    public void setEditProjectProperty(boolean booleanProperty) {
        setEditActiveProperty(booleanProperty);
        editProject.set(booleanProperty);
    }

    public BooleanProperty getEditProjectProperty() {
        return editProject;
    }

    public void setEditVersionProperty(boolean booleanProperty) {
        setEditActiveProperty(booleanProperty);
        editVersion.set(booleanProperty);
    }

    public BooleanProperty getEditVersionProperty() {
        return editVersion;
    }

    public void setEditPatchProperty(boolean booleanProperty) {
        setEditActiveProperty(booleanProperty);
        editPatch.set(booleanProperty);
    }

    public BooleanProperty getEditPatchProperty() {
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
