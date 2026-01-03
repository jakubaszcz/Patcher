package net.chrupki.ui.components;

import javafx.beans.property.StringProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import net.chrupki.app.AppData;
import net.chrupki.project.AppProject;
import net.chrupki.ui.model.ProjectModel;

import java.io.IOException;

public class PickProjectComponent extends VBox {

    private ProjectModel model;

    public PickProjectComponent(ProjectModel projectModel) throws IOException {
        this.model = projectModel;

        ObservableList<String> projects = model.getProjects();

        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.setItems(projects);

        comboBox.valueProperty().addListener((obs, oldV, newV) -> {
            if (newV != null) {
                AppData.setCurrentProject(newV);
            }
        });

        this.getChildren().add(comboBox);

        projects.setAll(
                AppProject.FetchAllProjectNames()
        );
    }
}
