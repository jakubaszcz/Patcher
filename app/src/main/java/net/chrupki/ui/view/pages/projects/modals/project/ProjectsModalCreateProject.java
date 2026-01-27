package net.chrupki.ui.view.pages.projects.modals.project;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import net.chrupki.dto.ProjectDTO;
import net.chrupki.ui.model.GlobalModel;

import java.util.function.Consumer;

public class ProjectsModalCreateProject extends VBox {

    public ProjectsModalCreateProject(
            Consumer<ProjectDTO> onCreate,
            Runnable onClose
    ) {

        Label title = new Label("Create project");
        title.getStyleClass().add("modal-title");

        TextField textField = new TextField();
        textField.setPromptText("Project name");
        textField.getStyleClass().add("modal-textfield");

        Button closeButton = new Button("Cancel");
        closeButton.getStyleClass().add("modal-button-close");

        Button createButton = new Button("Create");
        createButton.getStyleClass().add("modal-button-create");

        closeButton.setOnAction(e -> {
            textField.clear();
            onClose.run();
        });

        createButton.setOnAction(e -> {
            if (!textField.getText().isBlank()) {
                onCreate.accept(new ProjectDTO(
                        textField.getText()
                ));
                onClose.run();
                textField.clear();
            }
        });

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox actions = new HBox(12, closeButton, spacer, createButton);
        actions.setAlignment(Pos.CENTER);

        setSpacing(16);
        setPadding(new Insets(18));
        setAlignment(Pos.CENTER_LEFT);

        setPrefWidth(360);
        setMaxWidth(360);

        getStyleClass().add("modal-card");

        visibleProperty().bind(GlobalModel.getSwitchCreateProjectsModal());
        managedProperty().bind(GlobalModel.getSwitchCreateProjectsModal());

        getChildren().addAll(
                title,
                textField,
                actions
        );
    }
}
