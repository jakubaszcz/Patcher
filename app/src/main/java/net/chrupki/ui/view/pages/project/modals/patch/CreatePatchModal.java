package net.chrupki.ui.view.pages.project.modals.patch;

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
import net.chrupki.request.PatchRequest;
import net.chrupki.ui.model.GlobalModel;

import java.util.function.Consumer;

public class CreatePatchModal extends VBox {

    public ComboBox<TagDTO> comboBox = new ComboBox<>();

    public CreatePatchModal(
            Consumer<PatchRequest> onCreate,
            Runnable onClose
    ) {
        ObservableList<TagDTO> tags = GlobalModel.getTags();

        Label title = new Label("Create patch");
        title.getStyleClass().add("modal-title");

        TextField textField = new TextField();
        textField.setPromptText("Patch name");
        textField.getStyleClass().add("modal-textfield");

        comboBox.getStyleClass().add("modal-combobox");

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

        refresh(tags);

        tags.addListener((javafx.collections.ListChangeListener<TagDTO>) change -> {
            comboBox.getItems().setAll(tags);
        });

        comboBox.setPromptText("Select a type");

        Button closeButton = new Button("Cancel");
        closeButton.getStyleClass().add("modal-button-close");

        Button createButton = new Button("Create");
        createButton.getStyleClass().add("modal-button-create");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox actions = new HBox(12, closeButton, spacer, createButton);
        actions.setAlignment(Pos.CENTER);

        setSpacing(16);
        setPadding(new Insets(18));
        setAlignment(Pos.CENTER_LEFT);

        setPrefWidth(360);
        setMaxWidth(360);

        createButton.setOnAction(e -> {
            onCreate.accept(
                    new PatchRequest(textField.getText(), comboBox.getValue().getId(), HubModel.versionModel().getId().get())
            );
            textField.clear();
            comboBox.getSelectionModel().clearSelection();
            comboBox.setValue(null);
            comboBox.setPromptText("Select a type");
            onClose.run();
        });

        closeButton.setOnAction(e -> {
            onClose.run();
        });

        getStyleClass().add("modal-card");

        visibleProperty().bind(GlobalModel.getSwitchCreatePatchProjectModal());
        managedProperty().bind(GlobalModel.getSwitchCreatePatchProjectModal());


        getChildren().addAll(
                title,
                textField,
                comboBox,
                actions
        );
    }

    public void refresh(ObservableList<TagDTO> tags) {
        comboBox.getItems().clear();

        for (TagDTO s : tags) {
            comboBox.getItems().add(s);
        }
    }

}
