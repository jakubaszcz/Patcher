package net.chrupki.ui.view;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import net.chrupki.ui.controllers.HubController;
import net.chrupki.ui.modal.Modal;
import net.chrupki.ui.util.Css;
import net.chrupki.ui.view.manager.PageManager;
import net.chrupki.ui.view.pages.projects.ProjectsView;

public class MainPage {

    private static final String APP_NAME = "patcher";

    private static void setup(Stage stage) {
        stage.setTitle(APP_NAME);
        stage.setMinWidth(600);
        stage.setMinHeight(400);
    }

    private static void scene(Stage stage, StackPane root) {
        Scene scene = new Scene(root, 1000, 800);

        scene.getStylesheets().addAll(
                Css.load("theme.css"),
                Css.load("header.css"),
                Css.load("card.css"),
                Css.load("container.css"),
                Css.load("text.css"),
                Css.load("button.css"),
                Css.load("icon.css"),
                Css.load("combo-box.css"),
                Css.load("text-field.css"),
                Css.load("scroll.css")
        );

        stage.setScene(scene);
    }

    public static void display(Stage stage) {

        PageManager viewManager = new PageManager();

        StackPane root = new StackPane();
        root.getChildren().add(viewManager.getContainer());
        root.getChildren().add(new Modal());

        viewManager.show(new ProjectsView(viewManager));

        // Set up the application stage
        setup(stage);

        scene(stage, root);

        HubController.getProjectController().loadProjects();
        HubController.getPatchController().loadPatches();
        HubController.getTemplateController().loadTemplates();
        HubController.getTagController().load();

        stage.show();
    }
}
