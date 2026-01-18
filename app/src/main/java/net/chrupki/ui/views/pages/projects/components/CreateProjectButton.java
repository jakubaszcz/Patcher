package net.chrupki.ui.views.pages.projects.components;

import javafx.scene.layout.HBox;
import net.chrupki.ui.views.pages.project.ProjectView;
import net.chrupki.ui.views.pages.projects.components.model.CreateProjectButtonModel;
import javafx.scene.control.Label;


public class CreateProjectButton extends HBox {

    public CreateProjectButton(
            CreateProjectButtonModel model,
            Runnable onCreateProjectModal
    ) {

        Label label = new Label("+");
        label.getStyleClass().add("add-button-title");

        getChildren().add(label);
        getStyleClass().add("add-button");

        setOnMouseClicked(e -> {
            onCreateProjectModal.run();
        });

        setAlignment(javafx.geometry.Pos.CENTER);
        setPrefSize(model.getWidth(), model.getHeight());
        setMinSize(model.getWidth(), model.getHeight());
        setMaxSize(model.getWidth(), model.getHeight());
    }

}
