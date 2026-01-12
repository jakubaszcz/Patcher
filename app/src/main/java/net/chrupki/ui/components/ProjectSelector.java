package net.chrupki.ui.components;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import net.chrupki.ui.model.ProjectModel;

import java.util.function.Consumer;

public class ProjectSelector extends VBox {

    private ProjectModel model;
    ComboBox<String> comboBox = new ComboBox<>();

    public ProjectSelector(ProjectModel projectModel, Consumer<String> onProjectSelected) {
        this.model = projectModel;

        ObservableList<String> projects = model.getProjects();

        comboBox.setItems(projects);

        comboBox.valueProperty().addListener((obs, oldV, newV) -> {
            if (newV != null) {
                onProjectSelected.accept(newV);
            }
        });

        this.getChildren().add(comboBox);
    }
}
