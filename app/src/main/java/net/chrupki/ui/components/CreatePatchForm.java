package net.chrupki.ui.components;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;
import net.chrupki.model.PatchRequest;

import java.util.function.Consumer;


public class CreatePatchForm extends VBox {

    public CreatePatchForm(Consumer<PatchRequest> onCreatePatch) {
        HBox box = new HBox();

        TextField textField = new TextField();

        Button button = new Button("Create patch");

        ComboBox<String> comboBox = new ComboBox<>();

        comboBox.getItems().addAll(
                "Patch", "Add", "Features", "Fix"
        );

        button.setOnAction(e -> {
            PatchRequest request = new PatchRequest(
                    textField.getText(),
                    comboBox.getValue()
            );

            textField.clear();
            comboBox.getSelectionModel().selectFirst();

            onCreatePatch.accept(request);
        });

        box.getChildren().addAll(textField, comboBox, button);

        this.getChildren().add(box);
    }
}
