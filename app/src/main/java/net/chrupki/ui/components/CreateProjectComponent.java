package net.chrupki.ui.components;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import net.chrupki.project.AppProject;

public class CreateProjectComponent extends VBox {

    private static final String BUTTON_TEXT = "Create project";
    private static final String TEXTFIELD_TEXT = "Project";

    public CreateProjectComponent() {
        TextField textField = new TextField();
        textField.setPromptText(TEXTFIELD_TEXT);

        Button button = new Button(BUTTON_TEXT);
        button.setOnAction(e -> {
            if (!textField.getText().isBlank()) {
                AppProject.CreateProject(textField.getText());
            }
        });

        this.getChildren().addAll(textField, button);
    }
}
