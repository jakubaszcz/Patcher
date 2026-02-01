package net.chrupki.ui.view.pages.project.modals;

import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import net.chrupki.ui.controllers.HubController;
import net.chrupki.ui.model.GlobalModel;
import net.chrupki.ui.view.pages.project.modals.export.ExportModal;
import net.chrupki.ui.view.pages.project.modals.patch.CreatePatchModal;
import net.chrupki.ui.view.pages.project.modals.patch.EditPatchModal;
import net.chrupki.ui.view.pages.project.modals.tag.TagModal;
import net.chrupki.ui.view.pages.project.modals.version.CreateVersionModal;
import net.chrupki.ui.view.pages.project.modals.version.EditVersionModal;

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
            GlobalModel.setSwitchProjectModal(false);
            GlobalModel.setSwitchCreateVersionProjectModal(false);
            GlobalModel.setSwitchEditVersionProjectModal(false);
            GlobalModel.setSwitchCreatePatchProjectModal(false);
            GlobalModel.setSwitchEditPatchProjectModal(false);
            GlobalModel.setSwitchExportModal(false);
            GlobalModel.setSwitchTagModal(false);
        });

        getChildren().addAll(pane, vBox);

        visibleProperty().bind(GlobalModel.getSwitchProjectModal());

        vBox.getChildren().addAll(
            new CreateVersionModal(
                    HubController.getVersionController()::createVersion,
                    HubController.getVersionController()::closeModal
            ),
                new EditVersionModal(
                        HubController.getVersionController()::saveVersion,
                        HubController.getVersionController()::deleteVersion,
                        HubController.getVersionController()::closeModal
                ),
                new CreatePatchModal(
                        HubController.getPatchController()::createPatch,
                        HubController.getPatchController()::closeModal
                ),
                new EditPatchModal(
                    HubController.getPatchController()::savePatch,
                    HubController.getPatchController()::deletePatch,
                    HubController.getPatchController()::closeModal
                ),
                new ExportModal(
                        HubController.getExportController()::closeModal,
                        HubController.getExportController()::export
                ),
                new TagModal()
        );

        managedProperty().bind(visibleProperty());

        StackPane.setAlignment(vBox, Pos.CENTER);
    }

}
