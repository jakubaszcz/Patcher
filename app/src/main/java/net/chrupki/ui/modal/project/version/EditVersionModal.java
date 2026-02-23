package net.chrupki.ui.modal.project.version;

import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import net.chrupki.model.HubModel;
import net.chrupki.ui.controllers.files.dtos.EditVersion;
import net.chrupki.ui.modal.ModalTemplate;
import net.chrupki.ui.model.GlobalModel;
import net.chrupki.ui.styles.Styles;
import net.chrupki.ui.styles.theme.ButtonTheme;
import net.chrupki.ui.styles.theme.ComboBoxTheme;
import net.chrupki.ui.styles.theme.TextFieldTheme;

import java.util.function.Consumer;

public class EditVersionModal extends ModalTemplate {

    public EditVersionModal(
            Consumer<EditVersion> onSave,
            Runnable onClose
    ) {
        super("Edit version", onClose);
        titleLabel.textProperty().bind(Bindings.concat("Edit version : ", HubModel.versionModel().getName()));

        TextField nameField = new TextField();
        nameField.setPromptText("Version name");
        new Styles().apply(nameField, TextFieldTheme.NORMAL);

        ComboBox<String> comboBox = new ComboBox<>();
        new Styles().apply(comboBox, ComboBoxTheme.NORMAL);


        comboBox.getItems().addAll(
                "Alpha", "Beta", "Pre-release", "HotFix", "Stable"
        );

        Button saveButton = new Button("Save");
        new Styles().apply(saveButton, ButtonTheme.NORMAL);

        saveButton.setOnAction(e -> {
            if (!nameField.getText().isBlank()) {
                onSave.accept(new EditVersion(
                        HubModel.versionModel().getId().get(),
                        nameField.getText(),
                        HubModel.projectModel().getName().get()));
            }
            nameField.clear();
            comboBox.getSelectionModel().clearSelection();
            onClose.run();
        });

        visibleProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                String currentName = HubModel.versionModel().getName().get();
                nameField.setText(currentName != null ? currentName : "");
            } else {
                nameField.clear();
            }
        });

        HubModel.versionModel().getName().addListener((obs, oldVal, newVal) -> {
            if (isVisible()) {
                nameField.setText(newVal != null ? newVal : "");
            }
        });

        visibleProperty().bind(GlobalModel.getSwitchEditVersionProjectModal());
        managedProperty().bind(GlobalModel.getSwitchEditVersionProjectModal());

        getChildren().addAll(
                nameField,
                comboBox
        );

        setOnMouseClicked(e -> e.consume());
        addActions(saveButton);
    }
}