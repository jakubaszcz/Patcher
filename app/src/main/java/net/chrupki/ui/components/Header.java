package net.chrupki.ui.components;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import net.chrupki.ui.model.ProjectModel;

import java.util.function.Consumer;

public class Header extends HBox {

    public Header(ProjectModel projectModel, Consumer<String> onCreateProject, Consumer<String> onSelectProject) {

        Label title = new Label("Patcher");
        title.getStyleClass().add("header-title");

        ComboBox<String> dropdown = new ComboBox<>();
        dropdown.getStyleClass().add("header-dropdown");

        Button button = new Button("+ Create");
        button.getStyleClass().add("header-button");

        ObservableList<String> projects = projectModel.getProjects();

        dropdown.setItems(projects);

        dropdown.valueProperty().addListener((obs, oldV, newV) -> {
            if (newV != null) {
                onSelectProject.accept(newV);
            }
        });

        this.getChildren().addAll(title, dropdown, button);
        this.getStyleClass().add("header");
        this.setAlignment(Pos.CENTER_LEFT);
        this.setMinHeight(48);
        this.setPrefHeight(48);
    }
}
