package net.chrupki.ui.modal.project.version;

import javafx.event.Event;
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
import net.chrupki.ui.modal.ModalTemplate;
import net.chrupki.ui.model.GlobalModel;
import net.chrupki.ui.styles.Styles;
import net.chrupki.ui.styles.theme.*;

import java.util.function.BiConsumer;

public class CreateVersionModal extends ModalTemplate {

    public CreateVersionModal(
            BiConsumer<String, String> onCreate,
            Runnable onClose
    ) {
        super("Create version", onClose);

        TextField textField = new TextField();
        textField.setPromptText("Version name");
        new Styles().apply(textField, TextFieldTheme.NORMAL);

        ComboBox<String> comboBox = new ComboBox<>();
        new Styles().apply(comboBox, ComboBoxTheme.NORMAL);


        comboBox.getItems().addAll(
                "Alpha", "Beta", "Pre-release", "HotFix", "Stable"
        );

        comboBox.setPromptText("Select a type");

        Button createButton = new Button("Create");
        new Styles().apply(createButton, ButtonTheme.NORMAL);

        createButton.setOnAction(e -> {
            if (textField.getText() == null || textField.getText().isBlank()) {
                GlobalModel.setErrorMessage("No version name provided");
                return;
            }

            if (comboBox.getValue() == null || comboBox.getValue().isBlank()) {
                GlobalModel.setErrorMessage("No type provided");
                return;
            }
            onCreate.accept(textField.getText(), comboBox.getValue());
            textField.clear();
            onClose.run();
        });

        visibleProperty().bind(GlobalModel.getSwitchCreateVersionProjectModal());
        managedProperty().bind(GlobalModel.getSwitchCreateVersionProjectModal());


        getChildren().addAll(
                textField,
                comboBox
        );

        setOnMouseClicked(Event::consume);
        addActions(createButton);
    }

}
