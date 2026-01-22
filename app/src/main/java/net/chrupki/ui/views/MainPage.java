package net.chrupki.ui.views;

import javafx.scene.Scene;
import javafx.stage.Stage;
import net.chrupki.ui.controllers.HubController;
import net.chrupki.ui.util.Css;
import net.chrupki.ui.views.manager.PageManager;
import net.chrupki.ui.views.pages.projects.ProjectsView;

public class MainPage {

    private static final String APP_NAME = "patcher";

    private static void setup(Stage stage) {
        stage.setTitle(APP_NAME);
        stage.setMinWidth(600);
        stage.setMinHeight(400);
    }

    private static void scene(Stage stage, PageManager viewManager) {
        Scene scene = new Scene(viewManager.getContainer(), 1000, 800);

        scene.getStylesheets().addAll(
                Css.load("theme.css"),
                Css.load("header.css"),
                Css.load("card-container.css"),
                Css.load("modal.css"),
                Css.load("empty-projects.css"),
                Css.load("project.css")
        );

        stage.setScene(scene);
    }

    public static void display(Stage stage) {

        PageManager viewManager = new PageManager();

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
