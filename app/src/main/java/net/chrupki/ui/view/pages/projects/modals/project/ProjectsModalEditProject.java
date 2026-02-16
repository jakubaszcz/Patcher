package net.chrupki.ui.view.pages.projects.modals.project;

import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import net.chrupki.model.HubModel;
import net.chrupki.ui.model.GlobalModel;
import net.chrupki.ui.styles.Styles;
import net.chrupki.ui.styles.theme.ButtonTheme;
import net.chrupki.ui.styles.theme.CardTheme;
import net.chrupki.ui.styles.theme.TextFieldTheme;
import net.chrupki.ui.styles.theme.TextTheme;

import java.util.function.Consumer;

public class ProjectsModalEditProject extends VBox {

    public interface SaveProjectHandler {
        void accept(String oldName, String newName, String description);
    }

    public ProjectsModalEditProject(
            SaveProjectHandler onSave,
            Runnable onDelete,
            Runnable onClose
    ) {

        StringProperty projectName = HubModel.projectModel().getName();
        StringProperty projectDescription = HubModel.projectModel().getDescription();

        Label title = new Label("Edit project");
        new Styles().apply(title, TextTheme.SUBTITLE);

        TextField currentName = new TextField();
        currentName.textProperty().bind(projectName);

        currentName.setDisable(true);
        new Styles().apply(currentName, TextFieldTheme.NORMAL);

        TextField newName = new TextField();
        newName.setPromptText("New project name");
        new Styles().apply(newName, TextFieldTheme.NORMAL);

        TextArea descriptionArea = new TextArea();
        descriptionArea.setPromptText("Description");
        descriptionArea.setPrefHeight(100);
        descriptionArea.setWrapText(true);
        descriptionArea.textProperty().bindBidirectional(projectDescription);
        new Styles().apply(descriptionArea, TextFieldTheme.NORMAL);

        Button deleteButton = new Button("Delete");
        new Styles().apply(deleteButton, ButtonTheme.DANGER);

        Button closeButton = new Button("Cancel");
        new Styles().apply(closeButton, ButtonTheme.CANCEL);

        Button saveButton = new Button("Save");
        new Styles().apply(saveButton, ButtonTheme.NORMAL);

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
            String nameToSave = newName.getText().isBlank() ? HubModel.projectModel().getName().get() : newName.getText();
            onSave.accept(HubModel.projectModel().getName().get(), nameToSave, descriptionArea.getText());
            onClose.run();
            newName.clear();
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

        new Styles().apply(this, CardTheme.NORMAL);

        visibleProperty().bind(GlobalModel.getSwitchEditProjectsModal());
        managedProperty().bind(GlobalModel.getSwitchEditProjectsModal());

        getChildren().addAll(
                title,
                currentName,
                newName,
                descriptionArea,
                actions
        );
    }
}
