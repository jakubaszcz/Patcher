package net.chrupki.ui.modal.projects;

import javafx.beans.binding.Bindings;
import javafx.beans.property.StringProperty;
import javafx.event.Event;
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
import net.chrupki.ui.modal.ModalTemplate;
import net.chrupki.ui.model.GlobalModel;
import net.chrupki.ui.styles.Styles;
import net.chrupki.ui.styles.theme.ButtonTheme;
import net.chrupki.ui.styles.theme.TextFieldTheme;

public class ProjectsModalEditProject extends ModalTemplate {

    public interface SaveProjectHandler {
        void accept(String oldName, String newName, String description);
    }

    public ProjectsModalEditProject(
            SaveProjectHandler onSave,
            Runnable onClose
    ) {
        super("Edit project", onClose);
        titleLabel.textProperty().bind(Bindings.concat("Edit project : ", HubModel.projectModel().getName()));

        StringProperty projectDescription = HubModel.projectModel().getDescription();

        TextField nameField = new TextField();
        new Styles().apply(nameField, TextFieldTheme.NORMAL);

        TextArea descriptionArea = new TextArea();
        descriptionArea.setPromptText("Description");
        descriptionArea.setPrefHeight(100);
        descriptionArea.setWrapText(true);
        new Styles().apply(descriptionArea, TextFieldTheme.NORMAL);

        Button saveButton = new Button("Save");
        new Styles().apply(saveButton, ButtonTheme.NORMAL);

        saveButton.setOnAction(e -> {
            String nameToSave = nameField.getText().isBlank() ? HubModel.projectModel().getName().get() : nameField.getText();
            onSave.accept(HubModel.projectModel().getName().get(), nameToSave, descriptionArea.getText());
            onClose.run();
        });

        visibleProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                String currentName = HubModel.projectModel().getName().get();
                String currentDesc = HubModel.projectModel().getDescription().get();
                nameField.setText(currentName != null ? currentName : "");
                descriptionArea.setText(currentDesc != null ? currentDesc : "");
            } else {
                nameField.clear();
                descriptionArea.clear();
            }
        });

        HubModel.projectModel().getName().addListener((obs, oldVal, newVal) -> {
            if (isVisible()) {
                nameField.setText(newVal != null ? newVal : "");
            }
        });

        HubModel.projectModel().getDescription().addListener((obs, oldVal, newVal) -> {
            if (isVisible()) {
                descriptionArea.setText(newVal != null ? newVal : "");
            }
        });

        visibleProperty().bind(GlobalModel.getSwitchEditProjectsModal());
        managedProperty().bind(GlobalModel.getSwitchEditProjectsModal());

        getChildren().addAll(
                nameField,
                descriptionArea
        );

        setOnMouseClicked(Event::consume);
        addActions(saveButton);
    }
}
