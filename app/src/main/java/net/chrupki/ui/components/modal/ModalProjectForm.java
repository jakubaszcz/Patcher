package net.chrupki.ui.components.modal;

import com.sun.javafx.scene.control.InputField;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import net.chrupki.app.AppContext;
import net.chrupki.ui.model.ProjectModel;

import java.util.function.BiConsumer;
import java.util.function.Consumer;


public class ModalProjectForm extends VBox {

    public ModalProjectForm(ProjectModel model,
                            BiConsumer<String, String> onSave,
                            Runnable onDelete,
                            Runnable onClose) {


        setAlignment(Pos.CENTER);

        visibleProperty().bind(model.getEditProjectProperty());
        managedProperty().bind(model.getEditProjectProperty());

        Label label = new Label();

        TextField textField = new TextField();

        textField.setPromptText("Enter new project name");

        Button save = new Button("Save");

        Button delete = new Button("Delete");

        Button close = new Button("Close");

        save.setOnAction(e -> {
            onSave.accept(AppContext.projectContext().getName().get(), textField.getText());
        });

        delete.setOnAction(e -> {
            onDelete.run();
        });

        close.setOnAction(e -> {
            onClose.run();
        });

        label.textProperty().bind(AppContext.projectContext().getName());

        getChildren().addAll(label, textField, save, delete, close);

    }
}
