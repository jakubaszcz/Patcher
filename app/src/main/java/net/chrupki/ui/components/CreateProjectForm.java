package net.chrupki.ui.components;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import net.chrupki.project.AppProject;
import net.chrupki.ui.model.ProjectModel;

import java.util.function.Consumer;

public class CreateProjectForm extends VBox {

    private static final String BUTTON_TEXT = "Create project";
    private static final String TEXTFIELD_TEXT = "Project";

    private final ProjectModel model;
    private final TextField textField = new TextField();
    private final Button button = new Button(BUTTON_TEXT);

    public CreateProjectForm(ProjectModel projectModel, Consumer<String> onCreateProject) {
        this.model = projectModel;

        textField.setPromptText(TEXTFIELD_TEXT);

        button.setOnAction(e -> {
            if (!textField.getText().isBlank()) {
                onCreateProject.accept(textField.getText());
                model.getProjects().add(textField.getText());
                textField.clear();
            }
        });

        this.getChildren().addAll(textField, button);
    }
}
