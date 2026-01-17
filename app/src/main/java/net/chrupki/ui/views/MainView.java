package net.chrupki.ui.views;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import net.chrupki.project.services.PatchService;
import net.chrupki.project.services.ProjectService;
import net.chrupki.project.services.VersionService;
import net.chrupki.project.services.exports.MarkdownExportService;
import net.chrupki.ui.components.CreateProjectForm;
import net.chrupki.ui.components.ProjectSelector;
import net.chrupki.ui.controllers.ExportController;
import net.chrupki.ui.controllers.PatchController;
import net.chrupki.ui.controllers.ProjectController;
import net.chrupki.ui.controllers.VersionController;
import net.chrupki.ui.model.ProjectModel;

public class MainView {

    private static final String APP_NAME = "patcher";

    private static void setup(Stage stage) {
        stage.setTitle(APP_NAME);
        stage.setMinWidth(600);
        stage.setMinHeight(400);
    }

    private static void scene(Stage stage, StackPane root) {
        Scene scene = new Scene(root, 1000, 800);

        scene.getStylesheets().add(
                Css.load("theme.css")
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

        // Set up the application stage
        setup(stage);

        VBox mainContent = new VBox(10,
                new CreateProjectForm(model, projectController::createProject),
                new ProjectSelector(model, projectController::selectProject),
                new ProjectView(model, projectController, versionController),
                new VersionView(model, patchController, exportController)
        );

        StackPane root = new StackPane();
        root.getChildren().add(mainContent);

        // Modal View
        root.getChildren().add(new ModalView(model,
                projectController,
                versionController,
                patchController));

        projectController.loadProjects();
        patchController.loadPatches();

        scene(stage, root);
        stage.show();
    }
}
