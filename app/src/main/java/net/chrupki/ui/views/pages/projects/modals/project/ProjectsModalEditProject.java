package net.chrupki.ui.views.pages.projects.modals.project;

import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import net.chrupki.app.AppContext;
import net.chrupki.ui.model.ProjectModel;

import java.util.function.BiConsumer;

public class ProjectsModalEditProject extends VBox {

    public ProjectsModalEditProject(
            BiConsumer<String, String> onSave,
            Runnable onDelete,
            Runnable onClose
    ) {

        StringProperty projectName = AppContext.projectContext().getName();

        Label title = new Label("Edit project");
        title.getStyleClass().add("modal-title");

        TextField currentName = new TextField();
        currentName.textProperty().bind(projectName);

        currentName.setDisable(true);
        currentName.getStyleClass().add("modal-textfield");

        TextField newName = new TextField();
        newName.setPromptText("New project name");
        newName.getStyleClass().add("modal-textfield");

        Button deleteButton = new Button("Delete");
        deleteButton.getStyleClass().add("modal-button-danger");

        Button closeButton = new Button("Cancel");
        closeButton.getStyleClass().add("modal-button-close");

        Button saveButton = new Button("Save");
        saveButton.getStyleClass().add("modal-button-create");

        deleteButton.setOnAction(e -> {
            onDelete.run();
            onClose.run();
            newName.clear();
        });
        closeButton.setOnAction(e -> {
            onClose.run();
            newName.clear();
        });

        saveButton.setOnAction(e -> {
            if (!newName.getText().isBlank()) {
                onSave.accept(AppContext.projectContext().getName().get(), newName.getText());
                onClose.run();
                newName.clear();
            }
        });

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox actions = new HBox(
                12,
                deleteButton,
                spacer,
                closeButton,
                saveButton
        );
        actions.setAlignment(Pos.CENTER);

        setSpacing(16);
        setPadding(new Insets(18));
        setAlignment(Pos.CENTER_LEFT);

        setPrefWidth(360);
        setMaxWidth(360);

        getStyleClass().add("modal-card");

        visibleProperty().bind(ProjectModel.getSwitchEditProjectsModal());
        managedProperty().bind(ProjectModel.getSwitchEditProjectsModal());

        getChildren().addAll(
                title,
                currentName,
                newName,
                actions
        );
    }
}
