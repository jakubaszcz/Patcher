package net.chrupki.ui.views;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import net.chrupki.app.AppContext;
import net.chrupki.app.context.ProjectContext;
import net.chrupki.ui.components.modal.ModalPatchForm;
import net.chrupki.ui.components.modal.ModalProjectForm;
import net.chrupki.ui.components.modal.ModalVersionForm;
import net.chrupki.ui.controllers.PatchController;
import net.chrupki.ui.controllers.ProjectController;
import net.chrupki.ui.controllers.VersionController;
import net.chrupki.ui.model.ProjectModel;
import javafx.beans.binding.Bindings;


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
                new ModalProjectForm(model, projectController::saveProject, projectController::closeModal),
                new ModalVersionForm(model, versionController::saveVersion, versionController::closeModal),
                new ModalPatchForm(model, patchController::savePatch, patchController::closeModal)
        );

        visibleProperty().bind(model.getEditActiveProperty());

        managedProperty().bind(visibleProperty());

        StackPane.setAlignment(popup, Pos.CENTER);
    }
}
