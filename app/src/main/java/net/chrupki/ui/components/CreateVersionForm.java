package net.chrupki.ui.components;

import javafx.beans.binding.Bindings;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import net.chrupki.app.AppData;
import net.chrupki.database.dao.VersionDAO;
import net.chrupki.model.Version;
import net.chrupki.ui.model.ProjectModel;

import java.util.function.Consumer;

public class CreateVersionForm extends VBox {

    private static final String BUTTON_TEXT = "Create version";
    private static final String BLANK = "No project selected";

    private ProjectModel model;

    public CreateVersionForm(ProjectModel model, Consumer<String> onCreateVersion) {
        this.model = model;
        HBox view = new HBox(10);

        Button button = new Button();
        TextField textField = new TextField();

        button.textProperty().bind(
                Bindings.when(
                                AppData.getPropertyCurrentProjectName().isNull())
                        .then(BLANK)
                        .otherwise(BUTTON_TEXT));

        button.setOnAction(event -> {
            onCreateVersion.accept(textField.getText());
        });

        view.getChildren().addAll(button, textField);

        this.getChildren().add(view);
    }
}
