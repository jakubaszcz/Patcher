package net.chrupki.ui.views;

import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import net.chrupki.ui.components.modal.ModalPatchForm;
import net.chrupki.ui.components.modal.ModalProjectForm;
import net.chrupki.ui.components.modal.ModalVersionForm;
import net.chrupki.ui.controllers.files.PatchController;
import net.chrupki.ui.controllers.files.ProjectController;
import net.chrupki.ui.controllers.files.VersionController;
import net.chrupki.ui.model.ProjectModel;


public class ModalView extends StackPane {

    private ProjectModel model;

    public ModalView(ProjectModel model,
                     ProjectController projectController,
                     VersionController versionController,
                     PatchController patchController) {
        this.model = model;

        Pane backdrop = new Pane();

        VBox popup = new VBox();
        popup.setAlignment(Pos.CENTER);
        popup.setStyle("""
            -fx-background-color: rgba(0,0,0,0.32);
            -fx-padding: 20;
        """);

        getChildren().addAll(backdrop, popup);

        popup.getChildren().addAll(
                new ModalProjectForm(model, projectController::saveProject, projectController::onDelete , projectController::closeModal),
                new ModalVersionForm(model, patchController::loadPatches, versionController::saveVersion, versionController::deleteVersion, versionController::closeModal),
                new ModalPatchForm(model, patchController::savePatch, patchController::deletePatch, patchController::closeModal)
        );

        visibleProperty().bind(model.getEditActiveProperty());

        managedProperty().bind(visibleProperty());

        StackPane.setAlignment(popup, Pos.CENTER);
    }
}
