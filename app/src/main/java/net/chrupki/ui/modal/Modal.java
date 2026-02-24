package net.chrupki.ui.modal;

import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import net.chrupki.ui.controllers.HubController;
import net.chrupki.ui.modal.project.export.ExportModal;
import net.chrupki.ui.modal.project.patch.CreatePatchModal;
import net.chrupki.ui.modal.project.patch.EditPatchModal;
import net.chrupki.ui.modal.project.tags.CreateTagModal;
import net.chrupki.ui.modal.project.tags.EditTagModal;
import net.chrupki.ui.modal.project.version.CreateVersionModal;
import net.chrupki.ui.modal.project.version.EditVersionModal;
import net.chrupki.ui.model.GlobalModel;
import net.chrupki.ui.modal.projects.ProjectsModalCreateProject;
import net.chrupki.ui.modal.projects.ProjectsModalEditProject;

public class Modal extends StackPane {
    public Modal() {
        Pane pane = new Pane();

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setStyle("""
                    -fx-background-color: rgba(0,0,0,0.32);
                    -fx-padding: 20;
                """);

        vBox.setOnMouseClicked(e -> {
            GlobalModel.setSwitchProjectsModal(false);
            GlobalModel.setSwitchProjectModal(false);
            GlobalModel.setSwitchCreateProjectsModal(false);
            GlobalModel.setSwitchEditProjectsModal(false);
            GlobalModel.setSwitchCreateVersionProjectModal(false);
            GlobalModel.setSwitchEditVersionProjectModal(false);
            GlobalModel.setSwitchCreatePatchProjectModal(false);
            GlobalModel.setSwitchEditPatchProjectModal(false);
            GlobalModel.setSwitchExportModal(false);
            GlobalModel.setSwitchCreateTagProjectModal(false);
            GlobalModel.setSwitchEditTagProjectModal(false);
            GlobalModel.setSwitchConfirmModal(false);

            GlobalModel.setErrorMessage(null);
        });

        getChildren().addAll(pane, vBox);

        visibleProperty().bind(
                GlobalModel.getSwitchProjectsModal()
                        .or(GlobalModel.getSwitchProjectModal())
                        .or(GlobalModel.getSwitchExportModal())
        );

        vBox.getChildren().addAll(
                new ProjectsModalCreateProject(
                        HubController.getProjectController()::createProject,
                        HubController.getProjectController()::closeModal
                ),
                new ProjectsModalEditProject(
                        HubController.getProjectController()::saveProject,
                        HubController.getProjectController()::closeModal
                ),
                new CreateVersionModal(
                        HubController.getVersionController()::createVersion,
                        HubController.getProjectController()::closeModal
                ),
                new EditVersionModal(
                        HubController.getVersionController()::saveVersion,
                        HubController.getProjectController()::closeModal
                ),
                new CreatePatchModal(
                        HubController.getPatchController()::createPatch,
                        HubController.getProjectController()::closeModal
                ),
                new EditPatchModal(
                        HubController.getPatchController()::savePatch,
                        HubController.getProjectController()::closeModal
                ),
                new ExportModal(
                        HubController.getProjectController()::closeModal,
                        HubController.getExportController()::export
                ),
                new CreateTagModal(
                        HubController.getTagController()::create,
                        HubController.getProjectController()::closeModal
                ),
                new EditTagModal(
                        HubController.getTagController()::save,
                        HubController.getProjectController()::closeModal
                ),
                new ConfirmModal(
                        HubController.getProjectController()::closeModal,
                        HubController.getProjectController()::onDelete,
                        HubController.getVersionController()::deleteVersion,
                        HubController.getTagController()::delete,
                        HubController.getPatchController()::deletePatch


                )
        );

        managedProperty().bind(visibleProperty());

        StackPane.setAlignment(vBox, Pos.CENTER);
    }
}
