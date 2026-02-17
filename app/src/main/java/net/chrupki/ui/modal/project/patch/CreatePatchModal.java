package net.chrupki.ui.modal.project.patch;

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
import net.chrupki.ui.modal.ModalTemplate;
import net.chrupki.ui.model.GlobalModel;
import net.chrupki.ui.styles.Styles;
import net.chrupki.ui.styles.theme.*;

import java.util.function.Consumer;

public class CreatePatchModal extends ModalTemplate {

    public ComboBox<TagDTO> comboBox = new ComboBox<>();

    public CreatePatchModal(
            Consumer<PatchRequest> onCreate,
            Runnable onClose
    ) {
        super("Create patch", onClose);
        ObservableList<TagDTO> tags = GlobalModel.getTags();

        TextField textField = new TextField();
        textField.setPromptText("Patch name");
        new Styles().apply(textField, TextFieldTheme.NORMAL);

        new Styles().apply(comboBox, ComboBoxTheme.NORMAL);

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

        Button createButton = new Button("Create");
        new Styles().apply(createButton, ButtonTheme.NORMAL);

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

        visibleProperty().bind(GlobalModel.getSwitchCreatePatchProjectModal());
        managedProperty().bind(GlobalModel.getSwitchCreatePatchProjectModal());


        getChildren().addAll(
                textField,
                comboBox
        );

        setOnMouseClicked(e -> e.consume());
        addActions(createButton);
    }

    public void refresh(ObservableList<TagDTO> tags) {
        comboBox.getItems().clear();

        for (TagDTO s : tags) {
            comboBox.getItems().add(s);
        }
    }

}
