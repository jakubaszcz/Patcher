package net.chrupki.ui.views;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import net.chrupki.project.services.PatchService;
import net.chrupki.project.services.ProjectService;
import net.chrupki.project.services.VersionService;
import net.chrupki.project.services.exports.MarkdownExportService;
import net.chrupki.ui.components.Header;
import net.chrupki.ui.components.ProjectSelector;
import net.chrupki.ui.controllers.ExportController;
import net.chrupki.ui.controllers.PatchController;
import net.chrupki.ui.controllers.ProjectController;
import net.chrupki.ui.controllers.VersionController;
import net.chrupki.ui.model.ProjectModel;
import net.chrupki.ui.util.Css;

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
                Css.load("project-card-container.css")
        );

        stage.setScene(scene);
    }

    public static void display(Stage stage) throws Exception {

        ProjectService projectService = new ProjectService();
        VersionService versionService = new VersionService();
        PatchService patchService = new PatchService();
        MarkdownExportService markdownExportService = new MarkdownExportService();

        ProjectModel model = new ProjectModel();

        ProjectController projectController = new ProjectController(projectService, model);
        VersionController versionController = new VersionController(versionService, model);
        PatchController patchController = new PatchController(patchService, model);
        ExportController exportController = new ExportController(markdownExportService);

        ViewManager viewManager = new ViewManager();
        Header header = new Header();

        viewManager.show(new ProjectsView(viewManager, model));

        BorderPane root = new BorderPane();
        root.setTop(header);
        root.setCenter(viewManager.getContainer());

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

        projectController.loadProjects();
        patchController.loadPatches();

        stage.show();
    }
}
