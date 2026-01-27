package net.chrupki.ui.view.pages.project.modals.patch;

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
import net.chrupki.ui.controllers.files.dtos.EditPatch;
import net.chrupki.ui.model.GlobalModel;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class EditPatchModal extends VBox {

    public EditPatchModal(
            Consumer<EditPatch> onSave,
            BiConsumer<Integer, Integer> onDelete,
            Runnable onClose
    ) {
        Label title = new Label("Edit patch");
        title.getStyleClass().add("modal-title");

        TextField textField = new TextField();
        textField.setPromptText("Patch name");
        textField.getStyleClass().add("modal-textfield");

        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getStyleClass().add("modal-combobox");

        comboBox.getItems().addAll(
                "Patch", "Add", "Features", "Fix"
        );

        comboBox.setPromptText("Select a type");

        Button deleteButton = new Button("Delete");
        deleteButton.getStyleClass().add("modal-button-danger");

        Button closeButton = new Button("Cancel");
        closeButton.getStyleClass().add("modal-button-close");

        Button createButton = new Button("Save");
        createButton.getStyleClass().add("modal-button-create");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox actions = new HBox(12, deleteButton, closeButton, spacer, createButton);
        actions.setAlignment(Pos.CENTER);

        setSpacing(16);
        setPadding(new Insets(18));
        setAlignment(Pos.CENTER_LEFT);

        setPrefWidth(360);
        setMaxWidth(360);

        createButton.setOnAction(e -> {
            onSave.accept(
                    new EditPatch(
                            HubModel.patchModel().getId().get(),
                            HubModel.patchModel().getVid().get(),
                            textField.getText(),
                            comboBox.getValue()
                    )
            );
            textField.clear();
            comboBox.getSelectionModel().clearSelection();
            comboBox.setValue(null);
            comboBox.setPromptText("Select a type");
            onClose.run();
        });

        deleteButton.setOnAction(e -> {
            onDelete.accept(
                    HubModel.patchModel().getId().get(),
                    HubModel.patchModel().getVid().get()
            );
        });

        closeButton.setOnAction(e -> {
            onClose.run();
        });

        getStyleClass().add("modal-card");

        visibleProperty().bind(GlobalModel.getSwitchEditPatchProjectModal());
        managedProperty().bind(GlobalModel.getSwitchEditPatchProjectModal());


        getChildren().addAll(
                title,
                textField,
                comboBox,
                actions
        );
    }

}
