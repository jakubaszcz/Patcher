package net.chrupki.ui.views;

import javafx.scene.layout.VBox;
import net.chrupki.app.AppContext;
import net.chrupki.ui.components.VersionListView;
import net.chrupki.ui.components.CreateVersionForm;
import net.chrupki.ui.components.CurrentProjectLabel;
import net.chrupki.ui.controllers.ProjectController;
import net.chrupki.ui.controllers.VersionController;
import net.chrupki.ui.model.ProjectModel;

public class ProjectView extends VBox {

    private static VBox view;

    private ProjectModel model;


    public ProjectView(
            ProjectModel projectModel,
            ProjectController projectController,
            VersionController versionController
    ) throws Exception {
        this.model = projectModel;

        view = new VBox(10,
                new CurrentProjectLabel(model),
                new CreateVersionForm(model, versionController::createVersion),
                new VersionListView(versionController, model));

        versionController.loadVersions(AppContext.projectContext().getName().get());

        this.getChildren().add(view);
    }
}
