package net.chrupki.ui.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import net.chrupki.model.Patch;
import net.chrupki.model.Version;

public class ProjectModel {

    public ProjectModel() {}

    private static final ObservableList<Version> versions = FXCollections.observableArrayList();
    private static final ObservableList<String> projects = FXCollections.observableArrayList();
    private static final ObservableList<Patch> patches = FXCollections.observableArrayList();

    private static final BooleanProperty editActive = new SimpleBooleanProperty(false);
    private static final BooleanProperty editProject = new SimpleBooleanProperty(false);
    private static final BooleanProperty editVersion = new SimpleBooleanProperty(false);
    private static final BooleanProperty editPatch = new SimpleBooleanProperty(false);

    private static final BooleanProperty switchProjectsModal =
            new SimpleBooleanProperty(false);

    private static final BooleanProperty switchEditProjectsModal =
            new SimpleBooleanProperty(false);

    private static final BooleanProperty switchCreateProjectsModal =
            new SimpleBooleanProperty(false);

    public static void setSwitchProjectsModal(boolean value) {
        switchProjectsModal.set(value);
    }

    public static BooleanProperty getSwitchProjectsModal() {
        return switchProjectsModal;
    }

    public static void setSwitchEditProjectsModal(boolean value) {
        switchEditProjectsModal.set(value);
    }

    public static BooleanProperty getSwitchEditProjectsModal() {
        return switchEditProjectsModal;
    }

    public static void setSwitchCreateProjectsModal(boolean value) {
        switchCreateProjectsModal.set(value);
    }

    public static BooleanProperty getSwitchCreateProjectsModal() {
        return switchCreateProjectsModal;
    }

    public static void setEditActiveProperty(boolean booleanProperty) {
        editActive.set(booleanProperty);
    }

    public static BooleanProperty getEditActiveProperty() {
        return editActive;
    }

    public static void setEditProjectProperty(boolean booleanProperty) {
        setEditActiveProperty(booleanProperty);
        editProject.set(booleanProperty);
    }

    public static BooleanProperty getEditProjectProperty() {
        return editProject;
    }

    public static void setEditVersionProperty(boolean booleanProperty) {
        setEditActiveProperty(booleanProperty);
        editVersion.set(booleanProperty);
    }

    public static BooleanProperty getEditVersionProperty() {
        return editVersion;
    }

    public static void setEditPatchProperty(boolean booleanProperty) {
        setEditActiveProperty(booleanProperty);
        editPatch.set(booleanProperty);
    }

    public static BooleanProperty getEditPatchProperty() {
        return editPatch;
    }

    public static ObservableList<Version> getVersions() {
        return versions;
    }

    public static ObservableList<String> getProjects() {
        return projects;
    }

    public static ObservableList<Patch> getPatches() { return patches; }
}
