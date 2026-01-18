package net.chrupki.ui.views;

import javafx.scene.layout.VBox;
import net.chrupki.ui.components.VersionListView;
import net.chrupki.ui.components.CreateVersionForm;
import net.chrupki.ui.components.CurrentProjectLabel;
import net.chrupki.ui.controllers.files.ProjectController;
import net.chrupki.ui.controllers.files.VersionController;
import net.chrupki.ui.model.ProjectModel;

public class ProjectViewt extends VBox {

    private static VBox view;

    private ProjectModel model;


    public ProjectViewt(
            ProjectModel projectModel,
            ProjectController projectController,
            VersionController versionController
    ) throws Exception {
        this.model = projectModel;

        view = new VBox(10,
                new CurrentProjectLabel(model, projectController::onEdit),
                new CreateVersionForm(model, versionController::createVersion),
                new VersionListView(versionController, model));

        versionController.loadVersions();

        this.getChildren().add(view);
    }
}
