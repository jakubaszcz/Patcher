package net.chrupki.ui.views.pages.projects.modals;

import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import net.chrupki.ui.controllers.HubController;
import net.chrupki.ui.model.ProjectModel;
import net.chrupki.ui.views.pages.projects.modals.files.ProjectsModalCreateProject;

public class ProjectsModal extends StackPane {

    public ProjectsModal() {
        Pane pane = new Pane();

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setStyle("""
            -fx-background-color: rgba(0,0,0,0.32);
            -fx-padding: 20;
        """);

        getChildren().addAll(pane, vBox);

        visibleProperty().bind(ProjectModel.getSwitchProjectsModal());

        vBox.getChildren().addAll(
                new ProjectsModalCreateProject(
                        HubController.getProjectController()::createProject,
                        HubController.getProjectController()::closeCreateProjectsModal
                )
        );

        managedProperty().bind(visibleProperty());

        StackPane.setAlignment(vBox, Pos.CENTER);
    }

}
