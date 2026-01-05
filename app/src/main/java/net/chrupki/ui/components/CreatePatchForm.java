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

        Button createButton = new Button("Create patch");
        ComboBox<String> comboBoxExport = new ComboBox<>();
        Button exportButton = new Button("Export");

        ComboBox<String> comboBoxPatch = new ComboBox<>();

        comboBoxPatch.getItems().addAll(
                "Patch", "Add", "Features", "Fix"
        );

        comboBoxExport.getItems().addAll(
                "JSON", "XML"
        );

        comboBoxPatch.getSelectionModel().selectFirst();
        comboBoxExport.getSelectionModel().selectFirst();

        createButton.setOnAction(e -> {
            PatchRequest request = new PatchRequest(
                    textField.getText(),
                    comboBoxPatch.getValue()
            );

            onCreatePatch.accept(request);
            model.getPatches().add(new Patch(
                    textField.getText(),
                    comboBoxPatch.getValue()
                    )
            );

            textField.clear();
            comboBoxPatch.getSelectionModel().selectFirst();
        });

        box.getChildren().addAll(textField, comboBoxPatch, createButton, comboBoxExport, exportButton);

        this.getChildren().add(box);
    }
}
