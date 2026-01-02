package net.chrupki.ui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProjectModel {

    public ProjectModel() {}

    private final ObservableList<String> versions= FXCollections.observableArrayList();

    public ObservableList<String> getVersions() {
        return versions;
    }
}
