package net.chrupki.ui.views;

import javafx.scene.layout.VBox;
import net.chrupki.app.AppData;
import net.chrupki.project.services.VersionService;
import net.chrupki.ui.components.CreatePatchForm;
import net.chrupki.ui.components.PatchContainer;
import net.chrupki.ui.components.VersionContainer;
import net.chrupki.ui.controllers.PatchController;
import net.chrupki.ui.controllers.VersionController;
import net.chrupki.ui.model.ProjectModel;


public class VersionView extends VBox {

    private static VBox view;


    public VersionView(ProjectModel projectModel, PatchController patchController) throws Exception {

        view = new VBox(10);

        view.visibleProperty().bind(AppData.getVersionSelected());

        view.getChildren().addAll(
                new CreatePatchForm(projectModel, patchController::createPatch),
                new PatchContainer(projectModel)
        );

        this.getChildren().add(view);
    }
}
