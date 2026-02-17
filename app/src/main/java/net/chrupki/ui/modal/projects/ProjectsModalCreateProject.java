package net.chrupki.ui.modal.projects;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import net.chrupki.dto.ProjectDTO;
import net.chrupki.ui.modal.ModalTemplate;
import net.chrupki.ui.model.GlobalModel;
import net.chrupki.ui.styles.Styles;
import net.chrupki.ui.styles.theme.ButtonTheme;
import net.chrupki.ui.styles.theme.TextFieldTheme;

import java.util.function.Consumer;

public class ProjectsModalCreateProject extends ModalTemplate {

    public ProjectsModalCreateProject(
            Consumer<ProjectDTO> onCreate,
            Runnable onClose
    ) {
        super("Create project", onClose);

        TextField textField = new TextField();
        textField.setPromptText("Project name");
        new Styles().apply(textField, TextFieldTheme.NORMAL);

        TextArea textArea = new TextArea();
        textArea.setPromptText("Description");
        textArea.setPrefHeight(100);
        textArea.setWrapText(true);
        new Styles().apply(textArea, TextFieldTheme.NORMAL);

        Button createButton = new Button("Create");
        new Styles().apply(createButton, ButtonTheme.NORMAL);

        createButton.setOnAction(e -> {
            if (!textField.getText().isBlank()) {
                String desc = textArea.getText();
                if (desc != null && desc.length() > 300) {
                    desc = desc.substring(0, 300);
                }
                onCreate.accept(new ProjectDTO(
                        textField.getText(),
                        desc
                ));
                onClose.run();
                textField.clear();
                textArea.clear();
            }
        });

        visibleProperty().bind(GlobalModel.getSwitchCreateProjectsModal());
        managedProperty().bind(GlobalModel.getSwitchCreateProjectsModal());

        getChildren().addAll(
                textField,
                textArea
        );

        setOnMouseClicked(e -> e.consume());
        addActions(createButton);
    }
}
