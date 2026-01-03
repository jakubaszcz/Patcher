package net.chrupki.ui.views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import net.chrupki.app.AppData;
import net.chrupki.database.dao.VersionDAO;
import net.chrupki.ui.components.CreateProjectComponent;
import net.chrupki.ui.components.PickProjectComponent;
import net.chrupki.ui.model.ProjectModel;

public class MainView {

    private static final String APP_NAME = "patcher";

    private static void setup(Stage stage) throws Exception {
        stage.setTitle(APP_NAME);
        stage.setMinWidth(600);
        stage.setMinHeight(400);
    }

    private static void scene(Stage stage, VBox root) {
        Scene scene = new Scene(root, 400, 200);
        stage.setScene(scene);
    }

    public static void display(Stage stage) throws Exception {


        // Set up the application stage
        setup(stage);

        ProjectModel model = new ProjectModel();

        VBox root = new VBox(10,
                new CreateProjectComponent(model),
                new PickProjectComponent(model),
                new ProjectView(model)
        );

        scene(stage, root);

        stage.show();
    }
}
