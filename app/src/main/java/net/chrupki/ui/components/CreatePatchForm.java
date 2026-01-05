package net.chrupki.ui.components;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;
import net.chrupki.model.Patch;
import net.chrupki.model.PatchRequest;
import net.chrupki.ui.model.ProjectModel;

import java.util.function.Consumer;


public class CreatePatchForm extends VBox {

    public CreatePatchForm(ProjectModel model, Consumer<PatchRequest> onCreatePatch) {
        HBox box = new HBox();

        TextField textField = new TextField();

        Button button = new Button("Create patch");

        ComboBox<String> comboBox = new ComboBox<>();

        comboBox.getItems().addAll(
                "Patch", "Add", "Features", "Fix"
        );

        comboBox.getSelectionModel().selectFirst();

        button.setOnAction(e -> {
            PatchRequest request = new PatchRequest(
                    textField.getText(),
                    comboBox.getValue()
            );

            textField.clear();
            comboBox.getSelectionModel().selectFirst();

            onCreatePatch.accept(request);
            model.getPatches().add(new Patch(
                    textField.getText(),
                    comboBox.getValue()
                    )
            );

            textField.clear();
            comboBox.getSelectionModel().selectFirst();
        });

        box.getChildren().addAll(textField, comboBox, button);

        this.getChildren().add(box);
    }
}
