package net.chrupki.ui.view.pages.project.modals.tags;

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
import net.chrupki.dto.TagDTO;
import net.chrupki.model.HubModel;
import net.chrupki.ui.controllers.files.dtos.EditPatch;
import net.chrupki.ui.controllers.files.dtos.EditTag;
import net.chrupki.ui.model.GlobalModel;

import java.util.function.Consumer;

public class EditTagModal extends VBox {

    public EditTagModal(
            Consumer<EditTag> onSave,
            Consumer<Integer> onDelete,
            Runnable onClose
    ) {
        Label title = new Label("Edit tag");
        title.getStyleClass().add("modal-title");

        TextField textField = new TextField();
        textField.setPromptText("Tag name");
        textField.getStyleClass().add("modal-textfield");

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
                    new EditTag(
                            HubModel.tagModel().getId().get(),
                            textField.getText()
                    )
            );
            textField.clear();
            onClose.run();
        });

        deleteButton.setOnAction(e -> {
            onDelete.accept(
                    HubModel.tagModel().getId().get()
            );
            onClose.run();

        });

        closeButton.setOnAction(e -> {
            onClose.run();
        });

        getStyleClass().add("modal-card");

        visibleProperty().bind(GlobalModel.getSwitchEditTagProjectModal());
        managedProperty().bind(GlobalModel.getSwitchEditTagProjectModal());


        getChildren().addAll(
                title,
                textField,
                actions
        );
    }

}
