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

    public ObservableList<Version> getVersions() {
        return versions;
    }

    public ObservableList<String> getProjects() {
        return projects;
    }

    public ObservableList<Patch> getPatches() { return patches; }
}
