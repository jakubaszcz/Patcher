package net.chrupki.ui.views.pages.project.modals;

import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import net.chrupki.ui.controllers.HubController;
import net.chrupki.ui.model.ProjectModel;
import net.chrupki.ui.views.pages.projects.modals.files.ProjectsModalCreateProject;
import net.chrupki.ui.views.pages.projects.modals.files.ProjectsModalEditProject;

public class ProjectModal extends StackPane {

    public ProjectModal() {
        Pane pane = new Pane();

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setStyle("""
            -fx-background-color: rgba(0,0,0,0.32);
            -fx-padding: 20;
        """);

        vBox.setOnMouseClicked(e -> {
            ProjectModel.setSwitchProjectModal(false);
            ProjectModel.setSwitchCreateVersionProjectModal(false);
            ProjectModel.setSwitchEditVersionProjectModal(false);
        });

        getChildren().addAll(pane, vBox);

        visibleProperty().bind(ProjectModel.getSwitchProjectModal());

        vBox.getChildren().addAll(
            new CreateVersionModal(
                    HubController.getVersionController()::createVersion,
                    HubController.getVersionController()::closeModal
            ),
                new EditVersionModal(
                        HubController.getVersionController()::saveVersion,
                        HubController.getVersionController()::deleteVersion,
                        HubController.getVersionController()::closeModal
                )
        );

        managedProperty().bind(visibleProperty());

        StackPane.setAlignment(vBox, Pos.CENTER);
    }

}
