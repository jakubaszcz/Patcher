package net.chrupki.ui.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import net.chrupki.ui.view.pages.project.dto.PatchDTO;
import net.chrupki.ui.view.pages.project.dto.VersionDTO;

public class ProjectModel {

    public ProjectModel() {}

    private static final ObservableList<VersionDTO> versions = FXCollections.observableArrayList();
    private static final ObservableList<String> projects = FXCollections.observableArrayList();
    private static final ObservableList<PatchDTO> patches = FXCollections.observableArrayList();

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

    private static final BooleanProperty switchProjectModal =
            new SimpleBooleanProperty(false);

    private static final BooleanProperty switchCreateVersionProjectModal =
            new SimpleBooleanProperty(false);

    private static final BooleanProperty switchEditVersionProjectModal =
            new SimpleBooleanProperty(false);

    private static final BooleanProperty switchCreatePatchProjectModal =
            new SimpleBooleanProperty(false);

    private static final BooleanProperty switchEditPatchProjectModal =
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

    public static void setSwitchProjectModal(boolean value) {
        switchProjectModal.set(value);
    }

    public static BooleanProperty getSwitchProjectModal() {
        return switchProjectModal;
    }

    public static void setSwitchCreateVersionProjectModal(boolean value) {
        switchCreateVersionProjectModal.set(value);
    }

    public static BooleanProperty getSwitchCreateVersionProjectModal() {
        return switchCreateVersionProjectModal;
    }

    public static void setSwitchEditVersionProjectModal(boolean value) {
        switchEditVersionProjectModal.set(value);
    }

    public static void setSwitchCreatePatchProjectModal(boolean value) {
        switchCreatePatchProjectModal.set(value);
    }

    public static void setSwitchEditPatchProjectModal(boolean value) {
        switchEditPatchProjectModal.set(value);
    }

    public static BooleanProperty getSwitchEditVersionProjectModal() {
        return switchEditVersionProjectModal;
    }

    public static BooleanProperty getSwitchCreatePatchProjectModal() {
        return switchCreatePatchProjectModal;
    }

    public static BooleanProperty getSwitchEditPatchProjectModal() {
        return switchEditPatchProjectModal;
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

    public static ObservableList<VersionDTO> getVersions() {
        return versions;
    }

    public static ObservableList<String> getProjects() {
        return projects;
    }

    public static ObservableList<PatchDTO> getPatches() { return patches; }
}
