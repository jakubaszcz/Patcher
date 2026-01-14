package net.chrupki.ui.components.modal;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import net.chrupki.app.AppContext;
import net.chrupki.ui.controllers.dtos.EditPatch;
import net.chrupki.ui.controllers.dtos.EditVersion;
import net.chrupki.ui.model.ProjectModel;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ModalPatchForm extends VBox {

    public ModalPatchForm(ProjectModel model, Consumer<EditPatch> onSave, BiConsumer<Integer, Integer> onDelete, Runnable onClose) {

        setAlignment(Pos.CENTER);

        visibleProperty().bind(model.getEditPatchProperty());
        managedProperty().bind(model.getEditPatchProperty());

        Label label = new Label();

        TextField textField = new TextField();

        textField.setPromptText("Enter new project name");

        Button save = new Button("Save");

        Button delete = new Button("Delete");

        Button close = new Button("Close");

        ComboBox<String> comboBoxPatch = new ComboBox<>();

        comboBoxPatch.getItems().addAll(
                "Patch", "Add", "Features", "Fix"
        );

        save.setOnAction(e -> {
            onSave.accept(new EditPatch(
                    AppContext.patchContext().getId().get(),
                    AppContext.patchContext().getVid().get(),
                    textField.getText(),
                    comboBoxPatch.getValue()
            ));
        });

        delete.setOnAction(e -> {
            onDelete.accept(AppContext.patchContext().getId().get(),
                    AppContext.patchContext().getVid().get());
        });

        close.setOnAction(e -> {
            onClose.run();
        });

        label.textProperty().bind(AppContext.projectContext().getName());

        getChildren().addAll(label, textField, comboBoxPatch, save, delete, close);
    }
}
