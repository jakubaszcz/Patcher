package net.chrupki.ui.view.pages.projects.modals.project;

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
import net.chrupki.ui.model.GlobalModel;
import net.chrupki.ui.styles.Styles;
import net.chrupki.ui.styles.theme.ButtonTheme;
import net.chrupki.ui.styles.theme.CardTheme;
import net.chrupki.ui.styles.theme.TextFieldTheme;
import net.chrupki.ui.styles.theme.TextTheme;

import java.util.function.Consumer;

public class ProjectsModalCreateProject extends VBox {

    public ProjectsModalCreateProject(
            Consumer<ProjectDTO> onCreate,
            Runnable onClose
    ) {

        Label title = new Label("Create project");
        new Styles().apply(title, TextTheme.SUBTITLE);

        TextField textField = new TextField();
        textField.setPromptText("Project name");
        new Styles().apply(textField, TextFieldTheme.NORMAL);

        TextArea textArea = new TextArea();
        textArea.setPromptText("Description");
        textArea.setPrefHeight(100);
        textArea.setWrapText(true);
        new Styles().apply(textArea, TextFieldTheme.NORMAL);

        Button closeButton = new Button("Cancel");
        new Styles().apply(closeButton, ButtonTheme.CANCEL);

        Button createButton = new Button("Create");
        new Styles().apply(createButton, ButtonTheme.NORMAL);

        closeButton.setOnAction(e -> {
            textField.clear();
            textArea.clear();
            onClose.run();
        });

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

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox actions = new HBox(12, closeButton, spacer, createButton);
        actions.setAlignment(Pos.CENTER);

        setSpacing(16);
        setPadding(new Insets(18));
        setAlignment(Pos.CENTER_LEFT);

        setPrefWidth(360);
        setMaxWidth(360);

        new Styles().apply(this, CardTheme.NORMAL);

        visibleProperty().bind(GlobalModel.getSwitchCreateProjectsModal());
        managedProperty().bind(GlobalModel.getSwitchCreateProjectsModal());

        getChildren().addAll(
                title,
                textField,
                textArea,
                actions
        );
    }
}
