package net.chrupki.ui.views;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import net.chrupki.app.AppContext;
import net.chrupki.app.context.ProjectContext;
import net.chrupki.ui.components.modal.ModalProjectForm;
import net.chrupki.ui.controllers.ProjectController;
import net.chrupki.ui.model.ProjectModel;
import javafx.beans.binding.Bindings;


public class ModalView extends StackPane {

    private ProjectModel model;

    public ModalView(ProjectModel model, ProjectController projectController) {
        this.model = model;

        Pane backdrop = new Pane();

        VBox popup = new VBox();
        popup.setAlignment(Pos.CENTER);
        popup.setStyle("""
            -fx-background-color: rgba(0,0,0,0.32);
            -fx-padding: 20;
        """);

        getChildren().addAll(backdrop, popup);

        popup.getChildren().add(
                new ModalProjectForm(model, projectController::saveProject, projectController::deleteProject)
        );

        visibleProperty().bind(model.getEditActiveProperty());

        managedProperty().bind(visibleProperty());

        StackPane.setAlignment(popup, Pos.CENTER);
    }
}
