package net.chrupki.ui.views;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import net.chrupki.project.services.files.PatchService;
import net.chrupki.project.services.files.ProjectService;
import net.chrupki.project.services.files.VersionService;
import net.chrupki.project.services.files.exports.MarkdownExportService;
import net.chrupki.ui.controllers.HubController;
import net.chrupki.ui.controllers.files.ExportController;
import net.chrupki.ui.controllers.files.PatchController;
import net.chrupki.ui.controllers.files.ProjectController;
import net.chrupki.ui.controllers.files.VersionController;
import net.chrupki.ui.model.ProjectModel;
import net.chrupki.ui.util.Css;
import net.chrupki.ui.views.manager.ViewManager;
import net.chrupki.ui.views.pages.projects.ProjectsView;

public class MainView {

    private static final String APP_NAME = "patcher";

    private static void setup(Stage stage) {
        stage.setTitle(APP_NAME);
        stage.setMinWidth(600);
        stage.setMinHeight(400);
    }

    private static void scene(Stage stage, ViewManager viewManager) {
        Scene scene = new Scene(viewManager.getContainer(), 1000, 800);

        scene.getStylesheets().addAll(
                Css.load("theme.css"),
                Css.load("header.css"),
                Css.load("card-container.css"),
                Css.load("modal.css"),
                Css.load("empty-projects.css"),
                Css.load("project-version.css")
        );

        stage.setScene(scene);
    }

    public static void display(Stage stage) {

        ViewManager viewManager = new ViewManager();

        viewManager.show(new ProjectsView(viewManager));

        // Set up the application stage
        setup(stage);

/*        VBox mainContent = new VBox(10,
                new Header(model, projectController::createProject, projectController::selectProject),
                new ProjectSelector(model, projectController::selectProject),
                new ProjectView(model, projectController, versionController),
                new VersionView(model, patchController, exportController)
        );*/

/*        StackPane root = new StackPane();
        root.getChildren().add(mainContent);*/

        // Modal View
        /*root.getChildren().add(new ModalView(model,
                projectController,
                versionController,
                patchController));*/

        scene(stage, viewManager);

        HubController.getProjectController().loadProjects();
        HubController.getPatchController().loadPatches();

        stage.show();
    }
}
