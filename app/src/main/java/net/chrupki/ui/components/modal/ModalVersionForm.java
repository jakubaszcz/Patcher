package net.chrupki.ui.components.modal;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import net.chrupki.app.AppContext;
import net.chrupki.ui.controllers.structures.EditVersion;
import net.chrupki.ui.model.ProjectModel;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ModalVersionForm extends VBox {

    public ModalVersionForm(ProjectModel model, Consumer<EditVersion> onSave, Runnable onClose) {

        setAlignment(Pos.CENTER);

        visibleProperty().bind(model.getEditVersionProperty());
        managedProperty().bind(model.getEditVersionProperty());


        Label label = new Label();

        TextField textField = new TextField();

        textField.setPromptText("Enter new version name");

        Button save = new Button("Save");

        Button close = new Button("Close");

        save.setOnAction(e -> {
            onSave.accept(new EditVersion(
                            AppContext.versionContext().getId().get(),
                            textField.getText(),
                            AppContext.projectContext().getName().get()
                    ));
        });

        close.setOnAction(e -> {
            onClose.run();
        });

        label.textProperty().bind(AppContext.projectContext().getName());

        getChildren().addAll(label, textField, save, close);

    }
}
