package net.chrupki.ui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProjectModel {

    public ProjectModel() {}

    private final ObservableList<String> versions = FXCollections.observableArrayList();
    private final ObservableList<String> projects = FXCollections.observableArrayList();

    class Version {
        ObservableList<String> patches = FXCollections.observableArrayList();
        ObservableList<String> fixes = FXCollections.observableArrayList();
    }

    public ObservableList<String> getVersions() {
        return versions;
    }

    public ObservableList<String> getProjects() {
        return projects;
    }
}
