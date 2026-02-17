package net.chrupki.ui.modal.project.patch;

import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
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
import javafx.util.StringConverter;
import net.chrupki.dto.TagDTO;
import net.chrupki.model.HubModel;
import net.chrupki.ui.controllers.files.dtos.EditPatch;
import net.chrupki.ui.modal.ModalTemplate;
import net.chrupki.ui.model.GlobalModel;
import net.chrupki.ui.styles.Styles;
import net.chrupki.ui.styles.theme.ButtonTheme;
import net.chrupki.ui.styles.theme.TextFieldTheme;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class EditPatchModal extends ModalTemplate {

    public ComboBox<TagDTO> comboBox = new ComboBox<>();


    public EditPatchModal(
            Consumer<EditPatch> onSave,
            BiConsumer<Integer, Integer> onDelete,
            Runnable onClose
    ) {
        super("Edit patch", onClose);
        titleLabel.textProperty().bind(Bindings.concat("Edit patch : ", HubModel.patchModel().getContent()));

        TextField nameField = new TextField();
        nameField.setPromptText("Patch name");
        new Styles().apply(nameField, TextFieldTheme.NORMAL);

        comboBox.getStyleClass().add("modal-combobox");

        ObservableList<TagDTO> tags = GlobalModel.getTags();

        comboBox.getItems().setAll(tags);

        comboBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(TagDTO tag) {
                return tag == null ? "" : tag.getName();
            }

            @Override
            public TagDTO fromString(String string) {
                return null;
            }
        });


        tags.addListener((javafx.collections.ListChangeListener<TagDTO>) change -> {
            comboBox.getItems().setAll(tags);
        });

        comboBox.setPromptText("Select a type");

        Button deleteButton = new Button("Delete");
        new Styles().apply(deleteButton, ButtonTheme.DANGER);

        Button saveButton = new Button("Save");
        new Styles().apply(saveButton, ButtonTheme.NORMAL);

        saveButton.setOnAction(e -> {
            onSave.accept(
                    new EditPatch(
                            HubModel.patchModel().getId().get(),
                            HubModel.patchModel().getVid().get(),
                            nameField.getText(),
                            comboBox.getValue().getId()
                    )
            );
            onClose.run();
        });

        deleteButton.setOnAction(e -> {
            onDelete.accept(
                    HubModel.patchModel().getId().get(),
                    HubModel.patchModel().getVid().get()
            );
            onClose.run();
        });

        visibleProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                String currentContent = HubModel.patchModel().getContent().get();
                nameField.setText(currentContent != null ? currentContent : "");
                updateComboBoxSelection();
            } else {
                nameField.clear();
                comboBox.getSelectionModel().clearSelection();
                comboBox.setValue(null);
                comboBox.setPromptText("Select a type");
            }
        });

        HubModel.patchModel().getContent().addListener((obs, oldVal, newVal) -> {
            if (isVisible()) {
                nameField.setText(newVal != null ? newVal : "");
            }
        });

        HubModel.patchModel().getTid().addListener((obs, oldVal, newVal) -> {
            if (isVisible()) {
                updateComboBoxSelection();
            }
        });

        visibleProperty().bind(GlobalModel.getSwitchEditPatchProjectModal());
        managedProperty().bind(GlobalModel.getSwitchEditPatchProjectModal());


        getChildren().addAll(
                nameField,
                comboBox
        );

        actions.getChildren().add(0, deleteButton);
        addActions(saveButton);
    }

    private void updateComboBoxSelection() {
        int tid = HubModel.patchModel().getTid().get();
        for (TagDTO tag : GlobalModel.getTags()) {
            if (tag.getId() == tid) {
                comboBox.getSelectionModel().select(tag);
                break;
            }
        }
    }

}
