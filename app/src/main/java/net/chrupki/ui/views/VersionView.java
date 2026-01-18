package net.chrupki.ui.views;

import javafx.scene.layout.VBox;
import net.chrupki.app.AppContext;
import net.chrupki.ui.components.CreatePatchForm;
import net.chrupki.ui.components.PatchListView;
import net.chrupki.ui.controllers.files.ExportController;
import net.chrupki.ui.controllers.files.PatchController;
import net.chrupki.ui.model.ProjectModel;


public class VersionView extends VBox {

    private static VBox view;


    public VersionView(ProjectModel projectModel, PatchController patchController, ExportController exportController) {

        view = new VBox(10);

        view.visibleProperty().bind(AppContext.versionContext().getId().isNotNull());

        view.getChildren().addAll(
                new CreatePatchForm(projectModel, patchController::createPatch, exportController::export),
                new PatchListView(projectModel)
        );

        this.getChildren().add(view);
    }
}
