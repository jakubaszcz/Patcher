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

    public ModalProjectForm(ProjectModel model, BiConsumer<String, String> onSave, Consumer<String> onDelete) {

        String projectName = AppContext.projectContext().getName().get();

        setAlignment(Pos.CENTER);

        visibleProperty().bind(model.getEditProjectProperty());

        Label label = new Label();

        TextField textField = new TextField();

        textField.setPromptText("Enter new project name");

        Button save = new Button("Save");

        Button delete = new Button("Delete");

        save.setOnAction(e -> {
            onSave.accept(projectName, textField.getText());
        });

        delete.setOnAction(e -> {
            onDelete.accept(projectName);
        });

        label.textProperty().bind(AppContext.projectContext().getName());

        getChildren().addAll(label, textField, save, delete);

    }
}
