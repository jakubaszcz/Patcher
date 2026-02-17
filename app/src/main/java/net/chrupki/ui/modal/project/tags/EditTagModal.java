package net.chrupki.ui.modal.project.tags;

import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import net.chrupki.model.HubModel;
import net.chrupki.ui.controllers.files.dtos.EditTag;
import net.chrupki.ui.modal.ModalTemplate;
import net.chrupki.ui.model.GlobalModel;
import net.chrupki.ui.styles.Styles;
import net.chrupki.ui.styles.theme.ButtonTheme;
import net.chrupki.ui.styles.theme.TextFieldTheme;

import java.util.function.Consumer;

public class EditTagModal extends ModalTemplate {

    public EditTagModal(
            Consumer<EditTag> onSave,
            Consumer<Integer> onDelete,
            Runnable onClose
    ) {
        super("Edit tag", onClose);
        titleLabel.textProperty().bind(Bindings.concat("Edit tag : ", HubModel.tagModel().getName()));

        TextField nameField = new TextField();
        nameField.setPromptText("Tag name");
        new Styles().apply(nameField, TextFieldTheme.NORMAL);


        Button deleteButton = new Button("Delete");
        new Styles().apply(deleteButton, ButtonTheme.DANGER);

        Button saveButton = new Button("Save");
        new Styles().apply(saveButton, ButtonTheme.NORMAL);

        saveButton.setOnAction(e -> {
            onSave.accept(
                    new EditTag(
                            HubModel.tagModel().getId().get(),
                            nameField.getText()
                    )
            );
            onClose.run();
        });

        deleteButton.setOnAction(e -> {
            onDelete.accept(
                    HubModel.tagModel().getId().get()
            );
            onClose.run();
        });

        visibleProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                nameField.setText(HubModel.tagModel().getName().get());
            } else {
                nameField.clear();
            }
        });

        HubModel.tagModel().getName().addListener((obs, oldVal, newVal) -> {
            if (isVisible()) {
                nameField.setText(newVal != null ? newVal : "");
            }
        });

        visibleProperty().bind(GlobalModel.getSwitchEditTagProjectModal());
        managedProperty().bind(GlobalModel.getSwitchEditTagProjectModal());


        getChildren().addAll(
                nameField
        );

        actions.getChildren().add(0, deleteButton);
        setOnMouseClicked(e -> e.consume());
        addActions(saveButton);
    }

}
